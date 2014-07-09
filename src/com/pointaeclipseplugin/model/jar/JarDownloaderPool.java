package com.pointaeclipseplugin.model.jar;




/**
 * Handles allocation and deallocation of JarDownloader worker threads
 * @version 1.0
 * @since July 08, 2014
 *
 */

public class JarDownloaderPool {

	// ===========================================================
	// Constants
	// ===========================================================

	static final String LOG_TAG =  JarDownloaderPool.class.getSimpleName();

	// ===========================================================
	// Fields
	// ===========================================================


	

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	
	protected JarDownloader onAllocatePoolItem() {
		return null;
	}

	/**
	 * Called when a JarDownloader is sent to the pool
	 */
	protected void onHandleRecycleItem(final JarDownloader pDownloader) {

	}

	/**
	 * Called just before a JarDownloader is returned to the caller
	 */
	protected void onHandleObtainItem(final JarDownloader pDownloader) {
		
	}
}