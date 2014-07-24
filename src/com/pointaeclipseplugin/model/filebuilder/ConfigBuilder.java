package com.pointaeclipseplugin.model.filebuilder;



/**
 * <Class Description>
 * @version 1.0
 * @since July 08, 2014
 *
 */

public class ConfigBuilder extends FileBuilder{

	// ===========================================================
	// Constants
	// ===========================================================

	static final String LOG_TAG =  ConfigBuilder.class.getSimpleName();
	private String configInject = "<service>\n<type>%s</type>\n<provider>%s</provider>\n<priority>%s</priority>\n%s</service>\n";
	// last %s is provider-specific parameters
	

	// ===========================================================
	// Fields
	// ===========================================================


	

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	//Overloaded:
	public FileBuilder addInject(String typeInject, String providerInject, String priorityInject, String paramsInject) {
		if(mInject == null){
			mInject = new String();
		}
		String serviceInfo = String.format(configInject, typeInject, providerInject, priorityInject, paramsInject);
		this.mInject += serviceInfo;
		return this;
	}


	public FileBuilder addInject(String pInject) {
		// TODO Auto-generated method stub
		return this;
	}

}