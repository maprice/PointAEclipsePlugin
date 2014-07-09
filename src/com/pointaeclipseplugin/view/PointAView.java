package com.pointaeclipseplugin.view;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

public class PointAView extends ViewPart {
	
	public static final String ID = "PointAEclipsePlugin.view";
	private TableViewer viewer;
	
	//Service Specific Variables
	String[] AdsServiceProviders = { "Admob", "AdSense", "Burstly" };
	String[] AdSizes = { "Banner", "Interstitial" };
	
	String[] AnalyticsServiceProviders = { "Google Analytics", "Parse Analytics" };
	String[] CrashServiceProviders = { "BugSense", "BugSnag", "Crittercism" };
	String[] PushServiceProviders = { "Parse" };
	String[] RatingServiceProviders = { "Amazon", "Google Play" };
	
	class ViewContentProvider implements IStructuredContentProvider{
		public void inputChanged(Viewer v, Object oldInput, Object newInput){}

		public void dispose(){}

		public Object[] getElements(Object parent){
			if (parent instanceof Object[]){
				return (Object[]) parent;
			}
	        return new Object[0];
		}
	}
	
	private void makeTextInput(Composite c, String label){
		new Label(c, SWT.LEFT).setText(label);
		new Text(c, SWT.BORDER);
	}
	
	private void makeDropdown(Composite c, String label, String [] options){
		new Label(c,SWT.LEFT).setText(label);
		new Combo(c, SWT.DROP_DOWN).setItems(options);
	}

	private Control AdsTabControl(TabFolder tabFolder){
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		Composite composite = new Composite(tabFolder, SWT.NONE);
		composite.setLayout(gridLayout);
		makeDropdown(composite, "Service Provider", AdsServiceProviders);
		makeDropdown(composite, "Ad Size", AdSizes);
		makeTextInput(composite, "Unit ID");
		return composite; 
	}
	
	private Control AnalyticsTabControl(TabFolder tabFolder){
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		Composite composite = new Composite(tabFolder, SWT.NONE);
		composite.setLayout(gridLayout);
		makeDropdown(composite, "Service Provider", AdsServiceProviders);
		makeTextInput(composite, "App ID");
		makeTextInput(composite, "Client Key");
		return composite;   
	}
	
	private Control CrashTabControl(TabFolder tabFolder){
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		Composite composite = new Composite(tabFolder, SWT.NONE);
		composite.setLayout(gridLayout);
		makeDropdown(composite, "Service Provider", CrashServiceProviders);
		makeTextInput(composite, "App ID");
		return composite;   
	}
	
	private Control RatingTabControl(TabFolder tabFolder){
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		Composite composite = new Composite(tabFolder, SWT.NONE);
		composite.setLayout(gridLayout);
		makeDropdown(composite, "Service Provider", RatingServiceProviders);
		makeTextInput(composite, "URI");
		return composite;   
	}
	
	private Control PushNotificationsTabControl(TabFolder tabFolder){
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		Composite composite = new Composite(tabFolder, SWT.NONE);
		composite.setLayout(gridLayout);
		makeDropdown(composite, "Service Provider", PushServiceProviders);
		makeTextInput(composite, "App ID");
		makeTextInput(composite, "Client Key");
		return composite;   
	}
	
	private Control TwitterTabControl(TabFolder tabFolder){
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		Composite composite = new Composite(tabFolder, SWT.NONE);
		composite.setLayout(gridLayout);
		makeTextInput(composite, "Consumer Key");
		makeTextInput(composite, "Consumer Secret");
		return composite;   
	}
	
	public void createPartControl(Composite parent) {
		final TabFolder tabFolder = new TabFolder(parent, SWT.NONE);
		
		//Ads Tab
		TabItem Tab_Ads = new TabItem(tabFolder, SWT.NONE);
        Tab_Ads.setText("Ads");
        Tab_Ads.setToolTipText("Ads Configurations");  
        Tab_Ads.setControl(AdsTabControl(tabFolder));
        
        //Analytics Tab
        TabItem Tab_Analytics = new TabItem(tabFolder, SWT.NONE);
        Tab_Analytics.setText("Analytics");
        Tab_Analytics.setToolTipText("Analytics Configurations");
        Tab_Analytics.setControl(AnalyticsTabControl(tabFolder));
        
        //Crash Reporting Tab
        TabItem Tab_Crash = new TabItem(tabFolder, SWT.NONE);
        Tab_Crash.setText("Crash Reporting");
        Tab_Crash.setToolTipText("Crash Reporting Configurations");
        Tab_Crash.setControl(CrashTabControl(tabFolder));
        
        //Rating Tab
        TabItem Tab_Rating = new TabItem(tabFolder, SWT.NONE);
        Tab_Rating.setText("Rating");
        Tab_Rating.setToolTipText("Rating Configurations");
        Tab_Rating.setControl(RatingTabControl(tabFolder));
        
        //Push Notifications Tab
        TabItem Tab_PushNotifications = new TabItem(tabFolder, SWT.NONE);
        Tab_PushNotifications.setText("Push Notifications");
        Tab_PushNotifications.setToolTipText("Push Notifications Configurations");
        Tab_PushNotifications.setControl(PushNotificationsTabControl(tabFolder));
        
        //Twitter Tab
        TabItem Tab_Twitter = new TabItem(tabFolder, SWT.NONE);
        Tab_Twitter.setText("Twitter");
        Tab_Twitter.setToolTipText("Twitter Configurations");
        Tab_Twitter.setControl(TwitterTabControl(tabFolder));
	}

	public void setFocus() {
		viewer.getControl().setFocus();
	}
}