package com.andrios.fleetknowledge.Models;

import java.io.Serializable;

import android.database.Cursor;

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
	
	public Ship(Cursor c){
		type = c.getString(1);
		ship_class = c.getString(2);
		dimension = c.getString(3);
		crew = c.getString(4);
		weapons = c.getString(5);
		performance = c.getString(6);
		propulsion = c.getString(7);
		about = c.getString(8);
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
	
	
}
