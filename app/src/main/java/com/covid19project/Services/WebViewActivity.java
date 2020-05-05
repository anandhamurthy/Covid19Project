package com.covid19project.Services;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;

import com.covid19project.MainActivity;
import com.covid19project.R;

public class WebViewActivity extends AppCompatActivity {

    private WebView webView;
    private ImageView Back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        Intent intent = getIntent();
        String link = intent.getStringExtra("link");

        Back = findViewById(R.id.toolbar_icon);
        webView = findViewById(R.id.web_view);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WebViewActivity.this, MainActivity.class));
                finish();
            }
        });

        WebViewClientService webViewClient = new WebViewClientService(WebViewActivity.this);
        webView.setWebViewClient(webViewClient);

        webView.loadUrl(link);


    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}