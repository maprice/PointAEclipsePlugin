package com.pointaeclipseplugin.model.filewriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

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
		mFile = new String();
		mFile += (pFileBuilder.preInject());
		mFile += (pFileBuilder.inject());
		mFile += (pFileBuilder.postInject());
	}


	// ===========================================================
	// Methods
	// ===========================================================

	public void writeToFile(String pFileLocation) {

		System.out.println("We are writing :" + mFile + " to " + pFileLocation);

		File configFile = new File(pFileLocation);
		if (configFile.exists())
		{
			configFile.delete(); // delete old file completely - easier than merging changes
		}
		FileWriter fw;
		try {
			fw = new FileWriter(pFileLocation);
			PrintWriter pw = new PrintWriter(fw);
			pw.print(this.mFile);
			pw.flush();
			pw.close();
			fw.close(); 
		} catch (IOException e) {
			e.printStackTrace();
		}

	}



}