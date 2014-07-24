package com.pointaeclipseplugin.model.filebuilder;

import com.pointaeclipseplugin.model.filewriter.WritableFile;



/**
 * <Class Description>
 * @version 1.0
 * @since July 08, 2014
 *
 */

public abstract class FileBuilder {

	// ===========================================================
	// Constants
	// ===========================================================

	static final String LOG_TAG =  FileBuilder.class.getSimpleName();

	// ===========================================================
	// Fields
	// ===========================================================

	protected String mPreInject;
	protected String mInject;
	protected String mPostInject;
	

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================
	
	public abstract FileBuilder addInject(String pInject);
	
	public FileBuilder addPreInject(String pPreInject){
		mPreInject = pPreInject;
		return this;
	}
	
	public FileBuilder addPostInject(String pPostInject){
		mPostInject = pPostInject;
		return this;
	}
	
	public WritableFile getFile(){
		return new WritableFile(this);
	}

	public String preInject() {
		return mPreInject;
	}	
	
	public String inject() {
		return mInject;
	}	
	
	public String postInject() {
		return mPostInject;
	}	
}