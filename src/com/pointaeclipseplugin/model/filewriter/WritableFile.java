package com.pointaeclipseplugin.model.filewriter;

import com.pointaeclipseplugin.model.filebuilder.FileBuilder;



/**
 * <Class Description>
 * @version 1.0
 * @since July 08, 2014
 *
 */

public class WritableFile{

	// ===========================================================
	// Constants
	// ===========================================================



	static final String LOG_TAG =  WritableFile.class.getSimpleName();

	// ===========================================================
	// Fields
	// ===========================================================

	private String mFile;
	

	// ===========================================================
	// Constructors
	// ===========================================================
	
	public WritableFile(FileBuilder pFileBuilder) {
		mFile.concat(pFileBuilder.preInject());
		mFile.concat(pFileBuilder.inject());
		mFile.concat(pFileBuilder.postInject());
	}


	// ===========================================================
	// Methods
	// ===========================================================

	public void writeToFile(String pFileLocation) {
		// Write mFile to specified location
	}
	
	
	
}