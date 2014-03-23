package com.example.com.vsshs.webview;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Settings extends Activity {
	public static final String PREFS_NAME = "SmartPortalApp";
	public Context ctx;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);

		EditText rfid = (EditText) findViewById(R.id.txt_rfid);
		EditText url = (EditText) findViewById(R.id.txt_url);
		
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		rfid.setText(settings.getString("rfid", ""));
		url.setText(settings.getString("url", ""));
		
		
		ctx = this;
		Button btnSave = (Button) findViewById(R.id.button1);
		btnSave.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
				
				EditText rfid = (EditText) findViewById(R.id.txt_rfid);
				EditText url = (EditText) findViewById(R.id.txt_url);
				Editor e = settings.edit();
				e.putString("rfid", rfid.getText().toString());
				e.putString("url", url.getText().toString());
				e.commit();
				
				Intent intent = new Intent(ctx, SmartPortalActivity.class);
				startActivity(intent);
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.settings, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	
}
