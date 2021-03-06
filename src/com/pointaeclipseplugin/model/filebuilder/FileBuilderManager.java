package com.pointaeclipseplugin.model.filebuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import com.pointaeclipseplugin.model.ProviderMetaData;
import com.pointaeclipseplugin.model.constants.FileConstants;
import com.pointaeclipseplugin.model.constants.MasterProviderInfo;
import com.pointaeclipseplugin.model.constants.MasterProviderInfo.Services;
import com.pointaeclipseplugin.model.constants.MasterProviderMeta;
import com.pointaeclipseplugin.model.constants.Permissions;
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

	public void updateFiles(Map<Services,ArrayList<ProviderMetaData>> mProviders, String filePath) {
		WritableFile lManifest, lClassPath, lConfig;

		lManifest = buildManifest(mProviders);
		lClassPath = buildClassPath(mProviders);
		lConfig = buildConfig(mProviders);

		// Synchronous start
		lManifest.writeToFile(filePath+FileConstants.MANIFEST_PATH);
		lClassPath.writeToFile(filePath+FileConstants.CLASSPATH_PATH);
		lConfig.writeToFile(filePath+FileConstants.CONFIG_PATH);
		// Synchronous end

	}

	private WritableFile buildManifest(Map<Services, ArrayList<ProviderMetaData>> mProviders){
		ManifestBuilder mManifestBuilder = new ManifestBuilder();

		mManifestBuilder.addPreInject(FileConstants.MANIFEST_HEADER);

		HashSet<Permissions> lTotalPermissions = new HashSet<Permissions>();

		// Make minimum set of permissions
		for (Services lService : Services.values()) {
			List<ProviderMetaData> lSubset = mProviders.get(lService);

			if(lSubset == null){
				continue;
			}
			
			for(ProviderMetaData lSubProvider : lSubset){
				lTotalPermissions.addAll(MasterProviderInfo.getPermissions(lSubProvider));
			}
		}

		// Inject our minimum set
		for(Permissions lPermission : lTotalPermissions){
			mManifestBuilder.addInject(lPermission.name());
		}


		mManifestBuilder.addPostInject(FileConstants.MANIFEST_FOOTER);

		return mManifestBuilder.getFile();
	}

	private WritableFile buildClassPath(Map<Services, ArrayList<ProviderMetaData>> mProviders){
		ClassPathBuilder mClassPathBuilder = new ClassPathBuilder();

		mClassPathBuilder.addPreInject(FileConstants.CLASSPATH_HEADER);

		//Read from config to get exlusion list
		HashMap<Services, List<MasterProviderMeta>> lMasterProviders = MasterProviderInfo.getProviders();

		// Super ineffeiecnt should really be using hashsets for everything.
		for (Services lService : Services.values()) {
			List<MasterProviderMeta> lSuperSet = lMasterProviders.get(lService);
			List<ProviderMetaData> lSubset = mProviders.get(lService);


			for(MasterProviderMeta lSuperProvider : lSuperSet){
				boolean found = false;
				if(lSubset != null){
				
				for(ProviderMetaData lSubProvider : lSubset){
					if(lSubProvider.getName().equals(lSuperProvider.name)){
						found = true;
						break;
					}
				}
				}
				if(!found)
					mClassPathBuilder.addInject(
							lSuperProvider.type.getPackageName() +
							"/" + 
							lSuperProvider.name +
							lSuperProvider.type.getClassName()
							);
			}
		}

		mClassPathBuilder.addPostInject(FileConstants.CLASSPATH_FOOTER);

		return mClassPathBuilder.getFile();
	}

	private WritableFile buildConfig(Map<Services, ArrayList<ProviderMetaData>> mProviders){
		ConfigBuilder mConfigBuilder = new ConfigBuilder();
		mConfigBuilder.addPreInject(FileConstants.CONFIG_HEADER);
		for (Services serType: mProviders.keySet())
		{
			for (int providerIndex = 0; providerIndex < mProviders.get(serType).size(); providerIndex++)
			{
				String typeInject = serType.toString();
				String providerInject = mProviders.get(serType).get(providerIndex).getName();
				String priorityInject = Integer.toString(providerIndex + 1);
				String paramsInject = "";
				for (String key: mProviders.get(serType).get(providerIndex).getParams().keySet())
				{
					paramsInject += "<" + key + ">";
					paramsInject += mProviders.get(serType).get(providerIndex).getParams().get(key);
					paramsInject += "</" + key + ">\n";
				}
				mConfigBuilder.addInject(typeInject, providerInject, priorityInject, paramsInject);
			}
		}
		mConfigBuilder.addPostInject(FileConstants.CONFIG_FOOTER);
		return mConfigBuilder.getFile();
	}
}