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
		
		
		String URL = "http://JarLocation.com";
		
		JarDownloader lJarDownloader = mPool.onAllocatePoolItem();
		lJarDownloader.run(URL);
		
	}
}