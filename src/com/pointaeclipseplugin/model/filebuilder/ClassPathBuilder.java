package com.pointaeclipseplugin.model.filebuilder;



/**
 * Builds the .classpath document
 * @version 1.0
 * @since July 08, 2014
 *
 */

public class ClassPathBuilder extends FileBuilder{

	// ===========================================================
	// Constants
	// ===========================================================

	static final String LOG_TAG =  ClassPathBuilder.class.getSimpleName();

	private String mExcludeInject =  "<classpathentry excluding=\"com/pointa/service/%sProvider.java\" kind=\"src\" path=\"src\"/>\n";
	

	// ===========================================================
	// Fields
	// ===========================================================


	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	@Override
	public FileBuilder addInject(String pInject) {
		if(mInject == null){
			mInject = new String();
		}
		
		String lExlude = String.format(mExcludeInject, pInject);
		this.mInject += lExlude;
		return this;
	}
}