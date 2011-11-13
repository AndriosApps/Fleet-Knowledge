package com.andrios.fleetknowledge.Models;

import java.io.Serializable;

import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.Drawable;

public class Aircraft implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8075820847168510914L;
	String function;
	String ac_type;
	String propulsion;
	String performance;
	String size;
	String crew;
	String sensors;
	String armament;

	String current;
	String mission;
	String link;
	int image;
	
	public Aircraft(Cursor c, Context context){
		function = c.getString(1);
		ac_type = c.getString(2);
		propulsion = c.getString(3);
		performance = c.getString(4);
		size = c.getString(5);
		crew = c.getString(6);
		sensors = c.getString(7);
		armament = c.getString(8);
		current = c.getString(9);
		mission = c.getString(10);
		image = context.getResources().getIdentifier(c.getString(11),"drawable", context.getPackageName());
		link = c.getString(12);
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public String getAc_type() {
		return ac_type;
	}

	public void setAc_type(String ac_type) {
		this.ac_type = ac_type;
	}

	public String getPropulsion() {
		return propulsion;
	}

	public void setPropulsion(String propulsion) {
		this.propulsion = propulsion;
	}

	public String getPerformance() {
		return performance;
	}

	public void setPerformance(String performance) {
		this.performance = performance;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getCrew() {
		return crew;
	}

	public void setCrew(String crew) {
		this.crew = crew;
	}

	public String getSensors() {
		return sensors;
	}

	public void setSensors(String sensors) {
		this.sensors = sensors;
	}

	public String getArmament() {
		return armament;
	}

	public void setArmament(String armament) {
		this.armament = armament;
	}

	public String getCurrent() {
		return current;
	}

	public void setCurrent(String current) {
		this.current = current;
	}

	public String getMission() {
		return mission;
	}

	public void setMission(String mission) {
		this.mission = mission;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public int getImage() {
		return image;
	}

	public void setImage(int image) {
		this.image = image;
	}

	
	
}
