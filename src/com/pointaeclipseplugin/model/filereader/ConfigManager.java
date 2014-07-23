package com.pointaeclipseplugin.model.filereader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pointaeclipseplugin.model.ProviderMetaData;
import com.pointaeclipseplugin.model.constants.MasterProviderInfo;
import com.pointaeclipseplugin.model.constants.MasterProviderInfo.Services;
import com.pointaeclipseplugin.model.constants.MasterProviderMeta;



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

	public void updateConfig(HashMap<Services, List<ProviderMetaData>> mProviders) {
		// TODO Auto-generated method stub

	}

	public HashMap<Services, List<ProviderMetaData>> getConfig(){
		
		// just returning non-empty config so i dont get errors
		HashMap<Services, List<ProviderMetaData>> pretendConfig = new HashMap<Services, List<ProviderMetaData>>();

		for (Services dir : Services.values()) {
			List<ProviderMetaData> serviceList = new ArrayList<ProviderMetaData>();

			pretendConfig.put(dir, serviceList);
		}

		return pretendConfig;
	}




}