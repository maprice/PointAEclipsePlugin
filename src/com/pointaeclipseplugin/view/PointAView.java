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

import com.pointaeclipseplugin.PointAServiceConstants;

public class PointAView extends ViewPart {
	
	public static final String ID = "PointAEclipsePlugin.view";
	private TableViewer viewer;
	
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
	
	// ===========================================================
	// Helper Methods
	// ===========================================================
	//User Input Generator
	private void makeTextInput(Composite c, String label){
		new Label(c, SWT.LEFT).setText(label);
		new Text(c, SWT.BORDER);
	}
	private void makeDropdown(Composite c, String label, String [] options){
		new Label(c,SWT.LEFT).setText(label);
		new Combo(c, SWT.DROP_DOWN).setItems(options);
	}
	
	//GridLayout Composite Generator
	private Composite getGridComposite(TabFolder tabFolder){
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		Composite composite = new Composite(tabFolder, SWT.NONE);
		composite.setLayout(gridLayout);
		return composite;
	}
	
	//Tab configuration
	private TabItem createTab(TabFolder tabFolder, String text, Control c){
		TabItem tab = new TabItem(tabFolder, SWT.NONE);
		tab.setText(text);
		tab.setControl(c);
		return tab;
	}
	
	// ===========================================================
	// Tab Control Configuration (FIX: MAKE CONFIGURATIONS DYNAMIC) 
	// ===========================================================
	private Control AdsTabControl(TabFolder tabFolder){
		Composite composite = getGridComposite(tabFolder);
		makeDropdown(composite, "Service Provider", PointAServiceConstants.AdsServiceProviders);
		makeDropdown(composite, "Ad Size", PointAServiceConstants.AdSizes);
		makeTextInput(composite, "Unit ID");
		return composite; 
	}
	private Control AnalyticsTabControl(TabFolder tabFolder){
		Composite composite = getGridComposite(tabFolder);
		makeDropdown(composite, "Service Provider", PointAServiceConstants.AdsServiceProviders);
		makeTextInput(composite, "App ID");
		makeTextInput(composite, "Client Key");
		return composite;   
	}
	private Control CrashTabControl(TabFolder tabFolder){
		Composite composite = getGridComposite(tabFolder);
		makeDropdown(composite, "Service Provider", PointAServiceConstants.CrashServiceProviders);
		makeTextInput(composite, "App ID");
		return composite;   
	}
	private Control RatingTabControl(TabFolder tabFolder){
		Composite composite = getGridComposite(tabFolder);
		makeDropdown(composite, "Service Provider", PointAServiceConstants.RatingServiceProviders);
		makeTextInput(composite, "URI");
		return composite;   
	}
	private Control PushNotificationsTabControl(TabFolder tabFolder){
		Composite composite = getGridComposite(tabFolder);
		makeDropdown(composite, "Service Provider", PointAServiceConstants.PushServiceProviders);
		makeTextInput(composite, "App ID");
		makeTextInput(composite, "Client Key");
		return composite;   
	}
	private Control TwitterTabControl(TabFolder tabFolder){
		Composite composite = getGridComposite(tabFolder);
		makeTextInput(composite, "Consumer Key");
		makeTextInput(composite, "Consumer Secret");
		return composite;   
	}
	
	// ===========================================================
	// Populate Tab Folder
	// ===========================================================
	public void createPartControl(Composite parent) {
		final TabFolder tabFolder = new TabFolder(parent, SWT.NONE);
		
		//Create Tabs
		TabItem Tab_Ads = createTab(tabFolder, "Ads", AdsTabControl(tabFolder));
        TabItem Tab_Analytics = createTab(tabFolder, "Analytics", AnalyticsTabControl(tabFolder));
        TabItem Tab_Crash = createTab(tabFolder, "Crash Reporting", CrashTabControl(tabFolder));
        TabItem Tab_Rating = createTab(tabFolder, "Rating", RatingTabControl(tabFolder));
        TabItem Tab_PushNotifications = createTab(tabFolder, "Push Notifications", PushNotificationsTabControl(tabFolder));
        TabItem Tab_Twitter = createTab(tabFolder, "Twitter", TwitterTabControl(tabFolder));
	}

	public void setFocus() {
		viewer.getControl().setFocus();
	}
}