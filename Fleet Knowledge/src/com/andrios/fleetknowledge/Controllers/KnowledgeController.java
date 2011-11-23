package com.andrios.fleetknowledge.Controllers;

import com.andrios.fleetknowledge.R;
import com.andrios.fleetknowledge.Database.AndriosDatabaseHelper;
import com.google.android.apps.analytics.GoogleAnalyticsTracker;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;

public class KnowledgeController extends Activity {
	

	private static final int ACTIVITY_CREATE = 0;
	private static final int ACTIVITY_EDIT = 1;
	
	Button generalOrdersBTN;
	Button creedsBTN;
	Button ranksBTN;
	Button historyBTN;
	Button abbrBTN;
	Button cocBTN;
	
	AndriosDatabaseHelper helper;
	GoogleAnalyticsTracker tracker;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.knowledgeview);
        
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
		generalOrdersBTN = (Button) findViewById(R.id.knowledgeViewGeneralOrdersBTN);
		creedsBTN = (Button) findViewById(R.id.knowledgeViewCreedsBTN);
		ranksBTN = (Button) findViewById(R.id.knowledgeViewRanksBTN);
		historyBTN = (Button) findViewById(R.id.knowledgeViewHistoryBTN);
		abbrBTN = (Button) findViewById(R.id.knowledgeViewAbbrBTN);
		//cocBTN = (Button) findViewById(R.id.knowledgeViewCocBTN);
		
	}

	private void setOnClickListeners() {

		/*cocBTN.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.setData(Uri.parse("http://www.history.navy.mil/books/OPNAV20-P1000/A.htm"));
				startActivity(intent);
				
			}
			
		});*/
		
		abbrBTN.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.setData(Uri.parse("http://www.history.navy.mil/books/OPNAV20-P1000/A.htm"));
				startActivity(intent);
				
			}
			
		});
		
		historyBTN.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.setData(Uri.parse("http://www.history.navy.mil/"));
				startActivity(intent);
				
			}
			
		});
		
		
		generalOrdersBTN.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), KnowledgeDetailsController.class);
				intent.putExtra("header", getString(R.string.title_general_orders));
				intent.putExtra("body", getString(R.string.general_orders));
				startActivity(intent);
				
			}
			
		});
		
		
		
		creedsBTN.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), CreedsController.class);
				
				startActivity(intent);
				
			}
			
		});
		
		ranksBTN.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), RankController.class);
				
				startActivity(intent);
				
			}
			
		});
		
		
		
	}
}