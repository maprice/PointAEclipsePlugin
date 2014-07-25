package com.pointaeclipseplugin.model.jar;

import java.util.ArrayList;
import java.util.HashMap;

import com.pointaeclipseplugin.model.ProviderMetaData;
import com.pointaeclipseplugin.model.constants.MasterProviderInfo;
import com.pointaeclipseplugin.model.constants.MasterProviderInfo.Services;



/**
 * Facade for all interaction with Jar downloading
 * @version 1.0
 * @since July 08, 2014
 *
 */

public class JarDownloaderManager {

	// ===========================================================
	// Constants
	// ===========================================================

	static final String LOG_TAG =  JarDownloaderManager.class.getSimpleName();

	// ===========================================================
	// Fields
	// ===========================================================

	private JarDownloaderPool mPool;
	
	// ===========================================================
	// Constructors
	// ===========================================================

	public JarDownloaderManager(){
		mPool = new JarDownloaderPool();
	}
	
	// ===========================================================
	// Methods
	// ===========================================================

	public void updateJars(HashMap<Services, ArrayList<ProviderMetaData>> mProviders) {
		//for(Number of Jars to download){
		
		for (Services lService : Services.values()) {
			ArrayList<ProviderMetaData> lProviders = mProviders.get(lService);
			if(lProviders == null){
				continue;
			}
			for(ProviderMetaData lProvider : lProviders){

			String lUrl = MasterProviderInfo.getJarURL(lProvider);
			
			if(lUrl != null && !lUrl.isEmpty()){
				// Create new thread
				JarDownloader lJarDownloader = new JarDownloader(lUrl);
			
				// Add thread to pool
				mPool.addThread(lJarDownloader);
			}

			}
			System.out.println("---------------------");
		}
		
	}
}