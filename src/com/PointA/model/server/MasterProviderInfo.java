package com.PointA.model.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MasterProviderInfo {
	
	public static enum Services {		
		Ads,
		Analytics,
		CrashReporter,
		Rating,
		Push,
		Twitter
		//...
	}
	// Could have a list of all providers,  OR a hashmap with key = serviceType value = ArrayList<Providers> (Better) 
	//final ArrayList<Provider> providerMasterList;
	static HashMap<Services, List<MasterProvider>> providerMasterList;



	private static void initMasterProviderInfo(){
		providerMasterList = new HashMap<Services, List<MasterProvider>>();

		//====================
		// ADS
		//====================
		MasterProvider admob = new MasterProvider("Admob", Services.Ads, null);
		admob.params.add("Size");
		admob.params.add("Unit ID");

		MasterProvider adsense = new MasterProvider("AdSense", Services.Ads, null);
		adsense.params.add("Size2");
		adsense.params.add("Unit ID2");

		MasterProvider burstly = new MasterProvider("Burstly", Services.Ads, null);
		burstly.params.add("Size3");
		burstly.params.add("Unit ID3");

		List<MasterProvider> adsList = new ArrayList<MasterProvider>();
		adsList.add(admob);
		adsList.add(adsense);
		adsList.add(burstly);

		providerMasterList.put(Services.Ads, adsList);

		//====================
		// ANALYTICS
		//====================
		MasterProvider googleanalytics = new MasterProvider("Google Analytics", Services.Analytics, null);
		googleanalytics.params.add("App ID");
		googleanalytics.params.add("Client Key");

		MasterProvider parseanalytics = new MasterProvider("Parse Analytics", Services.Analytics, null);
		parseanalytics.params.add("App ID");
		parseanalytics.params.add("Client Key");

		List<MasterProvider> analyticsList = new ArrayList<MasterProvider>();
		analyticsList.add(googleanalytics);
		analyticsList.add(parseanalytics);

		providerMasterList.put(Services.Analytics, analyticsList);

		//====================
		// CRASH
		//====================
		MasterProvider bugsense = new MasterProvider("BugSense", Services.CrashReporter, null);
		bugsense.params.add("App ID");

		MasterProvider bugsnag = new MasterProvider("BugSnag", Services.CrashReporter, null);
		bugsnag.params.add("App ID");

		MasterProvider crittercism = new MasterProvider("Crittercism", Services.CrashReporter, null);
		crittercism.params.add("App ID");

		List<MasterProvider> crashList = new ArrayList<MasterProvider>();
		crashList.add(bugsense);
		crashList.add(bugsnag);
		crashList.add(crittercism);

		providerMasterList.put(Services.CrashReporter, crashList);

		//====================
		// PUSH
		//====================
		MasterProvider parse = new MasterProvider("Parse", Services.Push, null);
		parse.params.add("App ID");
		parse.params.add("Client Key");

		List<MasterProvider> parseList = new ArrayList<MasterProvider>();
		parseList.add(parse);

		providerMasterList.put(Services.Push, parseList);

		//====================
		// RATING
		//====================
		MasterProvider amazon = new MasterProvider("Amazon", Services.Rating, null);
		amazon.params.add("URI");

		MasterProvider googleplay = new MasterProvider("Google Play", Services.Rating, null);
		googleplay.params.add("URI");

		List<MasterProvider> ratingList = new ArrayList<MasterProvider>();
		ratingList.add(amazon);
		ratingList.add(googleplay);

		providerMasterList.put(Services.Rating, ratingList);

		//====================
		// TWITTER
		//====================
		MasterProvider twitter = new MasterProvider("Twitter", Services.Twitter, null);
		twitter.params.add("Consumer Key");
		twitter.params.add("Consumer Secret");

		List<MasterProvider> twitterList = new ArrayList<MasterProvider>();
		twitterList.add(twitter);

		providerMasterList.put(Services.Twitter, twitterList);

		//Create other stuff..........

	}

	// UI reads me
	public static HashMap<Services, List<MasterProvider>> getProviders(){
		if(providerMasterList == null){
			initMasterProviderInfo();
		}
		return providerMasterList;
	}
}
