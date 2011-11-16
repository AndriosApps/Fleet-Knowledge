package com.andrios.fleetknowledge.Models;

import java.io.Serializable;

import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.Drawable;

public class Missile implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8075820847168510914L;
	String function;
	String name;
	String price;
	String features;
	String background;
	String description;
	String deploy_date;
	String propulsion;
	String dimensions;
	String performance;
	String warhead;
	String platforms;
	int image;
	String link;
	
	public Missile(Cursor c, Context context){
		link = c.getString(1);
		image = context.getResources().getIdentifier(c.getString(2),"drawable", context.getPackageName());
		 price = c.getString(3);
		 features = c.getString(4);
		 background = c.getString(5);
		 description = c.getString(6);
		 name = c.getString(7);
		 function = c.getString(8);
		 deploy_date = c.getString(9);
		 propulsion = c.getString(10);
		 dimensions = c.getString(11);
		 performance = c.getString(12);
		 warhead = c.getString(13);
		 platforms = c.getString(14);
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getFeatures() {
		return features;
	}

	public void setFeatures(String features) {
		this.features = features;
	}

	public String getBackground() {
		return background;
	}

	public void setBackground(String background) {
		this.background = background;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDeploy_date() {
		return deploy_date;
	}

	public void setDeploy_date(String deploy_date) {
		this.deploy_date = deploy_date;
	}

	public String getPropulsion() {
		return propulsion;
	}

	public void setPropulsion(String propulsion) {
		this.propulsion = propulsion;
	}

	public String getDimensions() {
		return dimensions;
	}

	public void setDimensions(String dimensions) {
		this.dimensions = dimensions;
	}

	public String getPerformance() {
		return performance;
	}

	public void setPerformance(String performance) {
		this.performance = performance;
	}

	public String getWarhead() {
		return warhead;
	}

	public void setWarhead(String warhead) {
		this.warhead = warhead;
	}

	public String getPlatforms() {
		return platforms;
	}

	public void setPlatforms(String platforms) {
		this.platforms = platforms;
	}

	public int getImage() {
		return image;
	}

	public void setImage(int image) {
		this.image = image;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	
	
	
}
