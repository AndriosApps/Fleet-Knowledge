package com.andrios.fleetknowledge.Controllers;

import com.andrios.fleetknowledge.R;
import com.andrios.fleetknowledge.Database.AndriosDatabaseHelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;

public class FleetKnowledgeActivity extends Activity {
	

	private static final int ACTIVITY_CREATE = 0;
	private static final int ACTIVITY_EDIT = 1;
	
	Button recruitersBTN;
	Button recceBTN;
	Button musicBTN;
	Button creedsBTN;
	Button toolsBTN;
	AndriosDatabaseHelper helper;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        helper = new AndriosDatabaseHelper(this);
        try{
        	System.out.println("CREATE DATABASE");
        	helper.createDataBase();
        }catch(Exception e){
        	e.printStackTrace();
        }
        setConnections();
        setOnClickListeners();
    }

	private void setConnections() {
		recruitersBTN = (Button) findViewById(R.id.mainViewRecruiterBTN);
		recceBTN = (Button) findViewById(R.id.mainViewRecceBTN);
		musicBTN = (Button) findViewById(R.id.mainViewMusicBTN);
		creedsBTN = (Button) findViewById(R.id.mainViewCreedsBTN);
		toolsBTN = (Button) findViewById(R.id.mainViewToolsBTN);
		
	}

	private void setOnClickListeners() {
		recruitersBTN.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), RecruitersController.class);
				
				startActivity(intent);
				
			}
			
		});
		
		recceBTN.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), RecceController.class);
				
				startActivity(intent);
				
			}
			
		});
		
		musicBTN.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), MusicController.class);
				
				startActivity(intent);
				
			}
			
		});
		
		creedsBTN.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), CreedsController.class);
				
				startActivity(intent);
				
			}
			
		});
		
		toolsBTN.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), ToolsController.class);
				
				startActivity(intent);
				
			}
			
		});
		
	}
}