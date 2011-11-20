package com.andrios.fleetknowledge.Controllers;

import com.andrios.fleetknowledge.R;
import com.andrios.fleetknowledge.Database.AndriosDatabaseHelper;
import com.google.android.apps.analytics.GoogleAnalyticsTracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

public class RankDetailsController extends Activity {
	
	TextView bodyTXT;
	
	String title;
	
	AndriosDatabaseHelper helper;
	GoogleAnalyticsTracker tracker;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        

        
        getExtras();
        if(title.equals(getString(R.string.title_enlisted))){
        	System.out.println("Setting enlisted View");
            setContentView(R.layout.rank_enlisted_view);
        }else if(title.equals(getString(R.string.title_warrant))){
        	System.out.println("Setting enlisted View");
            setContentView(R.layout.rank_warrant_view);
        	
        }else if(title.equals(getString(R.string.title_officer))){
        	System.out.println("Setting enlisted View");
            setContentView(R.layout.rank_officer_view);
        	
        }
       
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
   		title = intent.getStringExtra("rank");
   	}


}