package com.andrios.fleetknowledge.Controllers;


import com.andrios.fleetknowledge.R;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TableRow;

public class MusicController extends Activity {
	
	private static String ANCHORS_AWEIGH_URL = "http://www.navy.mil/navydata/media/anchors.mp3";
	private static String MARINE_HYMN_URL = "http://www.navyband.navy.mil/Anthems/Honors%20Music/Service%20Songs/The%20Marine%27s%20Hymn%20(Marine%20Corps).mp3";

	private static String BLUE_GOLD_URL = "http://www.navyband.navy.mil/Anthems/Honors%20Music/Service%20Songs/Navy%20Blue%20and%20Gold.MP3";
	private static String AIR_CORPS_URL = "http://www.navyband.navy.mil/Anthems/Honors%20Music/Service%20Songs/On%20Navy%20Wings%20of%20Gold%20(Air%20Corps).mp3";
	private static String SUBMARINERS_URL = "http://www.navyband.navy.mil/Anthems/Honors%20Music/Service%20Songs/Take%20Her%20Down%20(Submariners).mp3";
	private static String SEABEES_URL = "http://www.navyband.navy.mil/Anthems/Honors%20Music/Service%20Songs/The%20Song%20of%20the%20Seabees.mp3";
	private static String WAVES_URL = "http://www.navyband.navy.mil/Anthems/Honors%20Music/Service%20Songs/Waves%20of%20the%20Navy.mp3";
	private static String DESTROYERMEN_URL = "http://www.navyband.navy.mil/Anthems/Honors%20Music/Service%20Songs/The%20Destroyermen.mp3";
	
	TableRow anchors_aweighBTN;
	TableRow marine_hymn_BTN;
	TableRow blue_gold_BTN;
	TableRow air_corps_BTN;
	TableRow submariner_BTN;
	TableRow seabees_BTN;
	TableRow waves_BTN;
	TableRow destroyermen_BTN;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.musicview);
        
        setConnections();
        setOnClickListeners();
    }

	private void setConnections() {
		anchors_aweighBTN = (TableRow) findViewById(R.id.musicViewAnchorsAweighTableRow);
		marine_hymn_BTN = (TableRow) findViewById(R.id.musicViewMarineHymnTableRow);
		blue_gold_BTN = (TableRow) findViewById(R.id.musicViewBlueGoldTableRow);
		air_corps_BTN = (TableRow) findViewById(R.id.musicViewAirCorpsTableRow);
		submariner_BTN = (TableRow) findViewById(R.id.musicViewSubmarinersTableRow);
		seabees_BTN = (TableRow) findViewById(R.id.musicViewSeabeesTableRow);
		waves_BTN = (TableRow) findViewById(R.id.musicViewWavesTableRow);
		destroyermen_BTN = (TableRow) findViewById(R.id.musicViewDestroyermenTableRow);
		
	}

	private void setOnClickListeners() {
		anchors_aweighBTN.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				
					 Intent browserIntent = new Intent("android.intent.action.VIEW", Uri.parse(ANCHORS_AWEIGH_URL));
					startActivity(browserIntent);
				 
				
			}
			
		});

		marine_hymn_BTN.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				
					 Intent browserIntent = new Intent("android.intent.action.VIEW", Uri.parse(MARINE_HYMN_URL));
					startActivity(browserIntent);
				 
				
			}
			
		});

		blue_gold_BTN.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				
					 Intent browserIntent = new Intent("android.intent.action.VIEW", Uri.parse(BLUE_GOLD_URL));
					startActivity(browserIntent);
				 
				
			}
			
		});

		air_corps_BTN.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				
					 Intent browserIntent = new Intent("android.intent.action.VIEW", Uri.parse(AIR_CORPS_URL));
					startActivity(browserIntent);
				 
				
			}
			
		});

		submariner_BTN.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				
					 Intent browserIntent = new Intent("android.intent.action.VIEW", Uri.parse(SUBMARINERS_URL));
					startActivity(browserIntent);
				 
				
			}
			
		});

		seabees_BTN.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				
					 Intent browserIntent = new Intent("android.intent.action.VIEW", Uri.parse(SEABEES_URL));
					startActivity(browserIntent);
				 
				
			}
			
		});

		waves_BTN.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				
					 Intent browserIntent = new Intent("android.intent.action.VIEW", Uri.parse(WAVES_URL));
					startActivity(browserIntent);
				 
				
			}
			
		});

		destroyermen_BTN.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				
					 Intent browserIntent = new Intent("android.intent.action.VIEW", Uri.parse(DESTROYERMEN_URL));
					startActivity(browserIntent);
				 
				
			}
			
		});
	}
	

}