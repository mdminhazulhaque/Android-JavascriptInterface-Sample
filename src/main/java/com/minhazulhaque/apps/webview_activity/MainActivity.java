package com.minhazulhaque.apps.webview_activity;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBarActivity;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView webview = (WebView) this.findViewById(R.id.webView);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.loadUrl("file:///android_res/raw/index.html");
        webview.addJavascriptInterface(new JSInterface(this), "JSInterface");
    }

    private void notifyHelper(String text) {
        NotificationManager mNotificationManager;
        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder mNotifyBuilder = new NotificationCompat.Builder(this)
                .setContentTitle("Notification from " + getString(R.string.app_name))
                .setContentText(text)
                .setSmallIcon(R.mipmap.ic_launcher);
        mNotificationManager.notify(0, mNotifyBuilder.build());
    }

    public class JSInterface {
        Context mContext;

        JSInterface(Context c) {
            mContext = c;
        }

        @JavascriptInterface
        public void toastMe(String text) {
            Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show();
        }

        @JavascriptInterface
        public void notifyMe(String text) {
            notifyHelper(text);
        }
    }
}
