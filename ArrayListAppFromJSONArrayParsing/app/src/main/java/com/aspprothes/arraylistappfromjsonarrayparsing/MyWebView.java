package com.aspprothes.arraylistappfromjsonarrayparsing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MyWebView extends AppCompatActivity {
    private WebView webView;
    public static String web_url = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setStatusBarColor(getResources().getColor(R.color.sky));
        this.getWindow().setNavigationBarColor(getResources().getColor(R.color.sky));
        setContentView(R.layout.my_web_view);

        webView = findViewById(R.id.webView);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient());
        webView.loadUrl(web_url);

    }
}