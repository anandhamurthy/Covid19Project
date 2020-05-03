package com.covid19project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

import com.covid19project.Services.WebViewClientService;

public class TweetsActivity extends AppCompatActivity {

    private  WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweets);

        Intent intent = getIntent();
        String url = intent.getStringExtra("url");

        webView = findViewById(R.id.web_view);
        webView.setWebViewClient(new WebViewClientService(this));
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadData(url,"text/html; charset=utf-8", "utf-8");
    }
}
