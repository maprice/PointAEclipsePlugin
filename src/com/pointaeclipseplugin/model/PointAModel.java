package com.pointaeclipseplugin.model;

import java.util.HashMap;
import java.util.List;

import com.pointaeclipseplugin.model.constants.MasterProviderInfo.Services;
import com.pointaeclipseplugin.model.filebuilder.FileBuilderManager;
import com.pointaeclipseplugin.model.filereader.ConfigManager;
import com.pointaeclipseplugin.model.filereader.ConfigSettings;
import com.pointaeclipseplugin.model.jar.JarDownloaderManager;


/**
 * Main model used to control all back-end functionality
 * @version 1.0
 * @since July 08, 2014
 *
 */

public class PointAModel {

	// ===========================================================
	// Constants
	// ===========================================================

	static final String LOG_TAG =  PointAModel.class.getSimpleName();

	// ===========================================================
	// Fields
	// ===========================================================

	private ConfigManager mConfigManager;
	private FileBuilderManager mFileBuilderManager;
	private JarDownloaderManager mJarDownloaderManager;

	// ===========================================================
	// Constructors
	// ===========================================================

	public PointAModel(){
		mConfigManager = new ConfigManager();
		mFileBuilderManager = new FileBuilderManager();
		mJarDownloaderManager = new JarDownloaderManager();
	}
	
	// ===========================================================
	// Methods
	// ===========================================================

	public void saveChanges(HashMap<Services, List<ProviderMetaData>> mProviders){
		//Synchronous start
		mConfigManager.updateConfig(mProviders);
		//mFileBuilderManager.updateFiles(mProviders); <-- Currently broken
		mJarDownloaderManager.updateJars(mProviders);
		//Synchronous end
	}
	
	public HashMap<Services, List<ProviderMetaData>> getConfig(){
		mConfigManager.parseConfig();
		return mConfigManager.getConfig();
	}
	
}