package com.andrios.fleetknowledge.Controllers;


import com.andrios.fleetknowledge.R;
import com.google.android.apps.analytics.GoogleAnalyticsTracker;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;

public class RecruitersController extends Activity {
	Button questionsBTN;
	Button locationBTN;
	GoogleAnalyticsTracker tracker;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.recruitersview);
        
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
		questionsBTN = (Button) findViewById(R.id.recruitersViewQuestionsBTN);
		locationBTN = (Button) findViewById(R.id.recruitersViewLocationBTN);
		
	}

	private void setOnClickListeners() {
		
		questionsBTN.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), RecruitersQuestionsController.class);
				
				startActivity(intent);
			}
			
		});
		
		locationBTN.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
			    try {
		            final Intent myIntent = new Intent(android.content.Intent.ACTION_VIEW, 
		            		Uri.parse("geo:0,0?q=US Navy Recruiting"));
		            startActivity(myIntent);
		    } catch (Exception e) { }

				
			}
			
		});
		
	}
}