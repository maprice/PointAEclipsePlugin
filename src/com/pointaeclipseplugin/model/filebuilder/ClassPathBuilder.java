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

	private String mExcludeInject =  "<classpathentry excluding=\"%s\" kind=\"src\" path=\"src\"/>\n";
	private String mExludeItem = "com/pointa/service/%sProvider.java";
	private int numExclude = 0;
	private String mExcludeFull = "";
	
	
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
		
		String lExludeItem = String.format(mExludeItem, pInject);
		
		numExclude++;
		
		if(numExclude > 1){
			mExcludeFull += "|";	
		}
		
		mExcludeFull += lExludeItem;
		
		
		
	
		this.mInject = String.format(mExcludeInject, mExcludeFull);;
		return this;
	}
}