package com.andrios.fleetknowledge.Models;

import java.io.Serializable;

import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.Drawable;

public class Ship implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8075820847168510914L;
	String type;
	String ship_class;
	String dimension;
	String crew;
	String weapons;
	String performance;
	String propulsion;

	String about;
	String aircraft;
	String ew;
	String sensors;
	String boats;
	int image;
	
	public Ship(Cursor c, Context context){
		type = c.getString(1);
		ship_class = c.getString(2);
		dimension = c.getString(3);
		crew = c.getString(4);
		weapons = c.getString(5);
		performance = c.getString(6);
		propulsion = c.getString(7);
		aircraft = c.getString(8);
		ew = c.getString(9);
		sensors = c.getString(10);
		boats = c.getString(11);
		about = c.getString(12);
		image = context.getResources().getIdentifier(c.getString(13),"drawable", context.getPackageName());
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getShip_class() {
		return ship_class;
	}

	public void setShip_class(String ship_class) {
		this.ship_class = ship_class;
	}

	public String getDimension() {
		return dimension;
	}

	public void setDimension(String dimension) {
		this.dimension = dimension;
	}

	public String getCrew() {
		return crew;
	}

	public void setCrew(String crew) {
		this.crew = crew;
	}

	public String getWeapons() {
		return weapons;
	}

	public void setWeapons(String weapons) {
		this.weapons = weapons;
	}

	public String getPerformance() {
		return performance;
	}

	public void setPerformance(String performance) {
		this.performance = performance;
	}

	public String getPropulsion() {
		return propulsion;
	}

	public void setPropulsion(String propulsion) {
		this.propulsion = propulsion;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getAircraft() {
		return aircraft;
	}

	public void setAircraft(String aircraft) {
		this.aircraft = aircraft;
	}

	public String getEw() {
		return ew;
	}

	public void setEw(String ew) {
		this.ew = ew;
	}

	public String getSensors() {
		return sensors;
	}

	public void setSensors(String sensors) {
		this.sensors = sensors;
	}

	public String getBoats() {
		return boats;
	}

	public void setBoats(String boats) {
		this.boats = boats;
	}

	public int getImage() {
		return image;
	}

	public void setImage(int image) {
		this.image = image;
	}
	
	
}
