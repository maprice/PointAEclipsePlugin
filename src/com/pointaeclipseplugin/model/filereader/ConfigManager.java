package com.pointaeclipseplugin.model.filereader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import com.pointaeclipseplugin.model.ProviderMetaData;
import com.pointaeclipseplugin.model.constants.MasterProviderInfo.Services;



/**
 * <Class Description>
 * @version 1.0
 * @since July 08, 2014
 *
 */

public class ConfigManager {

	// ===========================================================
	// Constants
	// ===========================================================

	static final String LOG_TAG =  ConfigManager.class.getSimpleName();

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	public ConfigManager(){
		mProviders = new HashMap<Services, ArrayList<ProviderMetaData>>();
	}

	// ===========================================================
	// Methods
	// ===========================================================
	private final HashMap<Services, ArrayList<ProviderMetaData>> mProviders;


	public void parseConfig() {
		// Populate mProviders
		//	Log.d(LOG_TAG,"Max: Launching Parse...");
		try {

			Services curType = null;
			String curProvider = "default provider";
			String curKey = "default key";
			String curValue = "default value";

			int curPriority = 1; // if unspecified, 1 is exactly what we want :)
			// if these are still default inside the map, something is wrong...

			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			factory.setNamespaceAware(true);
			// XmlPullParser xpp = factory.newPullParser();

			//XmlPullParser xrp = defaultContext.getResources().getXml(R.xml.config);

			XmlPullParser xrp = factory.newPullParser();
			//File localfile = new File("C:/temp/config.xml");
			//xrp.setInput("<?xml version=\"1.0\" encoding=\"UTF-8\"?><pointAconfig><service><type>Ads</type><provider>Admob</provider><priority>1</priority><Unit ID>3</Unit ID><Size>2</Size></service></pointAconfig>");
			xrp.setInput(new FileReader("C:/temp/config.xml"));

			// InputStream inputStream = defaultContext.getResources().openRawResource(R.raw.config);

			//	Log.d(LOG_TAG, "Parser: input set");


			//Log.d(LOG_TAG, "Parser: Starting to read");
			int eventType = xrp.next(); //<start document>
			eventType = xrp.next(); // <pointAconfig>

			while (eventType != XmlPullParser.END_DOCUMENT)
			{
				eventType = xrp.next(); // <service>

				if (eventType == XmlPullParser.START_TAG && xrp.getName().trim().equalsIgnoreCase("service"))
				{
					//	Log.d(LOG_TAG, "Parser: Found a service!");

					eventType = xrp.next(); //<type>

					if (eventType == XmlPullParser.START_TAG)
					{
						eventType = xrp.next(); // "type"

						//	Log.d(LOG_TAG,"Parser: Found a type:" + xrp.getText().trim() + "!");

						if (xrp.getText().trim().equalsIgnoreCase("ads"))
						{
							curType = Services.Ads;
						}
						else if(xrp.getText().trim().equalsIgnoreCase("analytics"))
						{
							curType = Services.Analytics;
						}
						else if(xrp.getText().trim().equalsIgnoreCase("crashreporter"))
						{
							curType = Services.CrashReporter;
						}
						else if(xrp.getText().trim().equalsIgnoreCase("rating"))
						{
							curType = Services.Rating;
						}
						else if(xrp.getText().trim().equalsIgnoreCase("push"))
						{
							curType = Services.Push;
						}
						else
						{
							//	Log.e(LOG_TAG,"Parser Error: Found an unrecognized service type: " + xrp.getText());
						}

						eventType = xrp.next(); //</type>

						if (eventType != XmlPullParser.END_TAG || !xrp.getName().trim().equalsIgnoreCase("type"))
						{
							//	Log.e(LOG_TAG, "Parser Error: Expected </type>, found something that isn't an end tag, or an end tag for something that is not type.");
						}

						eventType = xrp.next(); //<provider>

						if (eventType == XmlPullParser.START_TAG && xrp.getName().trim().equalsIgnoreCase("provider"))
						{
							eventType = xrp.next(); // "provider"
							if (eventType == XmlPullParser.TEXT)
							{
								//	Log.d(LOG_TAG, "Parser: Got provider: " + xrp.getText());
								curProvider = xrp.getText();
							}
							else
							{
								//		Log.e(LOG_TAG, "Parser Error: Expected provider name; found something that isn't text");
							}

							eventType = xrp.next(); // </provider>

							if (eventType != XmlPullParser.END_TAG || xrp.getName().trim().equalsIgnoreCase("/provider"))
							{
								//			Log.e(LOG_TAG, "Parser Error: Expected </provider>, found " + xrp.getName() + "!");
							}

							HashMap<Integer, ProviderMetaData> curParams = new HashMap<Integer, ProviderMetaData>();
							HashMap<String, String> simpleParamsMap = new HashMap<String, String>();

							eventType = xrp.next(); // <priority>

							if (eventType == XmlPullParser.START_TAG && xrp.getName().trim().equalsIgnoreCase("priority"))
							{
								eventType = xrp.next(); // "priority"
								if (eventType == XmlPullParser.TEXT)
								{
									//			Log.d(LOG_TAG, "Parser: Got priority: " + xrp.getText());
									curPriority = Integer.parseInt(xrp.getText());
								}
								else
								{
									//			Log.e(LOG_TAG, "Parser Error: Expected priority; found something that isn't text");
								}

								eventType = xrp.next(); // </priority>

								if (eventType != XmlPullParser.END_TAG || xrp.getName().trim().equalsIgnoreCase("/priority"))
								{
									//			Log.e(LOG_TAG, "Parser Error: Expected </priority>");
								}

								if (curParams.get(curPriority) != null)
								{
									//			Log.e(LOG_TAG, "Error: attempting to overwrite an existing priority.");
								}
								curParams.put(curPriority, new ProviderMetaData(curProvider, new HashMap<String, String>()));

								// loop to populate parameters
								while (eventType != XmlPullParser.END_DOCUMENT)
								{	
									eventType = xrp.next(); // <key>

									if (eventType == XmlPullParser.START_TAG)
									{
										curKey = xrp.getName();
										//			Log.d(LOG_TAG, "Parser: Got a key: " + xrp.getName());
									} 
									else if ( eventType == XmlPullParser.END_TAG && xrp.getName().trim().equalsIgnoreCase("service"))
									{
										//			Log.d(LOG_TAG, "Parser: Found the end of a service: " + curProvider);
										break;
									}

									eventType = xrp.next(); // "value"

									if (eventType == XmlPullParser.TEXT)
									{
										curValue = xrp.getText();
										//		Log.d(LOG_TAG, "Parser: Got a value: " + xrp.getText());
									}
									eventType = xrp.next(); // </key>

									if (curParams.get(curPriority).getParams() == null)
									{
										//		Log.d(LOG_TAG, "getParams is null");
									}
									curParams.get(curPriority).getParams().put(curKey, curValue);
									simpleParamsMap.put(curKey, curValue);
								}
								TreeMap<Integer, ProviderMetaData> sortedParams = new TreeMap<Integer, ProviderMetaData>();
								for (Integer i: curParams.keySet())
								{
									//sortedParams.put(i, curParams.get(i));
									sortedParams.put(i, new ProviderMetaData(curProvider, simpleParamsMap));
								}
								for (Integer i: curParams.keySet())
								{
									//		Log.d(LOG_TAG, "Inserting priority: " + i + ", thing: " + curProvider);
								}
								//mProviders.put(curType, sortedParams.values().toArray(new ProviderMetaData[sortedParams.size()]));
								// outdated code - only allows one provider per service

								//	Log.d(LOG_TAG, "curtype: " + curType.toString());
								//	Log.d(LOG_TAG, "curPriority: " + curPriority);

								if (mProviders.get(curType) == null)
								{
									mProviders.put(curType, new ArrayList<ProviderMetaData>());
								}


								mProviders.get(curType).add(new ProviderMetaData(curProvider, simpleParamsMap));
								//mProviders.get(curType)[curPriority] = new ProviderMetaData();
								//Log.d(LOG_TAG, "Array Value: " + mProviders.get(curType).get(curPriority));
							}

						}
						else {
							//		Log.e(LOG_TAG, "Parser Error: Expected <provider>, found something different");
						}

					} else
					{
						//	Log.e(LOG_TAG, "Parser: Error: Expected <service>, found instead: " + xrp.getName());
						//break; // maybe instead of breaking we just move on to the next service?
					}

				}

			}

		} catch (XmlPullParserException e) {
			//	Log.e(LOG_TAG, "Parser Error: XMLPullParser Exception");
			e.printStackTrace();
		} catch (IOException e) {
			//	Log.e(LOG_TAG, "Parser Error: IO Exception");
			e.printStackTrace();
		}



	}

	public void updateConfig(HashMap<Services, ArrayList<ProviderMetaData>> mProviders) {
		// TODO Auto-generated method stub

	}

	public HashMap<Services, ArrayList<ProviderMetaData>> getConfig(){

		// just returning non-empty config so i dont get errors
		HashMap<Services, ArrayList<ProviderMetaData>> pretendConfig = new HashMap<Services, ArrayList<ProviderMetaData>>();

		for (Services dir : Services.values()) {
			ArrayList<ProviderMetaData> serviceList = new ArrayList<ProviderMetaData>();

			pretendConfig.put(dir, serviceList);
		}

		return mProviders;
	}




}