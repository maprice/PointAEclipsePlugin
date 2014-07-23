package com.PointA.model.server;

import java.util.ArrayList;
import java.util.List;

import com.PointA.model.server.MasterProviderInfo.Services;

public class MasterProvider {
	public String name;
	public Services type; // make enum
	public String jarLocation;
	
	public List<String> params;
	
	public MasterProvider(String name, Services type, String jarLocation){
		this.name = name;
		this.type = type;
		this.jarLocation = jarLocation;
		this.params = new ArrayList<String>();
	}	
}
