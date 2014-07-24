package com.pointaeclipseplugin.model.filereader;

import java.util.ArrayList;
import java.util.HashMap;

import com.pointaeclipseplugin.model.ProviderMetaData;
import com.pointaeclipseplugin.model.constants.MasterProviderInfo.Services;



/**
 * <Class Description>
 * @version 1.0
 * @since July 08, 2014
 *
 */

public class ConfigManager {

	// ===========================================================
	// Constants
	// ===========================================================

	static final String LOG_TAG =  ConfigManager.class.getSimpleName();

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================



	public void parseConfig() {
		// TODO Auto-generated method stub

	}

	public void updateConfig(HashMap<Services, ArrayList<ProviderMetaData>> mProviders) {
		// TODO Auto-generated method stub

	}

	public HashMap<Services, ArrayList<ProviderMetaData>> getConfig(){
		
		// just returning non-empty config so i dont get errors
		HashMap<Services, ArrayList<ProviderMetaData>> pretendConfig = new HashMap<Services, ArrayList<ProviderMetaData>>();

		for (Services dir : Services.values()) {
			ArrayList<ProviderMetaData> serviceList = new ArrayList<ProviderMetaData>();

			pretendConfig.put(dir, serviceList);
		}

		return pretendConfig;
	}




}