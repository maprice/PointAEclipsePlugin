package com.pointaeclipseplugin.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pointaeclipseplugin.model.constants.MasterProviderInfo;
import com.pointaeclipseplugin.model.constants.MasterProviderInfo.Services;
import com.pointaeclipseplugin.model.constants.MasterProviderMeta;

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
	private Services mType;

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

	public ProviderMetaData(String pName, Map<String, String> pParams, int pPriority, boolean pEnabled, Services pType){
		mName = pName;
		mParams = pParams;
		mPriority = pPriority;
		mEnabled = pEnabled;
		mType = pType;
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
	
	public Services getType(){
		return mType;
	}

	public void setEnabled(boolean pEnabled) {
		mEnabled = pEnabled;
	}

	public void setPriority(int pPriority) {
		mPriority = pPriority;
	}
	
	public static ProviderMetaData buildEmptyProvider(String pName) {
		HashMap<Services, List<MasterProviderMeta>> fProvider = MasterProviderInfo.getProviders();

		for (Services lService : Services.values()) {
			List<MasterProviderMeta> lProviders = fProvider.get(lService);

			for(MasterProviderMeta lProvider : lProviders){
				if(lProvider.name.equals(pName)){
					// Create the new meta object
					Map<String,String> newParams = new HashMap<String, String>();

					for(String paramKey : lProvider.params){
						newParams.put(paramKey, "");
					}

					ProviderMetaData newProvider = new ProviderMetaData(pName, newParams, 0, false, lService);
					return newProvider;
				}
			}
		}
		// Throw exception
		// This means user has messed up config
		return null;

	}

}