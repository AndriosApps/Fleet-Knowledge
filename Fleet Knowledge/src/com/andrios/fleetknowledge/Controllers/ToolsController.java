package com.andrios.fleetknowledge.Controllers;

import com.andrios.fleetknowledge.R;
import com.andrios.fleetknowledge.R.layout;
import com.google.android.apps.analytics.GoogleAnalyticsTracker;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ToolsController extends Activity {
	
	Button awardsBTN;
	Button pfaBTN;
	Button prtBTN;
	Button bcaBTN;
	GoogleAnalyticsTracker tracker;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.toolsview);
        
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
		awardsBTN = (Button) findViewById(R.id.toolsViewAwardsBTN);
		pfaBTN = (Button) findViewById(R.id.toolsViewPFABTN);
		prtBTN = (Button) findViewById(R.id.toolsViewPRTBTN);
		bcaBTN = (Button) findViewById(R.id.toolsViewBCABTN);
		
	}

	void setOnClickListeners() {
		awardsBTN.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				try{
			        Intent i = new Intent("andrios.android.intent.action.AWARDSMAIN");
			        startActivity(i);
			        }catch(Exception e){
			                Intent i = new Intent(Intent.ACTION_VIEW);
			                i.setData(Uri.parse("market://search?q=pname:com.andrios.militaryawards"));
			                startActivity(i);
			        }
				
			}
			
		});

		pfaBTN.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				try{
		            Intent i = new Intent("andrios.android.intent.action.NAVYMAIN");
		            i.addCategory(Intent.CATEGORY_LAUNCHER);
		            startActivity(i);
		            }catch(Exception e){
		                    Intent i = new Intent(Intent.ACTION_VIEW);
		                    i.setData(Uri.parse("market://search?q=pname:com.andrios.prt"));
		                    startActivity(i);
		            }
				
			}
			
		});

		prtBTN.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				try{
			        Intent i = new Intent("andrios.android.intent.action.NAVYPRT");
			        startActivity(i);
			        }catch(Exception e){
			                Intent i = new Intent(Intent.ACTION_VIEW);
			                i.setData(Uri.parse("market://search?q=pname:com.andrios.militaryawards"));
			                startActivity(i);
			        }
				
			}
			
		});

		bcaBTN.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				try{
			        Intent i = new Intent("andrios.android.intent.action.NAVYBCA");
			        startActivity(i);
			        }catch(Exception e){
			                Intent i = new Intent(Intent.ACTION_VIEW);
			                i.setData(Uri.parse("market://search?q=pname:com.andrios.militaryawards"));
			                startActivity(i);
			        }
				
			}
			
		});
		
	}

	
		
}