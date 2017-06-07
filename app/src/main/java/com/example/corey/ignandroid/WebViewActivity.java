package com.example.corey.ignandroid;

import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

public class WebViewActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        String title = this.getIntent().getExtras().getString("title");
        String url = this.getIntent().getExtras().getString("url");

        setTitle(title);

        Log.d("URL", url);

        webView = (WebView) findViewById(R.id.activity_web_view);
        webView.loadUrl(url);
    }
}
