package com.pointaeclipseplugin.model;

import java.util.ArrayList;
import java.util.EnumMap;
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

		public Widget widget;//Dropdown, Textinput, label, etc
		public String label;
		public String[] values;
		public Services service;
		public String actual_value;
		
		
		public Field(Widget widget, String label, String [] values, Services service){
			this.widget = widget;
			this.label = label;
			this.values = values;
			this.actual_value = null;
			this.service = service;
			
			
		}
	}

	//List of Fields for each service
	public static List<Field> Ads_UI = new ArrayList<Field>();
	public static List<Field> Analytics_UI = new ArrayList<Field>();
	public static List<Field> Crash_Reporting_UI = new ArrayList<Field>();
	public static List<Field> Push_Notifications_UI = new ArrayList<Field>();
	public static List<Field> Rating_UI = new ArrayList<Field>();
	public static List<Field> Twitter_UI = new ArrayList<Field>();
	
	//Wrapper object to be passed to XML writer
	public static List<List<Field>> Fields = new ArrayList<List<Field>>();
	
	//static //public static List<List<Field>> ConfigData = new ArrayList<List<Field>>();
	
	
	//enum map is used to store the mapping from service type to the list
	public static EnumMap<Services, List<Field>> ConfigData = new EnumMap<Services,List<Field>>(Services.class);
	
	
	public static enum Services {
		
		Ads, Analytics, Crash_Reporting, Push_Notifications, Rating, Twitter;
	
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
	
	
	public static void packageUIData(){
	
		for(Services service:PointAServiceConstants.Services.values()){
			
			
    		switch(service){
			
			case Ads:
				
				ConfigData.put(service, Ads_UI);
				
				continue;
				
			case Analytics:
				
				ConfigData.put(service, Analytics_UI);
				
				continue;
			
			case Crash_Reporting:
				
				
				ConfigData.put(service, Crash_Reporting_UI);
				
				continue;
			
			case Rating:
				
				ConfigData.put(service,Rating_UI);
				
				continue;
				
			case Push_Notifications:
				
				
				ConfigData.put(service, Push_Notifications_UI);
				
				continue;
				
			case Twitter:
				
				
				ConfigData.put(service, Twitter_UI);
				
				continue;
		    
			default:
			
			
    		}
		}
	}
		
		
	
	
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
		Ads_UI.add(new Field(Widget.Label, "Rank Service Providers", null, Services.Ads));
		
		for (int i = 0; i < AdsServiceProviders.length; i++){
			Ads_UI.add(new Field(Widget.Dropdown, AdsServiceProviders[i], rankDropDown, Services.Ads));
		}
		
		Ads_UI.add(new Field(Widget.BlankLine, null, null, Services.Ads));
		Ads_UI.add(new Field(Widget.Dropdown,"Ad Size",AdSizes, Services.Ads));
		Ads_UI.add(new Field(Widget.TextInput,"Unit ID",null, Services.Ads));
		
		//...
	}
	
	//Analytics UI
	public static void createAnalyticsFieldList(){
		
		String[] rankDropDown = createRankDropDown(AnalyticsServiceProviders.length);
		Analytics_UI.add(new Field(Widget.Label, "Rank Service Providers", null, Services.Analytics));
		
		for (int i = 0; i < AnalyticsServiceProviders.length; i++){
			Analytics_UI.add(new Field(Widget.Dropdown, AnalyticsServiceProviders[i], rankDropDown,Services.Analytics));
		}
		
		Analytics_UI.add(new Field(Widget.BlankLine, null, null, Services.Analytics));
		Analytics_UI.add(new Field(Widget.TextInput,"App ID", null, Services.Analytics));
		Analytics_UI.add(new Field(Widget.TextInput,"Client Key", null, Services.Analytics));
		
		//...
	}
	
	//Crash Reporting UI
	public static void createCrashReportingFieldList(){
		
		String[] rankDropDown = createRankDropDown(CrashServiceProviders.length);
		Crash_Reporting_UI.add(new Field(Widget.Label, "Rank Service Providers", null, Services.Crash_Reporting));
		
		for (int i = 0; i < CrashServiceProviders.length; i++){
			Crash_Reporting_UI.add(new Field(Widget.Dropdown, CrashServiceProviders[i], rankDropDown, Services.Crash_Reporting));
		}

		Crash_Reporting_UI.add(new Field(Widget.BlankLine, null, null, Services.Crash_Reporting));
		Crash_Reporting_UI.add(new Field(Widget.TextInput,"App ID", null, Services.Crash_Reporting));
		
		
		//....
	}
	

	//Push Notification UI
	public static void createPushNotificationsFieldList(){
		
		String[] rankDropDown = createRankDropDown(PushServiceProviders.length);
		Push_Notifications_UI.add(new Field(Widget.Label, "Rank Service Providers", null, Services.Push_Notifications));
		
		for (int i = 0; i < PushServiceProviders.length; i++){
			Push_Notifications_UI.add(new Field(Widget.Dropdown, PushServiceProviders[i], rankDropDown, Services.Push_Notifications));
		}

		Push_Notifications_UI.add(new Field(Widget.BlankLine, null, null, Services.Push_Notifications));		
		Push_Notifications_UI.add(new Field(Widget.TextInput,"App ID", null, Services.Push_Notifications));	
		Push_Notifications_UI.add(new Field(Widget.TextInput,"Client Key", null, Services.Push_Notifications));
		
		//....
	}
	
	
	//Ratings UI
	public static void createRatingFieldList(){
		
		String[] rankDropDown = createRankDropDown(RatingServiceProviders.length);
		Rating_UI.add(new Field(Widget.Label, "Rank Service Providers", null, Services.Rating));
		
		for (int i = 0; i < RatingServiceProviders.length; i++){
			Rating_UI.add(new Field(Widget.Dropdown, RatingServiceProviders[i], rankDropDown, Services.Rating));
		}

		Rating_UI.add(new Field(Widget.BlankLine, null, null, Services.Rating));	
		Rating_UI.add(new Field(Widget.TextInput,"URI", null, Services.Rating));
		
		//....
	}
	
	
	//Twitter UI
	public static void createTwitterFieldList(){
		
		Twitter_UI.add(new Field(Widget.TextInput,"Consumer Key", null, Services.Twitter));
		Twitter_UI.add(new Field(Widget.TextInput,"Consumer Secret", null, Services.Twitter));
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
