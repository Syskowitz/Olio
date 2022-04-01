package com.example.viikko10;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    WebView web;
    EditText URLInput;
    String prevSite = null;
    String nextSite = null;
    String currSite = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        URLInput = findViewById(R.id.editTextURL);
        web = findViewById(R.id.webBrowser);
        web.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView v, String url) {
                if (v.getProgress() == 100) {
                    if (v.getUrl().equals(currSite) == false) {
                    prevSite = currSite;
                    currSite = v.getUrl();
                    }
                }
            }
        });
        web.getSettings().setJavaScriptEnabled(true);
        web.loadUrl("http://www.satky.fi/kahvi");
        //web.loadUrl("file:///android_asset/index.html");

        URLInput.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if ((keyEvent.getAction() == KeyEvent.ACTION_DOWN) && (i == KeyEvent.KEYCODE_ENTER)){
                    String content = URLInput.getText().toString();
                    if (content.equals("index.html")) {
                        web.loadUrl("file:///android_asset/index.html");
                    } else {
                        web.loadUrl("http://" + content);
                    }
                    return true;
                }
                return false;
            }
        });

    }


    public void shoutOut(View v) {
        web.evaluateJavascript("javascript:shoutOut()", null);
    }

    public void initializeJavaScript(View v) {
        web.evaluateJavascript("javascript:initialize()", null);
    }

    public void goPrevSite(View v) {
        if (prevSite != null) {
            web.loadUrl(prevSite);
            nextSite = currSite;
            prevSite = null;
        }
        //web.goBack();
    }

    public void goNextSite(View v) {
        if (nextSite != null) {
            web.loadUrl(nextSite);
            nextSite = null;
        }
        //web.goForward();
    }

    public void refreshPage(View v) {
        web.loadUrl( web.getUrl() ); // Gaunista
        //web.reload();
    }
}