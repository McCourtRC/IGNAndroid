package com.example.corey.ignandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.corey.ignandroid.models.IGNArticlesResponse;
import com.example.corey.ignandroid.models.IGNData;
import com.example.corey.ignandroid.models.IGNObject;
import com.example.corey.ignandroid.models.IGNVideosResponse;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.Observable;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
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

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create());

        Retrofit retrofit = builder.build();

        IGNClient ignClient = retrofit.create(IGNClient.class);
        Observable<IGNVideosResponse> callVideos = ignClient.getVideos(0,0);
        Observable<IGNArticlesResponse> callArticles = ignClient.getArticles(0,0);

        callVideos.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<IGNVideosResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull IGNVideosResponse response) {
                        // Build relevant IGN Object List
                        List<IGNData> data = response.getData();
                        List<IGNObject> relevantData = new ArrayList<IGNObject>();

                        for (int i = 0; i < data.size(); i++) {
                            IGNData d = data.get(i);
                            String date = d.getMetadata().getPublishDate();
                            String title = d.getMetadata().getName();
                            String type = "video";
                            String imageUrl = d.getThumbnails().get(1).getUrl();
                            String url = d.getMetadata().getUrl();
                            IGNObject obj = new IGNObject(date, title, type, imageUrl, url);
                            relevantData.add(obj);
                        }

                        IGNAdapter adapter = new IGNAdapter(MainActivity.this, relevantData);
                        listView.setAdapter(adapter);

                        Log.d(TAG, "Success");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(TAG, "Async Error");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
