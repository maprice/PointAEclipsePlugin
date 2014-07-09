package com.pointaeclipseplugin.model.filebuilder;



/**
 * <Class Description>
 * @version 1.0
 * @since July 08, 2014
 *
 */

public class ClassPathBuilder extends FileBuilder{

	// ===========================================================
	// Constants
	// ===========================================================

	static final String LOG_TAG =  ClassPathBuilder.class.getSimpleName();

	private String mExcludeInject =  "<classpathentry excluding=\"com/pointa/service/%s.java\" kind=\"src\" path=\"src\"/>";
	

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
		String lExlude = String.format(mExcludeInject, pInject);
		this.mInject += lExlude;
		return this;
	}
}