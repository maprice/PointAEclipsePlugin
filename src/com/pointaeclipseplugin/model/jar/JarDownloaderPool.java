package com.pointaeclipseplugin.model.jar;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;




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

	ExecutorService mPool;

	// ===========================================================
	// Constructors
	// ===========================================================

	public JarDownloaderPool(){
		 mPool = Executors.newFixedThreadPool(10);
	}
	
	// ===========================================================
	// Methods
	// ===========================================================

	
	public void addThread(JarDownloader pThread){
		   mPool.submit(pThread);
	}
}