package com.sc703.proyecto_movil;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityWeb extends AppCompatActivity {
        private String uri = "https://www.inamu.go.cr/web/inamu/inicio";
        WebView webView;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_web);
            webView = findViewById(R.id.web_View);
            webView.setWebViewClient(new WebViewClient());
            WebSettings settings = webView.getSettings();
            settings.setJavaScriptEnabled(true);
            webView.loadUrl(uri);
        }
    }

