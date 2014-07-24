package com.pointaeclipseplugin.model.constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.pointaeclipseplugin.model.ProviderMetaData;

public class MasterProviderInfo {
	
	public static enum Services {		
		Ads,
		Analytics,
		CrashReporter,
		Rating,
		Push,
		Billing
		//...
	}
	// Could have a list of all providers,  OR a hashmap with key = serviceType value = ArrayList<Providers> (Better) 
	//final ArrayList<Provider> providerMasterList;
	static HashMap<Services, List<MasterProviderMeta>> providerMasterList;



	private static void initMasterProviderInfo(){
		providerMasterList = new HashMap<Services, List<MasterProviderMeta>>();

		//====================
		// ADS
		//====================
		MasterProviderMeta admob = new MasterProviderMeta("Admob", Services.Ads, "http://www.aptoide.org/export/1394/libraries/aptoide-4.2.0-libs/mopub-sdk/libs/GoogleAdMobAdsSdk-6.4.1.jar");
		admob.params.add("Size");
		admob.params.add("Unit ID");

	/*	MasterProviderMeta adsense = new MasterProviderMeta("AdSense", Services.Ads, null);
		adsense.params.add("Size2");
		adsense.params.add("Unit ID2");

		MasterProviderMeta burstly = new MasterProviderMeta("Burstly", Services.Ads, null);
		burstly.params.add("Size3");
		burstly.params.add("Unit ID3");
*/
		List<MasterProviderMeta> adsList = new ArrayList<MasterProviderMeta>();
		adsList.add(admob);
	//	adsList.add(adsense);
	//	adsList.add(burstly);

		providerMasterList.put(Services.Ads, adsList);

		//====================
		// ANALYTICS
		//====================
		MasterProviderMeta googleanalytics = new MasterProviderMeta("Google Analytics", Services.Analytics, null);
		googleanalytics.params.add("App ID");
		googleanalytics.params.add("Client Key");

		MasterProviderMeta parseanalytics = new MasterProviderMeta("Parse Analytics", Services.Analytics, null);
		parseanalytics.params.add("App ID");
		parseanalytics.params.add("Client Key");

		List<MasterProviderMeta> analyticsList = new ArrayList<MasterProviderMeta>();
		analyticsList.add(googleanalytics);
		analyticsList.add(parseanalytics);

		providerMasterList.put(Services.Analytics, analyticsList);

		//====================
		// CRASH
		//====================
		MasterProviderMeta bugsense = new MasterProviderMeta("BugSense", Services.CrashReporter, "https://s3.amazonaws.com/bugsenseplugins/bugsense-3.6.1.jar");
		bugsense.params.add("App ID");

		MasterProviderMeta bugsnag = new MasterProviderMeta("BugSnag", Services.CrashReporter, "http://central.maven.org/maven2/com/bugsnag/bugsnag-android/2.2.0/bugsnag-android-2.2.0.jar");
		bugsnag.params.add("App ID");

		MasterProviderMeta crittercism = new MasterProviderMeta("Crittercism", Services.CrashReporter, "http://central.maven.org/maven2/com/crittercism/crittercism-android-agent/4.5.3/crittercism-android-agent-4.5.3.jar");
		crittercism.params.add("App ID");

		List<MasterProviderMeta> crashList = new ArrayList<MasterProviderMeta>();
		crashList.add(bugsense);
		crashList.add(bugsnag);
		crashList.add(crittercism);

		providerMasterList.put(Services.CrashReporter, crashList);

		//====================
		// PUSH
		//====================
		MasterProviderMeta parse = new MasterProviderMeta("Parse", Services.Push, null);
		parse.params.add("App ID");
		parse.params.add("Client Key");

		List<MasterProviderMeta> parseList = new ArrayList<MasterProviderMeta>();
		parseList.add(parse);

		providerMasterList.put(Services.Push, parseList);

		//====================
		// RATING
		//====================
		MasterProviderMeta amazon = new MasterProviderMeta("Amazon", Services.Rating, null);
		amazon.params.add("URI");

		MasterProviderMeta googleplay = new MasterProviderMeta("Google Play", Services.Rating, null);
		googleplay.params.add("URI");

		List<MasterProviderMeta> ratingList = new ArrayList<MasterProviderMeta>();
		ratingList.add(amazon);
		ratingList.add(googleplay);

		providerMasterList.put(Services.Rating, ratingList);

		//====================
		// BILLING
		//====================
		MasterProviderMeta googleBilling = new MasterProviderMeta("Google Play", Services.Billing, null);
		

		MasterProviderMeta amazonBilling = new MasterProviderMeta("Amazon Market", Services.Billing, null);
	
		
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
}
