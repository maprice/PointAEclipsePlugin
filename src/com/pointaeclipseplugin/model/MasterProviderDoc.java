package com.pointaeclipseplugin.model;

import java.util.ArrayList;
import com.pointaeclipseplugin.model.PointAServiceConstants.Services;

public class MasterProviderDoc {
	
	// Could have a list of all providers,  OR a hashmap with key = serviceType value = ArrayList<Providers> (Better) 
	final ArrayList<Provider> providerMasterList;
	
	
	public MasterProviderDoc(){
		providerMasterList = new ArrayList<Provider>();
		
		//====================
		// ADS
		//====================
		Provider admob = new Provider("Admob", Services.Ads, null);
		admob.params.add("Size");
		admob.params.add("Unit ID");
		
		Provider adsense = new Provider("AdSense", Services.Ads, null);
		admob.params.add("Size");
		admob.params.add("Unit ID");
		
		Provider burstly = new Provider("Burstly", Services.Ads, null);
		admob.params.add("Size");
		admob.params.add("Unit ID");
		
		providerMasterList.add(admob);
		providerMasterList.add(adsense);
		providerMasterList.add(burstly);
		
		//====================
		// ANALYTICS
		//====================
		Provider googleanalytics = new Provider("Google Analytics", Services.Analytics, null);
		googleanalytics.params.add("App ID");
		googleanalytics.params.add("Client Key");
		
		Provider parseanalytics = new Provider("Parse Analytics", Services.Analytics, null);
		googleanalytics.params.add("App ID");
		googleanalytics.params.add("Client Key");
		
		providerMasterList.add(googleanalytics);
		providerMasterList.add(parseanalytics);
		
		//====================
		// CRASH
		//====================
		Provider bugsense = new Provider("BugSense", Services.CrashReporter, null);
		bugsense.params.add("App ID");
		
		Provider bugsnag = new Provider("BugSnag", Services.CrashReporter, null);
		bugsnag.params.add("App ID");
		
		Provider crittercism = new Provider("Crittercism", Services.CrashReporter, null);
		crittercism.params.add("App ID");
		
		providerMasterList.add(bugsense);
		providerMasterList.add(bugsnag);
		providerMasterList.add(crittercism);
		
		//====================
		// PUSH
		//====================
		Provider parse = new Provider("Parse", Services.Push, null);
		parse.params.add("App ID");
		parse.params.add("Client Key");
		providerMasterList.add(parse);
		
		//====================
		// RATING
		//====================
		Provider amazon = new Provider("Amazon", Services.Rating, null);
		amazon.params.add("URI");
		
		Provider googleplay = new Provider("Google Play", Services.Rating, null);
		googleplay.params.add("URI");
		
		providerMasterList.add(amazon);
		providerMasterList.add(googleplay);
		
		//====================
		// TWITTER
		//====================
		Provider twitter = new Provider("Twitter", Services.Twitter, null);
		twitter.params.add("Consumer Key");
		twitter.params.add("Consumer Secret");
		
		providerMasterList.add(twitter);
		
		//Create other stuff..........
		
	}
	
	// UI reads me
	public ArrayList<Provider> getProviders(){
		return providerMasterList;
	}
}
