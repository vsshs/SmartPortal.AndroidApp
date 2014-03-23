package com.example.com.vsshs.webview;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class SmartPortalActivity extends Activity {

	private String rfid = "";
	private Context ctx;
	WebView myWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smart_portal);
        
        ctx = this;
        SharedPreferences settings = getSharedPreferences(Settings.PREFS_NAME, 0);
        
        rfid = settings.getString("rfid", "");
        String url = settings.getString("url", "http://192.168.0.67");
        
        myWebView = (WebView) findViewById(R.id.webview);
        myWebView.addJavascriptInterface(new WebAppInterface(this), "Android");
        myWebView.setWebViewClient(new MyWebViewClient());
        myWebView.loadUrl(url);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true); 
        
        Log.d("", "Init complete " + url);
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.smart_portal, menu);
        return true;
    }
    
    @Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.menu_settings) {
			Intent intent = new Intent(this, Settings.class);
			startActivity(intent);
			return true;
		}
		
		if (id == R.id.menu_reloadPage) {
			myWebView.reload();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
    
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(event.getAction() == KeyEvent.ACTION_DOWN){
            switch(keyCode)
            {
            case KeyEvent.KEYCODE_BACK:
                if(myWebView.canGoBack()){
                    myWebView.goBack();
                }else{
                   finish();
                }
                return true;
            }

        }
        return super.onKeyDown(keyCode, event);
    }
    public class WebAppInterface {
        Context mContext;

        /** Instantiate the interface and set the context */
        WebAppInterface(Context c) {
            mContext = c;
        }
        
        @JavascriptInterface
        public String getRfidTag()
        {
        	return rfid;
        }
    }
   
    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            
        	return false;
        
        }
        
       
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {

            ///Toast.makeText(ctx, "Loading " + url, Toast.LENGTH_SHORT).show();
        }
    }
}
