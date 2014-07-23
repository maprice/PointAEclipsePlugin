package com.pointaeclipseplugin.model.filebuilder;

import java.util.Map;

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

	public void updateFiles(ConfigSettings pNewConfig, Map<Services,ProviderMetaData[]> mProviders) {
		WritableFile lManifest, lClassPath, lConfig;

		lManifest = buildManifest(pNewConfig);
		lClassPath = buildClassPath(pNewConfig);
		lConfig = buildConfig(mProviders);

		// Synchronous start
		lManifest.writeToFile(FileConstants.MANIFEST_PATH);
		lClassPath.writeToFile(FileConstants.CLASSPATH_PATH);
		lConfig.writeToFile(FileConstants.CONFIG_PATH);
		// Synchronous end

	}

	private WritableFile buildManifest(ConfigSettings pNewConfig){
		ManifestBuilder mManifestBuilder = new ManifestBuilder();

		mManifestBuilder.addPreInject(FileConstants.MANIFEST_HEADER);

		//Read from config to get permission list
		int totalPermission = 10;
		for(int i = 0; i < totalPermission; i++)
			mManifestBuilder.addInject("jkhb");

		mManifestBuilder.addPostInject(FileConstants.MANIFEST_FOOTER);

		return mManifestBuilder.getFile();
	}

	private WritableFile buildClassPath(ConfigSettings pNewConfig){
		ClassPathBuilder mClassPathBuilder = new ClassPathBuilder();

		mClassPathBuilder.addPreInject(FileConstants.CLASSPATH_HEADER);

		//Read from config to get exlusion list
		int totalPermission = 10;
		for(int i = 0; i < totalPermission; i++)
			mClassPathBuilder.addInject("Name of class to be exluded");

		mClassPathBuilder.addPostInject(FileConstants.CLASSPATH_FOOTER);

		return mClassPathBuilder.getFile();
	}

	private WritableFile buildConfig(Map<Services,ProviderMetaData[]> mProviders){
		ConfigBuilder mConfigBuilder = new ConfigBuilder();

		mConfigBuilder.addPreInject(FileConstants.CONFIG_HEADER);

		for (Services serType: mProviders.keySet())
		{
			for (int providerIndex = 0; providerIndex < mProviders.get(serType).length; providerIndex++)
			{
				String typeInject = serType.toString();
				
				String providerInject = mProviders.get(serType)[providerIndex].getName();
				
				String priorityInject = Integer.toString(providerIndex);

				String paramsInject = "";
				for (String key: mProviders.get(serType)[providerIndex].getParams().keySet())
				{
					paramsInject += "<" + key + ">";
					paramsInject += mProviders.get(serType)[providerIndex].getParams().get(key);
					paramsInject += "</" + key + ">";
				}

				mConfigBuilder.addInject(typeInject, providerInject, priorityInject, paramsInject);
			}
		}
		
		mConfigBuilder.addPostInject(FileConstants.CONFIG_FOOTER);

		return mConfigBuilder.getFile();
	}
}