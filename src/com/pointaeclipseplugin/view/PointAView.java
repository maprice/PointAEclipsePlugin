package com.pointaeclipseplugin.view;

import java.awt.Button;
import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

import com.pointaeclipseplugin.model.PointAServiceConstants;
import com.pointaeclipseplugin.model.PointAServiceConstants.Services;


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
	private void makeTextInput(Composite c, String label, final Services service, final int index){
		new Label(c, SWT.LEFT).setText(label);
		final Text text = new Text(c, SWT.BORDER);
		
		  text.addListener(SWT.FocusOut, new Listener() {
		      public void handleEvent(Event e) {
		    	  
		    		switch(service){
					
					case Ads:
						//Control AdsTabControl = makeTabFields(...);
						PointAServiceConstants.Ads_UI.get(index).actual_value = text.getText();
						
						System.out.println(PointAServiceConstants.Ads_UI.get(index).actual_value);
						
						break;
						
					case Analytics:
						
						PointAServiceConstants.Analytics_UI.get(index).actual_value = text.getText();
						
						System.out.println(PointAServiceConstants.Analytics_UI.get(index).actual_value);
						
						break;
					
					case CrashReporter:
						
						PointAServiceConstants.Crash_Reporting_UI.get(index).actual_value = text.getText();
						
						System.out.println(PointAServiceConstants.Crash_Reporting_UI.get(index).actual_value);
						
						break;
					
					case Rating:
						
						PointAServiceConstants.Rating_UI.get(index).actual_value = text.getText();
						
						System.out.println(PointAServiceConstants.Rating_UI.get(index).actual_value);
						
						break;
						
					case Push:
						
						PointAServiceConstants.Push_Notifications_UI.get(index).actual_value = text.getText();
						
						System.out.println(PointAServiceConstants.Push_Notifications_UI.get(index).actual_value);
						
						break;
						
					case Twitter:
						
						PointAServiceConstants.Twitter_UI.get(index).actual_value = text.getText();
						
						System.out.println(PointAServiceConstants.Twitter_UI.get(index).actual_value);
						
						break;
				    
					default:
				    

						
					}
		    	  
		      }
		    });
		
	}
	
	
	private void makeDropdown(Composite c, String label, String [] options, final Services service, final int index){
		new Label(c,SWT.LEFT).setText(label);
		final Combo combo = new Combo(c, SWT.DROP_DOWN);
		combo.setItems(options);
		
		combo.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				
				switch(service){
				
				case Ads:
					//Control AdsTabControl = makeTabFields(...);
					PointAServiceConstants.Ads_UI.get(index).actual_value = combo.getText();
					
					System.out.println(PointAServiceConstants.Ads_UI.get(index).actual_value);
					
					break;
					
					
				case Analytics:
					
					PointAServiceConstants.Analytics_UI.get(index).actual_value = combo.getText();
					
					System.out.println(PointAServiceConstants.Analytics_UI.get(index).actual_value);
					
					break;
				
				case CrashReporter:
					
					PointAServiceConstants.Crash_Reporting_UI.get(index).actual_value = combo.getText();
					
					System.out.println(PointAServiceConstants.Crash_Reporting_UI.get(index).actual_value);
					
					break;
				
				case Rating:
					
					PointAServiceConstants.Rating_UI.get(index).actual_value = combo.getText();
					
					System.out.println(PointAServiceConstants.Rating_UI.get(index).actual_value);
					
					break;
					
				case Push:
					
					PointAServiceConstants.Push_Notifications_UI.get(index).actual_value = combo.getText();
					
					System.out.println(PointAServiceConstants.Push_Notifications_UI.get(index).actual_value);
					
					break;
					
				case Twitter:
					
					PointAServiceConstants.Twitter_UI.get(index).actual_value = combo.getText();
					
					System.out.println(PointAServiceConstants.Twitter_UI.get(index).actual_value);
					
					break;
			    
				default:
			    

					
				}
			}
		});
		
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
		
		int i = 0;
		
		for(PointAServiceConstants.Field Field: FieldList){
			
		//	System.out.println(FieldList.indexOf(Field));
		
			switch(Field.widget){
			
			case Dropdown:
				
				makeDropdown(composite, Field.label, Field.values, Field.service, FieldList.indexOf(Field));

				continue;
				
			case TextInput:
				
				makeTextInput(composite, Field.label, Field.service, FieldList.indexOf(Field));

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
			
			case CrashReporter:
				
		        TabItem Tab_Crash = createTab(tabFolder, "Crash Reporting", TabControl(tabFolder,PointAServiceConstants.Crash_Reporting_UI));
		    
		        continue;
			
			case Rating:
				
		        TabItem Tab_Rating = createTab(tabFolder, "Rating", TabControl(tabFolder,PointAServiceConstants.Rating_UI));

		        continue;
				
			case Push:
				
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
		//viewer.getControl().setFocus();
	}
}