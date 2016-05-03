package com.netcosports.studytest;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Шикунец on 14.04.2016.
 */
public class BrowserActivity extends AppCompatActivity {


   // private WebView webView ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.browser_actvity);

        WebView webView = (WebView) findViewById(R.id.webview);

        webView.setWebViewClient(new WebViewClient());
        Uri data = getIntent().getData();
        webView.loadUrl(data.toString());
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("http://tut.by");
    }


    public void onBackPressed() {

        WebView  webView = (WebView) findViewById(R.id.webview);

        if (webView.canGoBack()){
            webView.goBack();
        }
        else {
            super.onBackPressed();
        }
    }
}
