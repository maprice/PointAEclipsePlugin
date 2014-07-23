package com.pointaeclipseplugin.model;

import java.util.Map;

/**
 * Container class of service provider specific data
 * @version 1.0
 * @since June 13, 2014
 *
 */

public class ProviderMetaData{

	// ===========================================================
	// Constants
	// ===========================================================

	static final String LOG_TAG = ProviderMetaData.class.getSimpleName();

	// ===========================================================
	// Fields
	// ===========================================================

	final private String mName;
	final private Map<String, String> mParams;
	private int mPriority;
	private boolean mEnabled;

	// ===========================================================
	// Constructors
	// ===========================================================

	/**
	 *
	 * @deprecated use {@link  ProviderMetaData(String pName, Map<String, String> pParams, int pPriority, boolean pEnabled)} instead.  
	 */
	public ProviderMetaData(String pName, Map<String, String> pParams){
		mName = pName;
		mParams = pParams;
		mPriority = 0;
		mEnabled = true;
	}

	public ProviderMetaData(String pName, Map<String, String> pParams, int pPriority, boolean pEnabled){
		mName = pName;
		mParams = pParams;
		mPriority = pPriority;
		mEnabled = pEnabled;
	}

	// ===========================================================
	// Methods
	// ===========================================================

	public String getName(){
		return mName;
	}

	public Map<String, String> getParams(){
		return mParams;
	}

	public boolean getEnabled(){
		return mEnabled;
	}

	public int getPriority(){
		return mPriority;
	}

}