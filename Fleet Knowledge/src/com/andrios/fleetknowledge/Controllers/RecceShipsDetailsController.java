package com.andrios.fleetknowledge.Controllers;


import com.andrios.fleetknowledge.R;
import com.andrios.fleetknowledge.Models.Ship;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class RecceShipsDetailsController extends Activity {
	

	Ship ship;
	Button newBTN;
	TextView typeLBL;
	TextView classLBL;
	TextView dimensionLBL;
	TextView crewLBL;
	TextView weaponsLBL;
	TextView performanceLBL;
	TextView propulsionLBL;
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.recceshipsdetailsview);
      
		
        getExtras();
		setConnections();
		setOnClickListeners();
    }

	

	private void getExtras() {
		Intent intent = this.getIntent();
		ship = (Ship) intent.getSerializableExtra("ship");
		
	}



	private void setConnections() {
		newBTN = (Button) findViewById(R.id.creedsNewBTN);
		typeLBL = (TextView) findViewById(R.id.recceShipsDetailsViewTypeLBL);
		typeLBL.setText(ship.getType());
		classLBL = (TextView) findViewById(R.id.recceShipsDetailsViewClassLBL);
		classLBL.setText(ship.getShip_class());
		dimensionLBL = (TextView) findViewById(R.id.recceShipsDetailsViewDimensionsLBL);
		dimensionLBL.setText(ship.getDimension());
		crewLBL = (TextView) findViewById(R.id.recceShipsDetailsViewCrewLBL);
		crewLBL.setText(ship.getCrew());
		weaponsLBL = (TextView) findViewById(R.id.recceShipsDetailsViewWeaponsLBL);
		weaponsLBL.setText(ship.getWeapons());
		performanceLBL = (TextView) findViewById(R.id.recceShipsDetailsViewPerformanceLBL);
		performanceLBL.setText(ship.getPerformance());
		propulsionLBL = (TextView) findViewById(R.id.recceShipsDetailsViewPropulsionLBL);
		propulsionLBL.setText(ship.getPropulsion());
	}
	
	
	

	private void setOnClickListeners() {
		
		
	}
	
	

}