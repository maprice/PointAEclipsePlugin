package com.pointaeclipseplugin.model.constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.pointaeclipseplugin.model.ProviderMetaData;

public class MasterProviderInfo {
		
	public enum Services{
		Ads("ads", "Ads"),
		Analytics("analytics", "Analytics"),
		CrashReporter("crashreporter", "CrashReporter"),
		Rating("rating", "Rating"),
		Push("push", "Push"),
		Billing("billing", "Billing");
		//...


		private Services(final String pPackage, final String pClass) {
			this.mPackage = pPackage;
			this.mClass = pClass;
		}

		private final String mPackage;
		private final String mClass;

		public String getPackageName() {
			return mPackage;
		}

		public String getClassName() {
			return mClass;
		}
	}
	
	// Could have a list of all providers,  OR a hashmap with key = serviceType value = ArrayList<Providers> (Better) 
	//final ArrayList<Provider> providerMasterList;
	static HashMap<Services, List<MasterProviderMeta>> providerMasterList;



	private static void initMasterProviderInfo(){
		providerMasterList = new HashMap<Services, List<MasterProviderMeta>>();

		//====================
		// ADS
		//====================

		MasterProviderMeta admob = new MasterProviderMeta("AdMob", Services.Ads, "http://www.aptoide.org/export/1394/libraries/aptoide-4.2.0-libs/mopub-sdk/libs/GoogleAdMobAdsSdk-6.4.1.jar");
		admob.params.add("bannerUnitID");
		admob.params.add("interstitialUnitID");
		admob.addPermission(Permissions.INTERNET);
		admob.addPermission(Permissions.ACCESS_NETWORK_STATE);

		List<MasterProviderMeta> adsList = new ArrayList<MasterProviderMeta>();
		adsList.add(admob);
		
		providerMasterList.put(Services.Ads, adsList);

		//====================
		// ANALYTICS
		//====================
		MasterProviderMeta googleanalytics = new MasterProviderMeta("Google", Services.Analytics, null);
		googleanalytics.params.add("appID");
		googleanalytics.params.add("clientID");
		googleanalytics.addPermission(Permissions.INTERNET);

		MasterProviderMeta parseanalytics = new MasterProviderMeta("Parse", Services.Analytics, null);
		parseanalytics.params.add("appId");
		parseanalytics.params.add("clientKey");
		parseanalytics.addPermission(Permissions.INTERNET);
		parseanalytics.addPermission(Permissions.WAKE_LOCK);
		parseanalytics.addPermission(Permissions.VIBRATE);
		parseanalytics.addPermission(Permissions.RECEIVE_BOOT_COMPLETED);
		parseanalytics.addPermission(Permissions.GET_ACCOUNTS);
		parseanalytics.addPermission(Permissions.RECEIVE);
		
		List<MasterProviderMeta> analyticsList = new ArrayList<MasterProviderMeta>();
		analyticsList.add(googleanalytics);
		analyticsList.add(parseanalytics);

		providerMasterList.put(Services.Analytics, analyticsList);

		//====================
		// CRASH
		//====================
		MasterProviderMeta bugsense = new MasterProviderMeta("BugSense", Services.CrashReporter, "https://s3.amazonaws.com/bugsenseplugins/bugsense-3.6.1.jar");
		bugsense.params.add("appId");
		bugsense.addPermission(Permissions.INTERNET);
		
		MasterProviderMeta bugsnag = new MasterProviderMeta("BugSnag", Services.CrashReporter, "http://central.maven.org/maven2/com/bugsnag/bugsnag-android/2.2.0/bugsnag-android-2.2.0.jar");
		bugsnag.params.add("appId");
		bugsnag.addPermission(Permissions.INTERNET);

		MasterProviderMeta crittercism = new MasterProviderMeta("Crittercism", Services.CrashReporter, "http://central.maven.org/maven2/com/crittercism/crittercism-android-agent/4.5.3/crittercism-android-agent-4.5.3.jar");
		crittercism.params.add("appId");
		crittercism.addPermission(Permissions.INTERNET);
		
		
		List<MasterProviderMeta> crashList = new ArrayList<MasterProviderMeta>();
		crashList.add(bugsense);
		crashList.add(bugsnag);
		crashList.add(crittercism);

		providerMasterList.put(Services.CrashReporter, crashList);

		//====================
		// PUSH
		//====================
		MasterProviderMeta parse = new MasterProviderMeta("Parse", Services.Push, null);
		parse.params.add("AppID");
		parse.params.add("ClientKey");

		List<MasterProviderMeta> parseList = new ArrayList<MasterProviderMeta>();
		parseList.add(parse);

		providerMasterList.put(Services.Push, parseList);

		//====================
		// RATING
		//====================
		MasterProviderMeta amazon = new MasterProviderMeta("Amazon", Services.Rating, null);
		amazon.params.add("URI");

		MasterProviderMeta googleplay = new MasterProviderMeta("GooglePlay", Services.Rating, null);
		googleplay.params.add("URI");

		List<MasterProviderMeta> ratingList = new ArrayList<MasterProviderMeta>();
		ratingList.add(amazon);
		ratingList.add(googleplay);

		providerMasterList.put(Services.Rating, ratingList);

		//====================
		// BILLING
		//====================
		MasterProviderMeta googleBilling = new MasterProviderMeta("GooglePlay", Services.Billing, null);
		

		MasterProviderMeta amazonBilling = new MasterProviderMeta("AmazonMarket", Services.Billing, null);
	
		
		List<MasterProviderMeta> twitterList = new ArrayList<MasterProviderMeta>();
		twitterList.add(googleBilling);
		twitterList.add(amazonBilling);

		providerMasterList.put(Services.Billing, twitterList);

		//Create other stuff..........

	}

	// UI reads me
	public static HashMap<Services, List<MasterProviderMeta>> getProviders(){
		if(providerMasterList == null){
			initMasterProviderInfo();
		}
		return providerMasterList;
	}

	public static String getJarURL(ProviderMetaData lProvider) {
		HashMap<Services, List<MasterProviderMeta>> lMaster = MasterProviderInfo.getProviders();
		
		List<MasterProviderMeta> lMasterService = lMaster.get(lProvider.getType());
		
		for(MasterProviderMeta lData : lMasterService){
			if(lData.name.equals(lProvider.getName())){
				return lData.jarLocation;
			}
		}
		
		return null;
	}

	public static Set<Permissions> getPermissions(ProviderMetaData lProvider) {
		
	HashMap<Services, List<MasterProviderMeta>> lMaster = MasterProviderInfo.getProviders();
		
		List<MasterProviderMeta> lMasterService = lMaster.get(lProvider.getType());
		
		for(MasterProviderMeta lData : lMasterService){
			if(lData.name.equals(lProvider.getName())){
				return lData.getPermission();
			}
		}
		
		return new HashSet<Permissions>();
	}
}
