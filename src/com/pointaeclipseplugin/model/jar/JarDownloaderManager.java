package com.pointaeclipseplugin.model.jar;

import com.pointaeclipseplugin.model.filereader.ConfigSettings;



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

	public void updateJars(ConfigSettings pNewConfig) {
		//for(Number of Jars to download){
		
			// Get the Jar's URL
			String URL = "http://JarLocation.com";
		
			// Create new thread
			JarDownloader lJarDownloader = new JarDownloader(URL);
		
			// Add thread to pool
			mPool.addThread(lJarDownloader);
			
		//}
	}
}