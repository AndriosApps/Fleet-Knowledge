package com.andrios.fleetknowledge.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class AndriosDbAdapter {
	//Database Fields
	public static final String KEY_ROWID = "_id";
	public static final String KEY_QUESTION = "question";
	public static final String KEY_ASKED = "isAsked";
	public static final String KEY_ANSWER = "answer";
	public static final String DATABASE_TABLE = "questions";
	public static final String DATABASE_CREEDS_TABLE = "creeds";

	public static final String KEY_TITLE = "title";
	public static final String KEY_BODY = "body";
	public static final String KEY_KNOWN = "isKnown";
	
	
	
	private Context context;
	private SQLiteDatabase database;
	private AndriosDatabaseHelper dbHelper;
	
	public AndriosDbAdapter(Context context){
		this.context = context;
	}
	
	public AndriosDbAdapter open() throws SQLException{
		dbHelper = new AndriosDatabaseHelper(context);
		database = dbHelper.getWritableDatabase();
		return this;
	}
	
	public void close(){
		dbHelper.close();
	}
	
	/**
	 * Create a new crisis information. If the information is sucessfully created
	 * return the new rowID for that set, otherwise return a -1 to indicate failure.
	 */
	public long createQuestion(String question, int isAsked, String answer){
		ContentValues initialValues = createQuestionContentValues(question, isAsked, answer);
		return database.insert(DATABASE_TABLE, null, initialValues);
	}
	
	/**
	 * Update the information
	 */
	public boolean updateQuestion(long rowId, String question, int isAsked, String answer){
		ContentValues updateValues = createQuestionContentValues(question,isAsked, answer);
		return database.update(DATABASE_TABLE, updateValues, KEY_ROWID + "=" + rowId, null) >0;
		
	}
	
	/**
	 * Deletes information
	 */
	public boolean deleteQuestion(long rowId){
		return database.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null)>0;
	}
	
	/**
	 * Return a Cursor over the list of all Information Sets in the database
	 * @return Cursor over all Information Sets
	 */
	public Cursor fetchAllQuestions(){
		return database.query(DATABASE_TABLE, new String[] {KEY_ROWID, KEY_QUESTION, KEY_ASKED, KEY_ANSWER}, null, null, null, null, null);
	}
	
	/**
	 * Return a Cursor positioned at the defined information set
	 */
	public Cursor fetchQuestion(long rowId) throws SQLException{
		Cursor mCursor = database.query(DATABASE_TABLE, new String[] {KEY_ROWID, KEY_QUESTION, KEY_ASKED, KEY_ANSWER}, KEY_ROWID + "=" + rowId, null, null, null, null, null);
		if(mCursor != null){
			mCursor.moveToFirst();
		}
		return mCursor;
	}
	
	private ContentValues createQuestionContentValues(String question, int isAsked, String answer){
		ContentValues values = new ContentValues();
		values.put(KEY_QUESTION, question);
		values.put(KEY_ASKED, isAsked);
		values.put(KEY_ANSWER, answer);
		return values;
	}
	
	
	
	
	
	
	
	
	
	
	// CREEDS
	
	
	/**
	 * Create a new crisis information. If the information is sucessfully created
	 * return the new rowID for that set, otherwise return a -1 to indicate failure.
	 */
	public long createCreed(String title, String body, int isKnown){
		ContentValues initialValues = createCreedContentValues(title, body, isKnown);
		return database.insert(DATABASE_CREEDS_TABLE, null, initialValues);
	}
	
	/**
	 * Update the information
	 */
	public boolean updateCreed(long rowId, String title, String body, int isKnown){
		ContentValues updateValues = createCreedContentValues(title, body, isKnown);
		return database.update(DATABASE_CREEDS_TABLE, updateValues, KEY_ROWID + "=" + rowId, null) >0;
		
	}
	
	/**
	 * Deletes information
	 */
	public boolean deleteCreed(long rowId){
		return database.delete(DATABASE_CREEDS_TABLE, KEY_ROWID + "=" + rowId, null)>0;
	}
	
	/**
	 * Return a Cursor over the list of all Information Sets in the database
	 * @return Cursor over all Information Sets
	 */
	public Cursor fetchAllCreeds(){
		return database.query(DATABASE_CREEDS_TABLE, new String[] {KEY_ROWID, KEY_TITLE, KEY_BODY, KEY_KNOWN}, null, null, null, null, null);
	}
	
	/**
	 * Return a Cursor positioned at the defined information set
	 */
	public Cursor fetchCreed(long rowId) throws SQLException{
		Cursor mCursor = database.query(DATABASE_CREEDS_TABLE, new String[] {KEY_ROWID, KEY_TITLE, KEY_BODY, KEY_KNOWN}, KEY_ROWID + "=" + rowId, null, null, null, null, null);
		if(mCursor != null){
			mCursor.moveToFirst();
		}
		return mCursor;
	}
	
	private ContentValues createCreedContentValues(String title, String body, int isKnown){
		ContentValues values = new ContentValues();
		values.put(KEY_TITLE, title);
		values.put(KEY_BODY, body);
		values.put(KEY_KNOWN, isKnown);
		return values;
	}
	
	
	
	
	// SHIPS
	// SHIPS STATIC VARIABLES
		public static final String DATABASE_SHIPS_TABLE = "ships";

	public static final String KEY_SHIP_TYPE = "type";
	public static final String KEY_SHIP_CLASS = "ship_class";
	public static final String KEY_SHIP_DIMENSIONS = "dimensions";
	public static final String KEY_SHIP_CREW = "crew";
	public static final String KEY_SHIP_WEAPONS = "weapons";
	public static final String KEY_SHIP_PERFORMANCE = "performance";
	public static final String KEY_SHIP_PROPULSION = "propulsion";
	public static final String KEY_SHIP_ABOUT = "about";
		/**
		 * Create a new crisis information. If the information is sucessfully created
		 * return the new rowID for that set, otherwise return a -1 to indicate failure.
		 */
		public long createShip(String type, String ship_class, String dimension, String crew, String weapons, String performance, String propulsion, String about){
			ContentValues initialValues = createShipContentValues(type, ship_class, dimension, crew, weapons, performance, propulsion, about);
			return database.insert(DATABASE_SHIPS_TABLE, null, initialValues);
		}
		
		/**
		 * Update the information
		 */
		public boolean updateShip(long rowId, String type, String ship_class, String dimension, String crew, String weapons, String performance, String propulsion, String about){
			ContentValues updateValues = createShipContentValues(type, ship_class, dimension, crew, weapons, performance, propulsion, about);
			return database.update(DATABASE_SHIPS_TABLE, updateValues, KEY_ROWID + "=" + rowId, null) >0;
			
		}
		
		/**
		 * Deletes information
		 */
		public boolean deleteShip(long rowId){
			return database.delete(DATABASE_SHIPS_TABLE, KEY_ROWID + "=" + rowId, null)>0;
		}
		
		/**
		 * Return a Cursor over the list of all Information Sets in the database
		 * @return Cursor over all Information Sets
		 */
		public Cursor fetchAllShips(){
			return database.query(DATABASE_SHIPS_TABLE, new String[] {KEY_ROWID, KEY_SHIP_TYPE, KEY_SHIP_CLASS, KEY_SHIP_DIMENSIONS, KEY_SHIP_CREW, KEY_SHIP_WEAPONS, KEY_SHIP_PERFORMANCE, KEY_SHIP_PROPULSION, KEY_SHIP_ABOUT}, null, null, null, null, null);
		}
		
		/**
		 * Return a Cursor positioned at the defined information set
		 */
		public Cursor fetchShip(long rowId) throws SQLException{
			Cursor mCursor = database.query(DATABASE_SHIPS_TABLE, new String[] {KEY_ROWID, KEY_SHIP_TYPE, KEY_SHIP_CLASS, KEY_SHIP_DIMENSIONS, KEY_SHIP_CREW, KEY_SHIP_WEAPONS, KEY_SHIP_PERFORMANCE, KEY_SHIP_PROPULSION, KEY_SHIP_ABOUT}, KEY_ROWID + "=" + rowId, null, null, null, null, null);
			if(mCursor != null){
				mCursor.moveToFirst();
			}
			return mCursor;
		}
		
		private ContentValues createShipContentValues(String type, String ship_class, String dimensions, String crew, String weapons, String performance, String propulsion, String about){
			ContentValues values = new ContentValues();
			values.put(KEY_SHIP_TYPE, type);
			values.put(KEY_SHIP_CLASS, ship_class );
			values.put(KEY_SHIP_DIMENSIONS, dimensions);
			values.put(KEY_SHIP_CREW, crew );
			values.put(KEY_SHIP_WEAPONS, weapons );
			values.put(KEY_SHIP_PERFORMANCE, performance );
			values.put(KEY_SHIP_PROPULSION, propulsion);
			values.put(KEY_SHIP_ABOUT, about);
			
			return values;
		}
		
	
	

}
