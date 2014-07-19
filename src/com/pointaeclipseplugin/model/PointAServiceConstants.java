package com.pointaeclipseplugin.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Contains all meta data on all service providers
 * @version 1.0
 * @since July 08, 2014
 *
 */


public class PointAServiceConstants {

	// ===========================================================
	// Constants
	// ===========================================================

	public static class Field{
		
		public Widget widget;
		public String label;
		public String[] values;
		
		public Field(Widget widget, String label, String [] values){
			this.widget = widget;
			this.label = label;
			this.values = values;
			
		}
		
	}


	public static List<Field> Ads_UI = new ArrayList<Field>();
	
	public static List<Field> Analytics_UI = new ArrayList<Field>();
	
	public static List<Field> Crash_Reporting_UI = new ArrayList<Field>();
	
	public static List<Field> Push_Notifications_UI = new ArrayList<Field>();
	
	public static List<Field> Rating_UI = new ArrayList<Field>();
	
	public static List<Field> Twitter_UI = new ArrayList<Field>();
	
	

	public static enum Services {
		
		Ads, Analytics, Crash_Reporting, Push_Notifications, Rating, Twitter
	
	}
	

	public static enum Widget {
		
		Checkbox, Dropdown, TextInput, Button, Label, BlankLine;

		
	}

	
	//Service Specific Variables
	public static final String[] AdsServiceProviders = { "Admob", "AdSense", "Burstly" };
	public static final String[] AdSizes = { "Banner", "Interstitial" };

	public static final String[] AnalyticsServiceProviders = { "Google Analytics", "Parse Analytics" };
	
	public static final String[] CrashServiceProviders = { "BugSense", "BugSnag", "Crittercism" };	
	
	public static final String[] PushServiceProviders = { "Parse" };
	
	public static final String[] RatingServiceProviders = { "Amazon", "Google Play" };
	
	
	
	//Maps of Service providers to Jar links
	

	public static Map<String,String> AdsServiceJars = new HashMap<String,String>();
	public static Map<String,String> AnalyticsJars = new HashMap<String,String>();
	public static Map<String,String> PushNotificationsJars = new HashMap<String,String>();
	public static Map<String,String> CrashReportingJars = new HashMap<String,String>();
	public static Map<String,String> RatingJars = new HashMap<String,String>();
	public static Map<String,String> TwitterJars = new HashMap<String,String>();

	
	//=== Service UI ===//
	
	//Helper method to create service provider ranking drop down menu
	public static String[] createRankDropDown(int n){
		String[] dropdown = new String[n];
		for (int i = 0; i < n; i++){
			dropdown[i] = Integer.toString(i + 1);
		}
		return dropdown;
	}
	
	//Ads UI
	public static void createAdsFieldList(){
		
		String[] rankDropDown = createRankDropDown(AdsServiceProviders.length);
		Ads_UI.add(new Field(Widget.Label, "Rank Service Providers", null));
		
		for (int i = 0; i < AdsServiceProviders.length; i++){
			Ads_UI.add(new Field(Widget.Dropdown, AdsServiceProviders[i], rankDropDown));
		}
		
		Ads_UI.add(new Field(Widget.BlankLine, null, null));
		Ads_UI.add(new Field(Widget.Dropdown,"Ad Size",AdSizes));
		Ads_UI.add(new Field(Widget.TextInput,"Unit ID",null));
		
		//...
	}
	
	//Analytics UI
	public static void createAnalyticsFieldList(){
		
		String[] rankDropDown = createRankDropDown(AnalyticsServiceProviders.length);
		Analytics_UI.add(new Field(Widget.Label, "Rank Service Providers", null));
		
		for (int i = 0; i < AnalyticsServiceProviders.length; i++){
			Analytics_UI.add(new Field(Widget.Dropdown, AnalyticsServiceProviders[i], rankDropDown));
		}
		
		Analytics_UI.add(new Field(Widget.BlankLine, null, null));
		Analytics_UI.add(new Field(Widget.TextInput,"App ID", null));
		Analytics_UI.add(new Field(Widget.TextInput,"Client Key", null));
		
		//...
	}
	
	//Crash Reporting UI
	public static void createCrashReportingFieldList(){
		
		String[] rankDropDown = createRankDropDown(CrashServiceProviders.length);
		Crash_Reporting_UI.add(new Field(Widget.Label, "Rank Service Providers", null));
		
		for (int i = 0; i < CrashServiceProviders.length; i++){
			Crash_Reporting_UI.add(new Field(Widget.Dropdown, CrashServiceProviders[i], rankDropDown));
		}

		Crash_Reporting_UI.add(new Field(Widget.BlankLine, null, null));
		Crash_Reporting_UI.add(new Field(Widget.TextInput,"App ID", null));
		
		//....
	}
	

	//Push Notification UI
	public static void createPushNotificationsFieldList(){
		
		String[] rankDropDown = createRankDropDown(PushServiceProviders.length);
		Push_Notifications_UI.add(new Field(Widget.Label, "Rank Service Providers", null));
		
		for (int i = 0; i < PushServiceProviders.length; i++){
			Push_Notifications_UI.add(new Field(Widget.Dropdown, PushServiceProviders[i], rankDropDown));
		}

		Push_Notifications_UI.add(new Field(Widget.BlankLine, null, null));		
		Push_Notifications_UI.add(new Field(Widget.TextInput,"App ID", null));	
		Push_Notifications_UI.add(new Field(Widget.TextInput,"Client Key", null));
		
		//....
	}
	
	
	//Ratings UI
	public static void createRatingFieldList(){
		
		String[] rankDropDown = createRankDropDown(RatingServiceProviders.length);
		Rating_UI.add(new Field(Widget.Label, "Rank Service Providers", null));
		
		for (int i = 0; i < RatingServiceProviders.length; i++){
			Rating_UI.add(new Field(Widget.Dropdown, RatingServiceProviders[i], rankDropDown));
		}

		Rating_UI.add(new Field(Widget.BlankLine, null, null));	
		Rating_UI.add(new Field(Widget.TextInput,"URI", null));
		
		//....
	}
	
	
	//Twitter UI
	public static void createTwitterFieldList(){
		
		Twitter_UI.add(new Field(Widget.TextInput,"Consumer Key", null));
		Twitter_UI.add(new Field(Widget.TextInput,"Consumer Secret", null));
	}
	
	//Popular the jar maps
	
	public static void createJarMap(){
		
		//jars for ads
		
		AdsServiceJars.put("Admob", "url1");
		
		AdsServiceJars.put("AdSense", "url2");
		
		AdsServiceJars.put("Burstly", "url3");
		
		
		//.....
		
	}
	

	public static void populateUIData(){
		
		createAdsFieldList();
		
		createAnalyticsFieldList();
		
		createCrashReportingFieldList();
		
		createPushNotificationsFieldList();
		
		createRatingFieldList();
		
		createTwitterFieldList();
		
		createJarMap();
		
		
	}
	

	
	//public static String[] Services = {"Ads","Analytics","Crash Reporting","Push Notification","Twitter" };
	
	//public static Services[] PluginServices = new Services[]
	//{Services.Ads, Services.Analytics, Services.Crash_Reporting, Services.Push_Notifications, Services.Rating, Services.Twitter};

	static final String LOG_TAG =  PointAServiceConstants.class.getSimpleName();

	// ===========================================================
	// Fields
	// ===========================================================


	

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

}