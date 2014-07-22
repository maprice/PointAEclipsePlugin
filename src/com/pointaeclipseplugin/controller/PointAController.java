package com.pointaeclipseplugin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pointaeclipseplugin.model.MasterProviderDoc;
import com.pointaeclipseplugin.model.PointAModel;
import com.pointaeclipseplugin.model.PointAServiceConstants;
import com.pointaeclipseplugin.model.Provider;
import com.pointaeclipseplugin.model.ProviderMetaData;
import com.pointaeclipseplugin.model.PointAServiceConstants.Services;
import com.pointaeclipseplugin.model.filereader.ConfigSettings;
import com.pointaeclipseplugin.view.PointAView;



/**
 * Contains all meta data on all service providers
 * @version 1.0
 * @since July 08, 2014
 *
 */

public class PointAController {

	// ===========================================================
	// Constants
	// ===========================================================

	static final String LOG_TAG =  PointAController.class.getSimpleName();

	// ===========================================================
	// Fields
	// ===========================================================

	private PointAModel mModel;
	private PointAView mView;
	private ActionListener mButtonListener;

	// ===========================================================
	// Constructors
	// ===========================================================

	public PointAController(PointAModel pModel, PointAView pView){
		mModel = pModel;
		mView = pView;
		
		this.populateView();
		this.setListeners();
	}
	
	// ===========================================================
	// Methods
	// ===========================================================
	public HashMap<Services, ProviderMetaData[]> getMProviders(){
		
		//Get data from MasterProviderDoc
		MasterProviderDoc mpd = new MasterProviderDoc();
		HashMap<Services, List<Provider>> mpdProviders = mpd.getProviders();	
		
		//Create instance of mProviders to be returned
		HashMap<Services, ProviderMetaData[]> mProviders = new HashMap<Services, ProviderMetaData[]>();
		
		int num_providers;
		int num_fields;
		
		//Populate mProviders
		
		//Loop through all Service Types
		for(Services pluginservice:PointAServiceConstants.Services.values()){
			List<Provider> providerList = mpdProviders.get(pluginservice);
			num_providers = providerList.size();
			
			ProviderMetaData[] providermetadata = new ProviderMetaData[num_providers];
			
			//System.out.println("Looking at Service:" + pluginservice.name());
			//Loop through all Service Providers
			for(int i = 0; i < num_providers; i++){	
				Provider provider = providerList.get(i);
				
				//System.out.println("Provider: " + provider.name);
				 
				num_fields = provider.params.size();
				Map<String, String> pParams = new HashMap<String, String>();
				
				pParams.put("Disabled", "1");
				pParams.put("Priority", null);
				
				//Loop through all Service Provider Fields
				for(int j = 0; j < num_fields; j++){
					//System.out.println("Adding Field: " + provider.params.get(j));
					pParams.put(provider.params.get(j), null);
				}	
				providermetadata[i] = new ProviderMetaData(provider.name, pParams);
			}	
			mProviders.put(pluginservice, providermetadata);
		}
		return mProviders;
	}
	

	private void setListeners() {
		 mButtonListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
           	 // Get new config settings from view
           	 ConfigSettings lConfigSettings = null;
           	 
           	 // Tell model to make changes
           	 mModel.saveChanges(lConfigSettings);
            }
      };      
      
      // Add action listener to view
      //mView.getButton().addActionListener(actionListener);  
      
	}

	private void populateView() {
		// Populate view with data from model
	}
	
}