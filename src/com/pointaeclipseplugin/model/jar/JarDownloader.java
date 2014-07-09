package com.pointaeclipseplugin.model.jar;



/**
 * Worker thread that downloads Jar file from specified URL
 * @version 1.0
 * @since July 08, 2014
 *
 */

public class JarDownloader implements Runnable{

	// ===========================================================
	// Constants
	// ===========================================================

	static final String LOG_TAG =  JarDownloader.class.getSimpleName();


	// ===========================================================
	// Fields
	// ===========================================================

	private String mTargetURL;

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================


	public JarDownloader(String pURL) {
		mTargetURL = pURL;
	}

	@Override
	public void run() {
		// Download Jar from URL
		System.out.println("Downloaded Jar from:" + mTargetURL);
	}
}