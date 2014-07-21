package com.pointaeclipseplugin.model;

import java.util.ArrayList;

public class MasterProviderDoc {
	
	
	// Could have a list of all providers,  OR a hashmap with key = serviceType value = ArrayList<Providers> (Better) 
	final ArrayList<Provider> providerMasterList;
	
	
	public MasterProviderDoc(){
		providerMasterList = new ArrayList<Provider>();
		
		//Populate providerMasterList
		// Will be populated via JSON from server in future, but for now just hardcode
		
		
		// Create admob
		Provider admob = new Provider();
		// Populate admob data
		admob.name = "admob";
		
		
		// Add admob to list
		providerMasterList.add(admob);
		
		//Create other stuff..........
		
	}
	
	// UI reads me
	public ArrayList<Provider> getProviders(){
		return providerMasterList;
	}
}
