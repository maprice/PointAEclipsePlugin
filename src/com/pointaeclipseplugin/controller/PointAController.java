package com.pointaeclipseplugin.controller;

import java.security.Provider.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pointaeclipseplugin.model.ParamList;
import com.pointaeclipseplugin.model.ProviderMetaData;
import com.pointaeclipseplugin.model.ProviderParamSet;
import com.pointaeclipseplugin.model.constants.MasterProviderInfo;
import com.pointaeclipseplugin.model.constants.MasterProviderInfo.Services;
import com.pointaeclipseplugin.model.constants.MasterProviderMeta;

public enum PointAController {
	INSTANCE;

	// ===========================================================
	// Constants
	// ===========================================================

	static final String LOG_TAG =  PointAController.class.getSimpleName();

	// ===========================================================
	// Fields
	// ===========================================================

	private ParamList mModel;

	private HashMap<Service, List<ProviderMetaData>> mProviders;
	private ProviderMetaData currentSelection;

	// ===========================================================
	// Constructors
	// ===========================================================

	private PointAController(){
		mModel = new ParamList();
		// Get from max
		mProviders = new HashMap<Service, List<ProviderMetaData>>();
	}

	// ===========================================================
	// Methods
	// ===========================================================

	public void onPriorityChanged(int pNewPriority){
		System.out.println("Priority changed to: "+pNewPriority);	
	}

	public void onCurrentSelectionChanged(String pNewSelection){
		System.out.println("Current selection changed to: "+pNewSelection);	

		ProviderMetaData newProvider = getProvider(pNewSelection);

		if(newProvider != null)
			mModel.setPersons(newProvider.getParams());

	}

	public void onEnableChanged(Boolean pEnabled){
		System.out.println("Enable button changed to: "+pEnabled);	
	}


	public List<ProviderParamSet> getCurrentProviderParams() {
		return mModel.getPersons();
	}

	public ProviderMetaData getProvider(String pName){
		// Find Provider in our list and return it
		//	HashMap<Services, List<MasterProviderMeta>> fProvider = MasterProviderInfo.getProviders();

		for (Services lService : Services.values()) {
			List<ProviderMetaData> lProviders = mProviders.get(lService);

			if(lProviders == null || lProviders.isEmpty()){
				break;
			}
			for(ProviderMetaData lProvider : lProviders){
				if(lProvider.getName().equals(pName)){
					return lProvider;
				}
			}
		}
		// We don't currently have an instance of this provider in our current config, so we will create a new one
		// We don't add it right away however, we wait until the user enables it
		return buildEmptyProvider(pName);
	}

	private ProviderMetaData buildEmptyProvider(String pName) {
		HashMap<Services, List<MasterProviderMeta>> fProvider = MasterProviderInfo.getProviders();

		for (Services lService : Services.values()) {
			List<MasterProviderMeta> lProviders = fProvider.get(lService);

			for(MasterProviderMeta lProvider : lProviders){
				if(lProvider.name.equals(pName)){
					// Create the new meta object
					Map<String,String> newParams = new HashMap<String, String>();

					for(String paramKey : lProvider.params){
						newParams.put(paramKey, "");
					}

					ProviderMetaData newProvider = new ProviderMetaData(pName, newParams);
					return newProvider;
				}
			}
		}
		// Throw exception
		// This means user has messed up config
		return null;

	}

	public void onSaveButtonPressed() {
		System.out.println("Save button pressed");		
	}

	public void onRevertButtonPressed() {
		System.out.println("Revert button pressed");		
	}

	public ProviderMetaData getConfigProvider(String lProviderName) {
		// TODO Auto-generated method stub
		return null;
	}



}
