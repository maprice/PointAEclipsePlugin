package com.pointaeclipseplugin.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List; 

import com.pointaeclipseplugin.model.PointAServiceConstants.Services;

public class MasterProviderDoc {
	
	// Could have a list of all providers,  OR a hashmap with key = serviceType value = ArrayList<Providers> (Better) 
	//final ArrayList<Provider> providerMasterList;
	final HashMap<Services, List<Provider>> providerMasterList;
	
	public MasterProviderDoc(){
		providerMasterList = new HashMap<Services, List<Provider>>();
		
		//====================
		// ADS
		//====================
		Provider admob = new Provider("Admob", Services.Ads, null);
		admob.params.add("Size");
		admob.params.add("Unit ID");
		
		Provider adsense = new Provider("AdSense", Services.Ads, null);
		adsense.params.add("Size2");
		adsense.params.add("Unit ID2");
		
		Provider burstly = new Provider("Burstly", Services.Ads, null);
		burstly.params.add("Size3");
		burstly.params.add("Unit ID3");
		
		List<Provider> adsList = new ArrayList<Provider>();
		adsList.add(admob);
		adsList.add(adsense);
		adsList.add(burstly);
		
		providerMasterList.put(Services.Ads, adsList);
		
		//====================
		// ANALYTICS
		//====================
		Provider googleanalytics = new Provider("Google Analytics", Services.Analytics, null);
		googleanalytics.params.add("App ID");
		googleanalytics.params.add("Client Key");
		
		Provider parseanalytics = new Provider("Parse Analytics", Services.Analytics, null);
		parseanalytics.params.add("App ID");
		parseanalytics.params.add("Client Key");
		
		List<Provider> analyticsList = new ArrayList<Provider>();
		analyticsList.add(googleanalytics);
		analyticsList.add(parseanalytics);
		
		providerMasterList.put(Services.Analytics, analyticsList);
		
		//====================
		// CRASH
		//====================
		Provider bugsense = new Provider("BugSense", Services.CrashReporter, null);
		bugsense.params.add("App ID");
		
		Provider bugsnag = new Provider("BugSnag", Services.CrashReporter, null);
		bugsnag.params.add("App ID");
		
		Provider crittercism = new Provider("Crittercism", Services.CrashReporter, null);
		crittercism.params.add("App ID");
		
		List<Provider> crashList = new ArrayList<Provider>();
		crashList.add(bugsense);
		crashList.add(bugsnag);
		crashList.add(crittercism);
		
		providerMasterList.put(Services.CrashReporter, crashList);
		
		//====================
		// PUSH
		//====================
		Provider parse = new Provider("Parse", Services.Push, null);
		parse.params.add("App ID");
		parse.params.add("Client Key");
		
		List<Provider> parseList = new ArrayList<Provider>();
		parseList.add(parse);
		
		providerMasterList.put(Services.Push, parseList);
		
		//====================
		// RATING
		//====================
		Provider amazon = new Provider("Amazon", Services.Rating, null);
		amazon.params.add("URI");
		
		Provider googleplay = new Provider("Google Play", Services.Rating, null);
		googleplay.params.add("URI");
		
		List<Provider> ratingList = new ArrayList<Provider>();
		ratingList.add(amazon);
		ratingList.add(googleplay);
		
		providerMasterList.put(Services.Rating, ratingList);
		
		//====================
		// TWITTER
		//====================
		Provider twitter = new Provider("Twitter", Services.Twitter, null);
		twitter.params.add("Consumer Key");
		twitter.params.add("Consumer Secret");
		
		List<Provider> twitterList = new ArrayList<Provider>();
		twitterList.add(twitter);
		
		providerMasterList.put(Services.Twitter, twitterList);
		
		//Create other stuff..........
		
	}
	
	// UI reads me
	public HashMap<Services, List<Provider>> getProviders(){
		return providerMasterList;
	}
}
