package com.andrios.fleetknowledge.Controllers;

import com.andrios.fleetknowledge.R;
import com.andrios.fleetknowledge.Database.AndriosDatabaseHelper;
import com.google.android.apps.analytics.GoogleAnalyticsTracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;

public class RankController extends Activity {
	

	private static final int ACTIVITY_CREATE = 0;
	private static final int ACTIVITY_EDIT = 1;
	
	Button enlistedBTN;
	Button warrantBTN;
	Button officerBTN;

	AndriosDatabaseHelper helper;
	GoogleAnalyticsTracker tracker;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.rankview);
        
        setConnections();
        setOnClickListeners();  
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

	private void setConnections() {
		enlistedBTN = (Button) findViewById(R.id.rankViewEnlistedBTN);
		warrantBTN = (Button) findViewById(R.id.rankViewWarrantBTN);
		officerBTN = (Button) findViewById(R.id.rankViewOfficerBTN);
		
	}

	private void setOnClickListeners() {
		enlistedBTN.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), RankDetailsController.class);
				intent.putExtra("rank", getString(R.string.title_enlisted));
				startActivity(intent);
				
			}
			
		});
		
		
		
		warrantBTN.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), RankDetailsController.class);

				intent.putExtra("rank", getString(R.string.title_warrant));
				startActivity(intent);
				
			}
			
		});
		
		officerBTN.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), RankDetailsController.class);
				intent.putExtra("rank", getString(R.string.title_officer));
				startActivity(intent);
				
			}
			
		});
		
	}
}