package com.pointaeclipseplugin.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.pointaeclipseplugin.model.ParamList;
import com.pointaeclipseplugin.model.ParamMap;
import com.pointaeclipseplugin.model.PointAModel;
import com.pointaeclipseplugin.model.ProviderMetaData;
import com.pointaeclipseplugin.model.constants.MasterProviderInfo.Services;

public enum PointAController {
	INSTANCE;

	// ===========================================================
	// Constants
	// ===========================================================

	static final String LOG_TAG =  PointAController.class.getSimpleName();

	// ===========================================================
	// Fields
	// ===========================================================



	private PointAModel mModel;

	private HashMap<Services, ArrayList<ProviderMetaData>> mProviders;

	private ProviderMetaData mCurrentSelection;
	private ParamList mCurrentSelectionParamList;
	// ===========================================================
	// Constructors
	// ===========================================================

	private PointAController(){
		// Create our model
		mModel = new PointAModel();

		// Get from max pretend is empty for now
		mProviders = mModel.getConfig();

		// Create empty params list to start
		mCurrentSelectionParamList = new ParamList();
	}

	// ===========================================================
	// Methods
	// ===========================================================

	public void onPriorityChanged(int pNewPriority){
		mCurrentSelection.setPriority(pNewPriority);
		System.out.println("Priority changed to: "+pNewPriority);	
	}

	public void onCurrentSelectionChanged(ProviderMetaData newProvider){


		if(mCurrentSelection != null){
			mCurrentSelectionParamList.updateParameters(mCurrentSelection.getParams());
			System.out.println("Moving away from " + mCurrentSelection.getName());
			System.out.println("Current Params selection changed: " + mCurrentSelection.getParams().toString());

		}

		if(newProvider != null){
			System.out.println("Current selection changed to: "+newProvider.getName());	
			mCurrentSelectionParamList.setParameters(newProvider.getParams());
			mCurrentSelection = newProvider;
		}
	}

	public void onEnableChanged(Boolean pEnabled){
		//Cool they enabled it, lets add it to our list
		if(pEnabled){
			mCurrentSelection.setEnabled(true);
			if(	mProviders.get(mCurrentSelection.getType()) == null){
				mProviders.put(mCurrentSelection.getType(), new ArrayList<ProviderMetaData>());
			}
			
			mProviders.get(mCurrentSelection.getType()).add(mCurrentSelection);
		}
		else{
			mCurrentSelection.setEnabled(false);

			mProviders.get(mCurrentSelection.getType()).remove(mCurrentSelection);
		}

		System.out.println("Enable button changed to: "+pEnabled);	
	}


	public List<ParamMap> getCurrentProviderParams() {
		return mCurrentSelectionParamList.getParameters();
	}

	public ProviderMetaData getProvider(String pName){
		// Find Provider in our list and return it
		//	HashMap<Services, List<MasterProviderMeta>> fProvider = MasterProviderInfo.getProviders();

		for (Services lService : Services.values()) {
			List<ProviderMetaData> lProviders = mProviders.get(lService);

			if(lProviders == null){
				break;
			}
			for(ProviderMetaData lProvider : lProviders){
				if(lProvider.getName().equals(pName)){
					System.out.println("Provider already in our config! We will return it");
					return lProvider;
				}
			}
		}
		// We don't currently have an instance of this provider in our current config, so we will create a new one
		// We don't add it right away however, we wait until the user enables it
		return ProviderMetaData.buildEmptyProvider(pName);
	}



	public void onSaveButtonPressed() {
		System.out.println("Save button pressed");		
		System.out.println("We should save the following to the config.xml");

		if(mCurrentSelection != null)
			mCurrentSelectionParamList.updateParameters(mCurrentSelection.getParams());

		for (Services lService : Services.values()) {
			ArrayList<ProviderMetaData> lProviders = mProviders.get(lService);
			System.out.println(lService.name());
			
			if(lProviders == null){
				continue;
			}
			
			for(ProviderMetaData lProvider : lProviders){

				System.out.println("Provider:" + lProvider.getName());
				System.out.println("    Priority:" + lProvider.getPriority());

			}
			System.out.println("---------------------");
		}


		// TODO: Uncomment once model works
		mModel.saveChanges(mProviders);
	}

	public void onRevertButtonPressed() {
		System.out.println("Revert button pressed");		
	}

	public ProviderMetaData getConfigProvider(String lProviderName) {
		// TODO Auto-generated method stub
		return null;
	}



}
