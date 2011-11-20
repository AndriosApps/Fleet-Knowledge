package com.andrios.fleetknowledge.Controllers;

import com.andrios.fleetknowledge.R;
import com.andrios.fleetknowledge.Database.AndriosDatabaseHelper;
import com.andrios.fleetknowledge.Models.Missile;
import com.google.android.apps.analytics.GoogleAnalyticsTracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class KnowledgeDetailsController extends Activity {
	
	TextView bodyTXT;
	TextView headerTXT;
	
	AndriosDatabaseHelper helper;
	GoogleAnalyticsTracker tracker;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.knowledgedetailsview);
        
        setConnections();
        setOnClickListeners(); 
        getExtras();
        setTracker();
    }
  
   	private void setTracker() {
   		tracker = GoogleAnalyticsTracker.getInstance();
   		tracker.start(this.getString(R.string.ga_api_key),
   				getApplicationContext());
   	}

   	@Override
   	public void onResume() {
   		super.onResume();
   		tracker.trackPageView("/" + this.getLocalClassName());
   	}

   	@Override
   	public void onPause() {
   		super.onPause();
   		tracker.dispatch();
   	}
   	
   	private void getExtras(){
   		Intent intent = this.getIntent();
		headerTXT.setText(intent.getStringExtra("header"));
		bodyTXT.setText(intent.getStringExtra("body"));
   	}

	private void setConnections() {
		headerTXT = (TextView) findViewById(R.id.knowledgeDetailsViewHeaderTXT);
		bodyTXT = (TextView) findViewById(R.id.knowledgeDetailsViewBodyTXT);
		
		
	}

	private void setOnClickListeners() {
		
		
	}
}