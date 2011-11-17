package com.andrios.fleetknowledge.Database;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class AndriosDatabaseHelper extends SQLiteOpenHelper{
 
    //The Android's default system path of your application database.
    private static String DB_PATH = "/data/data/com.andrios.fleetknowledge/databases/";
 
    private static String DB_NAME = "fleetknowledgeapplicationdata";
    private static int DB_VERSION = 2;
    private SQLiteDatabase myDataBase;
    private SQLiteDatabase tempDataBase;  
 
    private final Context myContext;
    private boolean firstRun = false;
 
    /**
     * Constructor
     * Takes and keeps a reference of the passed context in order to access to the application assets and resources.
     * @param context
     */
    public AndriosDatabaseHelper(Context context) {
 
    	super(context, DB_NAME, null, DB_VERSION);
        this.myContext = context;
    }	
 
  /**
     * Creates a empty database on the system and rewrites it with your own database.
     * */
    public void createDataBase() throws IOException{
    	
    	boolean dbExist = checkDataBase();
 
    	if(dbExist){
    		this.getReadableDatabase();
    	}else{
 
    		//By calling this method and empty database will be created into the default system path
               //of your application so we are gonna be able to overwrite that database with our database.
        	this.getReadableDatabase();
        	try {
 
    			copyDataBase();
 
    		} catch (IOException e) {
 
        		throw new Error("Error copying database");
 
        	}
    	}
 
    }
 
    /**
     * Check if the database already exist to avoid re-copying the file each time you open the application.
     * @return true if it exists, false if it doesn't
     */
    private boolean checkDataBase(){
 
    	SQLiteDatabase checkDB = null;
 
    	try{
    		String myPath = DB_PATH + DB_NAME;
    		checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
    	}catch(SQLiteException e){
 
    		//database does't exist yet.
 
    	}
 
    	if(checkDB != null){
 
    		checkDB.close();
 
    	}
 
    	return checkDB != null ? true : false;
    }
 
    /**
     * Copies your database from your local assets-folder to the just created empty database in the
     * system folder, from where it can be accessed and handled.
     * This is done by transfering bytestream.
     * */
    private void copyDataBase() throws IOException{
    	System.out.println("COPY DATABASE");
    	//Open your local db as the input stream
    	InputStream myInput = myContext.getAssets().open(DB_NAME);
 
    	// Path to the just created empty db
    	String outFileName = DB_PATH + DB_NAME;
 
    	//Open the empty db as the output stream
    	OutputStream myOutput = new FileOutputStream(outFileName);
 
    	//transfer bytes from the inputfile to the outputfile
    	byte[] buffer = new byte[1024];
    	int length;
    	while ((length = myInput.read(buffer))>0){
    		myOutput.write(buffer, 0, length);
    	}
 
    	//Close the streams
    	myOutput.flush();
    	myOutput.close();
    	myInput.close();
    	openDataBase();
    	myDataBase.setVersion(DB_VERSION);
    	myDataBase.close();
    }
    
    private void duplicateDataBase() throws IOException{
    	System.out.println("Duplicate DATABASE");
    	//Open your local db as the input stream
    	InputStream myInput = myContext.getAssets().open(DB_NAME);
 
    	// Path to the just created empty db
    	String outFileName = DB_PATH + "temp";
 
    	//Open the empty db as the output stream
    	OutputStream myOutput = new FileOutputStream(outFileName);
 
    	//transfer bytes from the inputfile to the outputfile
    	byte[] buffer = new byte[1024];
    	int length;
    	while ((length = myInput.read(buffer))>0){
    		myOutput.write(buffer, 0, length);
    	}
 
    	//Close the streams
    	myOutput.flush();
    	myOutput.close();
    	myInput.close();
    }
 
    public void openDataBase() throws SQLException{
 
    	//Open the database
        String myPath = DB_PATH + DB_NAME;
    	myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
 
    }
    
    public void openTempDataBase() throws SQLException{
    	 
    	//Open the database
        String myPath = DB_PATH + "temp";
    	tempDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
 
    }
    
    public SQLiteDatabase openMyDataBase() throws SQLException{
   	 
    	//Open the database
        String myPath = DB_PATH + DB_NAME;
    	return SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
 
    }
 
    @Override
	public synchronized void close() {
 
    	    if(myDataBase != null)
    		    myDataBase.close();
 
    	    
    	    super.close();
 
	}
 
	@Override
	public void onCreate(SQLiteDatabase db) {
		System.out.println("on Create");
		db.setVersion(DB_VERSION);
		System.out.println("DB VERSION: " + db.getVersion());
		
		firstRun = true;
		
	}
 
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		System.out.println("Upgrade From :" + oldVersion + " to: " + newVersion);

		System.out.println("DB VERSION: " + db.getVersion());
		if(oldVersion == 1 && !firstRun){
			// Create a temp Database (With old database)
			try {
				duplicateDataBase();
			} catch (IOException e) {
				e.printStackTrace();
			}

			
			
			openTempDataBase();
			
			
			// SELECT * FROM tempDB.knowledge Table
			Cursor c = tempDataBase.query("knowledge", new String[] {"_id", "description", "subtitle", "title", "body"},  null, null, null, null, null);
			c.moveToFirst();
			
			//Add Knowledge Table
			db.execSQL("CREATE TABLE knowledge (" + 
			"_id INTEGER PRIMARY KEY, " + 
			"description TEXT, "+
			"subtitle TEXT, " + 
			"title TEXT, " +
			"body TEXT);");

			while(c.isAfterLast() == false){
				ContentValues values = new ContentValues();
				values.put("description", c.getString(1));
				values.put("subtitle", c.getString(2));
				values.put("title", c.getString(3));
				values.put("body", c.getString(4));
				db.insert("knowledge", null, values);
				c.moveToNext();
			}
			
			// SELECT * FROM tempDB.missiles Table
			c = tempDataBase.query("missiles", new String[] {"_id", "link", "image", "price", "features", "background", "description", "name", "function", "deploy_date", "propulsion", "dimensions", "performance", "warhead", "platforms"},  null, null, null, null, null);
			c.moveToFirst();
			
			// Add Missiles Table
			db.execSQL("create table missiles (" + 
			"_id INTEGER PRIMARY KEY, " + 
			"link TEXT, "+
			"image TEXT, "+
			"price TEXT, "+
			"features TEXT, " + 
			"background TEXT, " +
			"description TEXT, " +
			"name TEXT, " +
			"function TEXT, " +
			"deploy_date TEXT, " +
			"propulsion TEXT, " +
			"dimensions TEXT, " +
			"performance TEXT, " +
			"warhead TEXT, " +
			"platforms TEXT);");

			while(c.isAfterLast() == false){
				ContentValues values = new ContentValues();
				values.put("link", c.getString(1));
				values.put("image", c.getString(2));
				values.put("price", c.getString(3));
				values.put("features", c.getString(4));
				values.put("background", c.getString(5));
				values.put("description", c.getString(6));
				values.put("name", c.getString(7));
				values.put("function", c.getString(8));
				values.put("deploy_date", c.getString(9));
				values.put("propulsion", c.getString(10));
				values.put("dimensions", c.getString(11));
				values.put("performance", c.getString(12));
				values.put("warhead", c.getString(13));
				values.put("platforms", c.getString(14));
				db.insert("missiles", null, values);
				c.moveToNext();
			}
			
			db.setVersion(2);
			tempDataBase.close();
		}
		
	
			
		// IF oldVersion == 2 && !firstRun	
			
	}
	
	

	
 
        // Add your public helper methods to access and get content from the database.
       // You could return cursors by doing "return myDataBase.query(....)" so it'd be easy
       // to you to create adapters for your views.
 
}