package com.example.corey.ignandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String BASE_URL = "http://ign-apis.herokuapp.com/";

    private ListView listView;

    public static final String TAG = "DEBUG TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.ign_list_view);

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
