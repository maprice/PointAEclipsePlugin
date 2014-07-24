package com.pointaeclipseplugin.model.constants;

import java.util.ArrayList;
import java.util.List;

import com.pointaeclipseplugin.model.ProviderMetaData;
import com.pointaeclipseplugin.model.constants.MasterProviderInfo.Services;

public class MasterProviderMeta {
	public String name;
	public Services type; // make enum
	public String jarLocation;
	
	public List<String> params;
	
	public MasterProviderMeta(String name, Services type, String jarLocation){
		this.name = name;
		this.type = type;
		this.jarLocation = jarLocation;
		this.params = new ArrayList<String>();
	}

	
}
