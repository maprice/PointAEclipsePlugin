package com.pointaeclipseplugin.view;

import java.util.List;

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

import com.pointaeclipseplugin.model.PointAServiceConstants;
import com.pointaeclipseplugin.model.PointAServiceConstants.Services;
import com.pointaeclipseplugin.model.PointAServiceConstants.Widget;


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
	private void makeLabel(Composite c, String label){
		new Label(c, SWT.LEFT).setText(label);
		new Label(c, SWT.LEFT).setText("");
	}
	private void makeBlankLine(Composite c){
		new Label(c, SWT.LEFT).setText("");
		new Label(c, SWT.LEFT).setText("");
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
	// Tab Control Configuration
	// ===========================================================
	
	
	private Control TabControl(TabFolder tabFolder, List<PointAServiceConstants.Field> FieldList ){
		
		Composite composite = getGridComposite(tabFolder);
		
		for(PointAServiceConstants.Field Field: FieldList){
			
			switch(Field.widget){
			
			case Dropdown: 
				
				makeDropdown(composite, Field.label, Field.values);
				
				continue;
				
			case TextInput:
				
				makeTextInput(composite, Field.label);
				
				continue;
			
			case Label:
				
				makeLabel(composite, Field.label);
				
				continue;
				
			case BlankLine:
				
				makeBlankLine(composite);
				
				continue;
				
			default:
				
				continue;
			
			}
			
		}
		
		return composite;
		
	}

	// ===========================================================
	// Populate Tab Folder
	// ===========================================================
	public void createPartControl(Composite parent) {
		final TabFolder tabFolder = new TabFolder(parent, SWT.NONE);
		
		//Create Tabs
		
		PointAServiceConstants.populateUIData();
		
		
		for(Services pluginservice:PointAServiceConstants.Services.values())
		{
			
			switch(pluginservice){
			
			case Ads:
				//Control AdsTabControl = makeTabFields(...);
				TabItem Tab_Ads = createTab(tabFolder, "Ads", TabControl(tabFolder, PointAServiceConstants.Ads_UI));
				continue;
				
			case Analytics:
				
				TabItem Tab_Analytics = createTab(tabFolder, "Analytics", TabControl(tabFolder,PointAServiceConstants.Analytics_UI));
				continue;
			
			case Crash_Reporting:
				
		        TabItem Tab_Crash = createTab(tabFolder, "Crash Reporting", TabControl(tabFolder,PointAServiceConstants.Crash_Reporting_UI));
		    
		        continue;
			
			case Rating:
				
		        TabItem Tab_Rating = createTab(tabFolder, "Rating", TabControl(tabFolder,PointAServiceConstants.Rating_UI));

		        continue;
				
			case Push_Notifications:
				
		        TabItem Tab_PushNotifications = createTab(tabFolder, "Push Notifications", TabControl(tabFolder,PointAServiceConstants.Push_Notifications_UI));

		        continue;
				
			case Twitter:
				
		        TabItem Tab_Twitter = createTab(tabFolder, "Twitter", TabControl(tabFolder,PointAServiceConstants.Twitter_UI));
		    
		        continue;
		    
			default:
		    	
		    	continue;

				
			}
			
			
		}
	}

	public void setFocus() {
		viewer.getControl().setFocus();
	}
}