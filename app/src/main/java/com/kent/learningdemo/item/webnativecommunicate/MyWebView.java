package com.kent.learningdemo.item.webnativecommunicate;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.ConsoleMessage;
import android.webkit.JavascriptInterface;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.lang.ref.WeakReference;

/**
 * Created by kent on 16/8/4.
 */
public class MyWebView {

    private static final String TAG = "MyWebView";

    private WebView mWebView;
    private WebSettings mWebSetting;
    private WebBridge mWebBridge;

    private WebViewClient mWebViewClient;
    private WebChromeClient mWebChromeClient;

    public MyWebView(WebView mWebView) {

        this.mWebView = mWebView;
        this.mWebSetting = mWebView.getSettings();

        initDefaultSetting();
    }

    private void initDefaultSetting(){
        this.mWebSetting.setJavaScriptEnabled(true);
        this.mWebView.addJavascriptInterface(new WebBridge(this.mWebView), "stub");
        this.initClient();
        this.initChromeClient();
    }

    private void initClient(){
        this.mWebViewClient = new WebViewClient(){
            @Override
            public void onLoadResource(WebView view, String url) {
                Log.e(TAG, "onLoadResource: called in thread:" + Thread.currentThread().getName()
                    +" urllength:" +url.length()+ " url :" + url);
                super.onLoadResource(view, url);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                Log.e(TAG, "onPageFinished: called in thread:" + Thread.currentThread().getName());

                super.onPageFinished(view, url);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                Log.e(TAG, "onPageStarted: called in thread:" + Thread.currentThread().getName());

                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                Log.e(TAG, "onReceivedSslError: called in thread:" + Thread.currentThread().getName());

                super.onReceivedSslError(view, handler, error);
            }

            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                Log.e(TAG, "shouldInterceptRequest: called in thread:" + Thread.currentThread().getName());

                return super.shouldInterceptRequest(view, request);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.e(TAG, "shouldOverrideUrlLoading: called in thread:" + Thread.currentThread().getName()
                    + "url :" + url);

                return false;
            }
        };
        this.mWebView.setWebViewClient(this.mWebViewClient);
    }

    private void initChromeClient(){
        this.mWebChromeClient = new WebChromeClient(){
            @Override
            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                Log.e(TAG, "onConsoleMessage: called in thread:" + Thread.currentThread().getName()
                    + "console msg:" + consoleMessage.message());

                return super.onConsoleMessage(consoleMessage);
            }

            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                Log.e(TAG, "onJsAlert: called in thread:" + Thread.currentThread().getName());

                return super.onJsAlert(view, url, message, result);
            }

            @Override
            public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
                Log.e(TAG, "onJsConfirm: called in thread:" + Thread.currentThread().getName());

                return super.onJsConfirm(view, url, message, result);
            }

            @Override
            public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
                Log.e(TAG, "onJsPrompt: called in thread:" + Thread.currentThread().getName()
                    +" url:"+url+" message:" + message+" defaultValue:"+defaultValue);
                if(!TextUtils.isEmpty(message)){
                    Log.e(TAG, "receive js prompt msg");
                    result.confirm();
                    return true;
                }

                return super.onJsPrompt(view, url, message, defaultValue, result);
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                Log.e(TAG, "onProgressChanged: called in thread:" + Thread.currentThread().getName() +
                        " progress:" + newProgress);

                super.onProgressChanged(view, newProgress);
            }
        };
        this.mWebView.setWebChromeClient(this.mWebChromeClient);
    }

    /**
     * 加载url
     * @param url
     */
    public void loadUrl(String url){
        if(TextUtils.isEmpty(url)){
            return ;
        }
        this.mWebView.loadUrl(url);
    }

    /**
     * 返回上一级
     */
    public void goBack(){
        if(this.mWebView.canGoBack()){
            this.mWebView.goBack();
        }
    }

    /**
     * 注册到js上下文中的java对象
     */
    private static class WebBridge{

        private WeakReference<WebView> mRef = null;

        public WebBridge(WebView webView) {
            this.mRef = new WeakReference<WebView>(webView);
        }


        /**
         * web&native 通信接口
         * @param action
         */
        @JavascriptInterface
        public void command(String action){
            if(this.mRef == null || this.mRef.get() == null ){
                return;
            }
            String actionStr = "";
        }
    }
}
