package com.andrios.fleetknowledge.Controllers;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.util.ByteArrayBuffer;

import com.andrios.fleetknowledge.R;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TableRow;
import android.widget.Toast;

public class MusicController extends Activity {
	

	private static String ANCHORS_AWEIGH = "anchors.mp3";
	private static String ANCHORS_AWEIGH_URL = "http://www.navy.mil/navydata/media/anchors.mp3";
	
	private static String MARINE_HYMN = "The Marine's Hymn (Marine Corps).mp3";
	private static String MARINE_HYMN_URL = "http://www.navyband.navy.mil/Anthems/Honors%20Music/Service%20Songs/The%20Marine%27s%20Hymn%20(Marine%20Corps).mp3";

	private static String BLUE_GOLD = "Navy Blue and Gold.mp3";
	private static String BLUE_GOLD_URL = "http://www.navyband.navy.mil/Anthems/Honors%20Music/Service%20Songs/Navy%20Blue%20and%20Gold.MP3";

	private static String AIR_CORPS = "On Navy Wings of Gold (Air Corps).mp3";
	private static String AIR_CORPS_URL = "http://www.navyband.navy.mil/Anthems/Honors%20Music/Service%20Songs/On%20Navy%20Wings%20of%20Gold%20(Air%20Corps).mp3";

	private static String SUBMARINERS = "Take Her Down (Submariners).mp3";
	private static String SUBMARINERS_URL = "http://www.navyband.navy.mil/Anthems/Honors%20Music/Service%20Songs/Take%20Her%20Down%20(Submariners).mp3";

	private static String SEABEES = "The Song of the Seabees.mp3";
	private static String SEABEES_URL = "http://www.navyband.navy.mil/Anthems/Honors%20Music/Service%20Songs/The%20Song%20of%20the%20Seabees.mp3";

	private static String WAVES = "Waves of the Navy.mp3";
	private static String WAVES_URL = "http://www.navyband.navy.mil/Anthems/Honors%20Music/Service%20Songs/Waves%20of%20the%20Navy.mp3";

	private static String DESTROYERMEN = "The Destroyermen.mp3";
	private static String DESTROYERMEN_URL = "http://www.navyband.navy.mil/Anthems/Honors%20Music/Service%20Songs/The%20Destroyermen.mp3";
	
	TableRow anchors_aweighBTN;
	TableRow marine_hymn_BTN;
	TableRow blue_gold_BTN;
	TableRow air_corps_BTN;
	TableRow submariner_BTN;
	TableRow seabees_BTN;
	TableRow waves_BTN;
	TableRow destroyermen_BTN;
	
	 int i = 0;
	
	MediaPlayer mp;
	
	boolean playing = false;
	
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
				if(!playing){
					try{
						open(ANCHORS_AWEIGH, v);
					}catch(Exception e){
						download(ANCHORS_AWEIGH_URL, ANCHORS_AWEIGH);
					}
				}else{
					stopPlay();
				}
				
				 
				
			}
			
		});

		marine_hymn_BTN.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				
				if(!playing){
					try{
						open(MARINE_HYMN, v);
					}catch(Exception e){
						download(MARINE_HYMN_URL, MARINE_HYMN);
					}
				}else{
					stopPlay();
				}
				
				 
				 
				
			}
			
		});

		blue_gold_BTN.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				
				if(!playing){
					try{
						open(BLUE_GOLD, v);
					}catch(Exception e){
						download(BLUE_GOLD_URL, BLUE_GOLD);
					}
				}else{
					stopPlay();
				}
				
				 
				 
				
			}
			
		});

		air_corps_BTN.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				
				if(!playing){
					try{
						open(AIR_CORPS, v);
					}catch(Exception e){
						download(AIR_CORPS_URL, AIR_CORPS);
					}
				}else{
					stopPlay();
				}
				
				 
				 
				
			}
			
		});

		submariner_BTN.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				
				if(!playing){
					try{
						open(SUBMARINERS, v);
					}catch(Exception e){
						download(SUBMARINERS_URL, SUBMARINERS);
					}
				}else{
					stopPlay();
				}
				
				 
				 
				
			}
			
		});

		seabees_BTN.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				if(!playing){
					try{
						open(SEABEES, v);
					}catch(Exception e){
						download(SEABEES_URL, SEABEES);
					}
				}else{
					stopPlay();
				}
				
				 
				 
				
			}
			
		});

		waves_BTN.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				
				if(!playing){
					try{
						open(WAVES, v);
					}catch(Exception e){
						download(WAVES_URL, WAVES);
					}
				}else{
					stopPlay();
				}
				
				 
				 
				
			}
			
		});

		destroyermen_BTN.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				if(!playing){
					try{
						open(DESTROYERMEN, v);
					}catch(Exception e){
						download(DESTROYERMEN_URL, DESTROYERMEN);
					}
				}else{
					stopPlay();
				}
				
				 
				
			}
			
		});
	}
	
	private void stopPlay(){
		mp.stop();
		playing = false;
		anchors_aweighBTN.setBackgroundColor(Color.BLACK);
		marine_hymn_BTN.setBackgroundColor(Color.BLACK);
		blue_gold_BTN.setBackgroundColor(Color.BLACK);
		air_corps_BTN.setBackgroundColor(Color.BLACK);
		submariner_BTN.setBackgroundColor(Color.BLACK);
		seabees_BTN.setBackgroundColor(Color.BLACK);
		waves_BTN.setBackgroundColor(Color.BLACK);
		destroyermen_BTN.setBackgroundColor(Color.BLACK);
	}
	
private void open(String filename, final View v){
		System.out.println("OPEN: " + filename);
		String PATH = Environment.getExternalStorageDirectory()
                + "/download/";
		
		File file = new File(PATH + filename);
		
		
		try{
			stopPlay();
		}catch(Exception e){
			
		}
		
		try{
				Uri path = Uri.fromFile(file);
				mp = MediaPlayer.create(MusicController.this, path);  
				  mp.start();
				  playing = true;
				  v.setBackgroundColor(Color.GREEN);
				  mp.setOnCompletionListener(new OnCompletionListener() {

                       public void onCompletion(MediaPlayer mp) {
                           // TODO Auto-generated method stub
                           mp.release();
                          v.setBackgroundColor(Color.BLACK);
                          playing = false;
                       }

                   });
				}catch(Exception e){
					float f = 1/0;
				}
		
	}

	
	public void download(String DownloadUrl, String fileName) {
		File file = null;
		Toast.makeText(MusicController.this, "Downloading " + fileName + ". Standby:",
					Toast.LENGTH_SHORT).show();
		System.out.println("Downloading");
		try {
	        //set the download URL, a url that points to a file on the internet
	        //this is the file to be downloaded
	        URL url = new URL(DownloadUrl);

	        //create the new connection
	        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

	        //set up some things on the connection
	        urlConnection.setRequestMethod("GET");
	        urlConnection.setDoOutput(true);

	        //and connect!
	        urlConnection.connect();

	        //set the path where we want to save the file
	        //in this case, going to save it on the root directory of the
	        //sd card.
	        File SDCardRoot = Environment.getExternalStorageDirectory();
	        //create a new file, specifying the path, and the filename
	        //which we want to save the file as.
	        file = new File(SDCardRoot,"download/" + fileName);
	        Toast.makeText(MusicController.this, "New file " +file + " has been created",
						Toast.LENGTH_SHORT).show();
	        if(!file.exists()){
	        	  file.createNewFile();
	        	
	        	  System.out.println("");
	        }
	        //this will be used to write the downloaded data into the file we created
	        FileOutputStream fileOutput = new FileOutputStream(file);

	        //this will be used in reading the data from the internet
	        InputStream inputStream = urlConnection.getInputStream();

	        //this is the total size of the file
	        int totalSize = urlConnection.getContentLength();
	        //variable to store total downloaded bytes
	        int downloadedSize = 0;

	        //create a buffer...
	        byte[] buffer = new byte[1024];
	        int bufferLength = 0; //used to store a temporary size of the buffer

	        //now, read through the input buffer and write the contents to the file
	       
	        while ( (bufferLength = inputStream.read(buffer)) > 0 ) {
	                //add the data in the buffer to the file in the file output stream (the file on the sd card
	                fileOutput.write(buffer, 0, bufferLength);
	                //add up the size so we know how much is downloaded
	                downloadedSize += bufferLength;
	                //this is where you would do something to report the prgress, like this maybe
	                //updateProgress(downloadedSize, totalSize);
	                float status = (float) downloadedSize/totalSize;
	                if(i == 0 && status > .25){
	                	Toast.makeText(MusicController.this, "progress update 25%",
	    						Toast.LENGTH_SHORT).show();
	                	i++;
	                }else if(i == 1 && status > .50){
	                	Toast.makeText(MusicController.this, "progress update 50%",
	    						Toast.LENGTH_SHORT).show();
	                	i++;
	                }else if(i == 2 && status > .75){
	                	Toast.makeText(MusicController.this, "progress update 50%",
	    						Toast.LENGTH_SHORT).show();
	                	i++;
	                }else if(i == 3 && status >= 1.0){
	                	Toast.makeText(MusicController.this, "download complete",
	    						Toast.LENGTH_SHORT).show();
	                	
	                }
	                
	        }
	        //close the output stream when done
	        fileOutput.close();

	//catch some possible errors...
	} catch (MalformedURLException e) {
	        e.printStackTrace();
	} catch (Exception e) {
	        e.printStackTrace();
	}
		
	}
	
	public void onPause(){
		super.onPause();
		mp.release();
	}

	
}