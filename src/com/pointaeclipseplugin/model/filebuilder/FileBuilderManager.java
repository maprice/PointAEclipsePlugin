package com.pointaeclipseplugin.model.filebuilder;

import java.util.HashMap;
import java.util.List;

import com.pointaeclipseplugin.model.ProviderMetaData;
import com.pointaeclipseplugin.model.constants.FileConstants;
import com.pointaeclipseplugin.model.constants.MasterProviderInfo.Services;
import com.pointaeclipseplugin.model.filereader.ConfigSettings;
import com.pointaeclipseplugin.model.filewriter.WritableFile;



/**
 * <Class Description>
 * @version 1.0
 * @since July 08, 2014
 *
 */

public class FileBuilderManager {

	// ===========================================================
	// Constants
	// ===========================================================

	static final String LOG_TAG =  FileBuilderManager.class.getSimpleName();

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	public void updateFiles(HashMap<Services, List<ProviderMetaData>> mProviders) {
		WritableFile lManifest, lClassPath, lConfig;

		lManifest = buildManifest(mProviders);
		lClassPath = buildClassPath(mProviders);
		lConfig = buildConfig(mProviders);

		// Synchronous start
		lManifest.writeToFile(FileConstants.MANIFEST_PATH);
		lClassPath.writeToFile(FileConstants.CLASSPATH_PATH);
		lConfig.writeToFile(FileConstants.CONFIG_PATH);
		// Synchronous end

	}

	private WritableFile buildManifest(HashMap<Services, List<ProviderMetaData>> mProviders){
		ManifestBuilder mManifestBuilder = new ManifestBuilder();

		mManifestBuilder.addPreInject(FileConstants.MANIFEST_HEADER);

		//Read from config to get permission list
		int totalPermission = 10;
		for(int i = 0; i < totalPermission; i++)
			mManifestBuilder.addInject("PermissionName");

		mManifestBuilder.addPostInject(FileConstants.MANIFEST_FOOTER);

		return mManifestBuilder.getFile();
	}

	private WritableFile buildClassPath(HashMap<Services, List<ProviderMetaData>> mProviders){
		ClassPathBuilder mClassPathBuilder = new ClassPathBuilder();

		mClassPathBuilder.addPreInject(FileConstants.CLASSPATH_HEADER);

		//Read from config to get exlusion list
		int totalPermission = 10;
		for(int i = 0; i < totalPermission; i++)
			mClassPathBuilder.addInject("Name of class to be exluded");

		mClassPathBuilder.addPostInject(FileConstants.CLASSPATH_FOOTER);

		return mClassPathBuilder.getFile();
	}

	private WritableFile buildConfig(HashMap<Services, List<ProviderMetaData>> mProviders){
		ConfigBuilder mConfigBuilder = new ConfigBuilder();

		mConfigBuilder.addPreInject(FileConstants.CONFIG_HEADER);

		//Read from config to get service providers
		int totalPermission = 10;
		for(int i = 0; i < totalPermission; i++)
			mConfigBuilder.addInject("Provider information");

		mConfigBuilder.addPostInject(FileConstants.CONFIG_FOOTER);

		return mConfigBuilder.getFile();
	}
}