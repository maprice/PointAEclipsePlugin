package com.pointaeclipseplugin.model.constants;

/**
 * Contains all file constants used to build the manifest, classpath and config file
 * @version 1.0
 * @since July 08, 2014
 *
 */

public class FileConstants {

	// ===========================================================
	// AndroidManifest
	// ===========================================================
	
	public final static String MANIFEST_HEADER = 
			"<manifest xmlns:android=\"http://schemas.android.com/apk/res/android\"" + "\n" +
				"package=\"com.maprice.pointa\"" + "\n" +
				"android:versionCode=\"1\"" + "\n" +
				"android:versionName=\"1.0\">" + "\n" +
				"<uses-sdk android:minSdkVersion=\"8\" android:targetSdkVersion=\"15\" />" + "\n";

	public final static String MANIFEST_FOOTER = 
			"</manifest>";
	

	public final static String MANIFEST_PATH = 
			"/path/to/AndroidManifest.xml";

	// ===========================================================
	// ClassPath
	// ===========================================================
	
	public final static String CLASSPATH_HEADER = 
				"<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + "\n" +
					"<classpath>";
	
	public final static String CLASSPATH_FOOTER = 	
				"<classpathentry kind=\"src\" path=\"gen\"/>" + "\n" +
					"<classpathentry kind=\"con\" path=\"com.android.ide.eclipse.adt.ANDROID_FRAMEWORK\"/>" + "\n" +
					"<classpathentry exported=\"true\" kind=\"con\" path=\"com.android.ide.eclipse.adt.LIBRARIES\"/>" + "\n" +
					"<classpathentry exported=\"true\" kind=\"con\" path=\"com.android.ide.eclipse.adt.DEPENDENCIES\"/>" + "\n" +
					"<classpathentry kind=\"output\" path=\"bin/classes\"/>" + "\n" +
					"</classpath>";

	public final static String CLASSPATH_PATH = 
			"/path/to/.classpath";
	
	// ===========================================================
	// Config
	// ===========================================================
	
	public final static String CONFIG_HEADER = "<pointAconfig>\n";
	public final static String CONFIG_FOOTER = "</pointAconfig>";
	public final static String CONFIG_PATH = 
			"/path/to/.classpath";
	
	
	public final static String JAR_PATH = 
			"/path/to/.jar";
}
