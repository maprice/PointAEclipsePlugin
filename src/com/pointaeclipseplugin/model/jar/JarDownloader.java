package com.pointaeclipseplugin.model.jar;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;



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
	private String mTargetDestination;

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================


	public JarDownloader(String pURL) {
		//Downloads 914bbs.txt into PointA/jars folder. Set Destination to match your machine
		mTargetURL = pURL;
		//mTargetDestination = "C:\\Users\\Pablo\\Documents\\GitHub\\PointA\\jars";
		mTargetDestination = "C:\\Users\\Mike Price\\Desktop";
	}

	@Override
	public void run(){
		// Download Jar from URL
		try {
			System.out.println("Downloading Jar from:" + mTargetURL);
			String fileName = mTargetURL.substring(mTargetURL.lastIndexOf("/")+ 1);
			URL url = new URL(mTargetURL);
			
			InputStream is = url.openStream();
			FileOutputStream fos = new FileOutputStream(mTargetDestination + "/" + fileName);
			
			byte[] buffer = new byte[4096];
			int bytesRead = 0;
			
			while ((bytesRead = is.read(buffer)) != -1){
				System.out.print("."); //Progress bar
				fos.write(buffer, 0, bytesRead);
			}
			
			System.out.println("Finished downloading " + fileName);
			fos.close();
			is.close();
		}
		catch(Exception e){
			System.out.println(e);
			System.out.println("Failed to download from " + mTargetURL);
		}
		
	}
}