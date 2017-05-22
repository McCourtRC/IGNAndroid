package com.example.corey.ignandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.corey.ignandroid.models.IGNArticlesResponse;
import com.example.corey.ignandroid.models.IGNData;
import com.example.corey.ignandroid.models.IGNObject;
import com.example.corey.ignandroid.models.IGNVideosResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.Observable;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static final String BASE_URL = "http://ign-apis.herokuapp.com/";

    private ListView listView;

    public static final String TAG = "DEBUG TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.ign_list_view);

        // Gson for custom type adaptation
        Gson gson = new GsonBuilder()
                .setDateFormat(DateFormat.LONG)
                .create();

        // Build Retrofit
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create());

        Retrofit retrofit = builder.build();

        // Build IGN Client for API consumption
        IGNClient ignClient = retrofit.create(IGNClient.class);
        Observable<IGNVideosResponse> callVideos = ignClient.getVideos(0,0);
        Observable<IGNArticlesResponse> callArticles = ignClient.getArticles(0,0);

//        callVideos.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<IGNVideosResponse>() {
//                    @Override
//                    public void onSubscribe(@NonNull Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(@NonNull IGNVideosResponse response) {
//                        // Build relevant IGN Object List
//                        List<IGNData> data = response.getData();
//                        List<IGNObject> relevantData = new ArrayList<IGNObject>();
//
//                        for (int i = 0; i < data.size(); i++) {
//                            IGNData d = data.get(i);
//                            String date = d.getMetadata().getPublishDate();
//                            String title = d.getMetadata().getName();
//                            String type = "video";
//                            String imageUrl = d.getThumbnails().get(1).getUrl();
//                            String url = d.getMetadata().getUrl();
//                            IGNObject obj = new IGNObject(date, title, type, imageUrl, url);
//                            relevantData.add(obj);
//                        }
//
//                        IGNAdapter adapter = new IGNAdapter(MainActivity.this, relevantData);
//                        listView.setAdapter(adapter);
//
//                        Log.d(TAG, "Success");
//                    }
//
//                    @Override
//                    public void onError(@NonNull Throwable e) {
//                        Log.d(TAG, "Async Error");
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });

        String url = "http://ign-apis.herokuapp.com/videos";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());

                try {
                    JSONArray data =  response.getJSONArray("data");
                    List<IGNObject> relevantData = new ArrayList<>();

                    for (int i = 0; i < data.length(); i++) {
                        JSONObject object = data.getJSONObject(i);
                        JSONObject metadata = object.getJSONObject("metadata");

                        String date = metadata.getString("publishDate");
                        String title = metadata.getString("name");
                        String type = "video";
                        JSONArray thumbnails = object.getJSONArray("thumbnails");
                        JSONObject thumb = thumbnails.getJSONObject(0);
                        String imageUrl = thumb.getString("url");
                        String url = metadata.getString("url");
                        relevantData.add(new IGNObject(date, title, type, imageUrl, url));
                    }

                    IGNAdapter adapter = new IGNAdapter(MainActivity.this, relevantData);
                    listView.setAdapter(adapter);
                }
                catch (final JSONException e) {
                    Log.d(TAG, e.toString());
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });

        RequestManager.getInstance(this).addToRequestQueue(jsonObjectRequest);

    }
}
