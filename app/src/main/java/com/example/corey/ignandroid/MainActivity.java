package com.example.corey.ignandroid;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import com.example.corey.ignandroid.models.IGNObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    public static final String BASE_URL = "http://ign-apis.herokuapp.com/";

    private ListView listView;

    private List<IGNObject> dataSource = new ArrayList<>();

    public static final String TAG = "DEBUG TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.ign_list_view);

        String url = "http://ign-apis.herokuapp.com/articles";

//        JsonObjectRequest videosRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                Log.d(TAG, response.toString());
//
//                try {
//                    JSONArray data =  response.getJSONArray("data");
//
//                    for (int i = 0; i < data.length(); i++) {
//                        JSONObject object = data.getJSONObject(i);
//                        JSONObject metadata = object.getJSONObject("metadata");
//
//                        String date = metadata.getString("publishDate");
//                        String title = metadata.getString("name");
//                        String type = "video";
//                        JSONArray thumbnails = object.getJSONArray("thumbnails");
//                        JSONObject thumb = thumbnails.getJSONObject(0);
//                        String imageUrl = thumb.getString("url");
//                        String url = metadata.getString("url");
//                        dataSource.add(new IGNObject(date, title, type, imageUrl, url));
//                    }
//
//                    IGNAdapter adapter = new IGNAdapter(MainActivity.this, dataSource);
//                    listView.setAdapter(adapter);
//                }
//                catch (final JSONException e) {
//                    Log.d(TAG, e.toString());
//                }
//
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError volleyError) {
//
//            }
//        });
//
//        RequestManager.getInstance(this).addToRequestQueue(videosRequest);

        JsonObjectRequest articlesRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());

                try {
                    JSONArray data =  response.getJSONArray("data");

                    for (int i = 0; i < data.length(); i++) {
                        JSONObject object = data.getJSONObject(i);
                        JSONObject metadata = object.getJSONObject("metadata");

                        String date = metadata.getString("publishDate");
                        String title = metadata.getString("headline");
                        String type = "article";
                        JSONArray thumbnails = object.getJSONArray("thumbnails");
                        JSONObject thumb = thumbnails.getJSONObject(0);
                        String imageUrl = thumb.getString("url");
                        String slug = metadata.getString("slug");
                        String url = articleURL(date, slug);
                        dataSource.add(new IGNObject(date, title, type, imageUrl, url));
                    }

                    IGNAdapter adapter = new IGNAdapter(MainActivity.this, dataSource);
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

        RequestManager.getInstance(this).addToRequestQueue(articlesRequest);

        final Context context = this;
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                IGNObject selectedObject = dataSource.get((position));
                Intent webViewIntent = new Intent(context, WebViewActivity.class);
                webViewIntent.putExtra("title", selectedObject.title);
                webViewIntent.putExtra("url", selectedObject.url);
                startActivity(webViewIntent);
            }
        });
    }

    // MARK: - Utilities
    String articleURL(String dateString, String slug) {
        String baseURL = "http://www.ign.com/articles/";
        String urlDate = baseURL + dateStringToUrlDate(dateString);
        String url = urlDate + slug;
        return url;
    }

    String dateStringToUrlDate(String dateString) {
        String year = new String();
        String month = new String();
        String day = new String();
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
            Date parsedDate = format.parse(dateString);

            SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
            year = yearFormat.format(parsedDate);

            SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
            month = monthFormat.format(parsedDate);

            SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
            day = dayFormat.format(parsedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return year + "/" + month + "/" + day + "/";
    }
}
