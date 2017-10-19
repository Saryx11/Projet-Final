package com.example.benjaminlouis.projetfinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class AideActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aide);
        WebView webView=(WebView)findViewById(R.id.webView);
        webView.loadUrl("https://google.com/");
    }
}
