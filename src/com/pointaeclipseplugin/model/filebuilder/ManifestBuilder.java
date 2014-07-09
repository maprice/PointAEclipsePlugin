package com.pointaeclipseplugin.model.filebuilder;



/**
 * <Class Description>
 * @version 1.0
 * @since July 08, 2014
 *
 */

public class ManifestBuilder extends FileBuilder{

	// ===========================================================
	// Constants
	// ===========================================================

	static final String LOG_TAG =  ManifestBuilder.class.getSimpleName();

	private String mPermissionInject =  "<uses-permission android:name=\"android.permission.%s\" />";

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
		String lPremission = String.format(mPermissionInject, pInject);
		this.mInject += lPremission;
		return this;
	}
}