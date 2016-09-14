package com.kent.learningdemo.item.webnativecommunicate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.StringBuilderPrinter;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import com.kent.learningdemo.R;

public class WebViewActivity extends AppCompatActivity {

    private MyWebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        WebView webView = (WebView) findViewById(R.id.webview);
        this.mWebView = new MyWebView(webView);

//        this.mWebView.loadUrl("http://www.baidu.com");

//        this.mWebView.loadUrl("javascript:execute()");
//

//        StringBuilder builder = new StringBuilder();
//        for(int i = 0;i < 200000;i++){
//            builder.append("asdfasdfefasdfasdfasdfasdfasdfasdfasf");
//        }
        chooseableParamMethodTest();
        String[] s = new String[]{};
        chooseableParamMethodTest(null);
        chooseableParamMethodTest(s);
        this.mWebView.loadUrl("file:/android_asset/test.html");

        Toast.makeText(this, "&#20013;&#22269;", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        if(mWebView != null){
            mWebView.goBack();
        }
        super.onBackPressed();
    }

    private void chooseableParamMethodTest(String... param){
        Log.e("test", "param:" + param.length);
    }
}
