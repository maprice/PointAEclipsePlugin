package com.pointaeclipseplugin.model;

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

	public void saveChanges(ConfigSettings pNewConfig){
		//Synchronous start
		mConfigManager.updateConfig(pNewConfig);
		mFileBuilderManager.updateFiles(pNewConfig);
		mJarDownloaderManager.updateJars(pNewConfig);
		//Synchronous end
	}
	
	public ConfigSettings getConfig(){
		mConfigManager.parseConfig();
		return mConfigManager.getConfig();
	}
	
}