package com.pointaeclipseplugin.model;

import java.util.ArrayList;
import java.util.List;

import com.pointaeclipseplugin.model.PointAServiceConstants.Services;

public class Provider {
	public String name;
	public Services type; // make enum
	public String jarLocation;
	
	List<String> params;
	
	public Provider(String name, Services type, String jarLocation){
		this.name = name;
		this.type = type;
		this.jarLocation = jarLocation;
		this.params = new ArrayList<String>();
	}	
}
