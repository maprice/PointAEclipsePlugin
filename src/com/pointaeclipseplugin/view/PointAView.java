package com.pointaeclipseplugin.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

import com.pointaeclipseplugin.model.ProviderMetaData;
import com.pointaeclipseplugin.model.MasterProviderDoc;
import com.pointaeclipseplugin.model.PointAServiceConstants;
import com.pointaeclipseplugin.model.Provider;
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
	
	private Control tabControl(TabFolder tabFolder, HashMap<Services, Provider> FieldList ){
		Composite composite = getGridComposite(tabFolder);
		return composite;
	}
	
	// ===========================================================
	// Helper Methods
	// ===========================================================
	
	private void makeTextInput(Composite c, String label){
		new Label(c, SWT.LEFT).setText(label);
		final Text text = new Text(c, SWT.BORDER);
		new Label(c, SWT.LEFT).setText("");
	}
	private void makeDropDown(Composite c, String label, String[] options){
		new Label(c,SWT.LEFT).setText(label);
		final Combo combo = new Combo(c, SWT.DROP_DOWN);
		combo.setItems(options);
		new Label(c, SWT.LEFT).setText("");
	}
	private void makeLabel(Composite c, String label){
		new Label(c, SWT.LEFT).setText(label);
		new Label(c, SWT.LEFT).setText("");
		new Label(c, SWT.LEFT).setText("");
	}
	private void makeBlankLine(Composite c){
		new Label(c, SWT.LEFT).setText("");
		new Label(c, SWT.LEFT).setText("");
		new Label(c, SWT.LEFT).setText("");
	}
	
	//GridLayout Composite Generator
	private Composite getGridComposite(TabFolder tabFolder){
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 3;
		Composite composite = new Composite(tabFolder, SWT.NONE);
		composite.setLayout(gridLayout);
		return composite;
	}
	
	//custom composite
	private Control TestComposite(TabFolder tabFolder){
		Composite c = new Composite(tabFolder, SWT.NONE);
		
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 3;
		
		c.setLayout(gridLayout);
		
		/*new Label(c, SWT.NONE).setText("Provider");
		new Combo(c, SWT.DROP_DOWN);
		new Button(c, SWT.CHECK).setText("Disable");
		
		new Label(c, SWT.NONE).setText("Priority");
		new Combo(c, SWT.DROP_DOWN);
		new Label(c, SWT.NONE).setText("");*/
		
		
		return c;
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
	private Control TabControl(TabFolder tabFolder, ProviderMetaData[] mProviders){
		Composite c = getGridComposite(tabFolder);
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 3;
		c.setLayout(gridLayout);
		
		String[] providers = new String[mProviders.length];
		String[] priorities = new String[mProviders.length];
		
		for(int i = 0; i < mProviders.length; i++){
			providers[i] = mProviders[i].getName();
			priorities[i] = Integer.toString(i + 1);
		}
		
		/*new Label(c, SWT.NONE).setText("Provider");
		Combo providers_combo = new Combo(c, SWT.DROP_DOWN);
		providers_combo.setItems(providers);*/
		
		//new Button(c, SWT.CHECK).setText("Disable");
		//new Label(c, SWT.NONE).setText("Priority");
		//Combo priorities_combo = new Combo(c, SWT.DROP_DOWN);
		//priorities_combo.setItems(priorities);
		
		new Label(c, SWT.NONE).setText("");
		
		makeBlankLine(c);
		
		Map<String, String> mParams = new HashMap<String, String>();
		for(int i = 0; i < mProviders.length; i++){
			
			mParams = mProviders[i].getParams();
			new Label(c, SWT.NONE).setText(providers[i]);
			new Button(c, SWT.CHECK).setText("Disable");
			new Label(c, SWT.NONE).setText("");
			
			new Label(c, SWT.NONE).setText("Priority");
			Combo priorities_combo = new Combo(c, SWT.DROP_DOWN);
			priorities_combo.setItems(priorities);
			new Label(c, SWT.NONE).setText("");
			
			for (String key : mParams.keySet()){
				makeTextInput(c, key);
			}
			makeBlankLine(c);
		}
		
		return c;
	}

	
	private HashMap<Services, ProviderMetaData[]> getMProviders(){
		
		//Get data from MasterProviderDoc
		MasterProviderDoc mpd = new MasterProviderDoc();
		HashMap<Services, List<Provider>> mpdProviders = mpd.getProviders();	
		
		//Create instance of mProviders to be returned
		HashMap<Services, ProviderMetaData[]> mProviders = new HashMap<Services, ProviderMetaData[]>();
		
		int num_providers;
		int num_fields;
		
		//Populate mProviders
		
		//Loop through all Service Types
		for(Services pluginservice:PointAServiceConstants.Services.values()){
			List<Provider> providerList = mpdProviders.get(pluginservice);
			num_providers = providerList.size();
			
			ProviderMetaData[] providermetadata = new ProviderMetaData[num_providers];
			
			System.out.println("Looking at Service:" + pluginservice.name());
			//Loop through all Service Providers
			for(int i = 0; i < num_providers; i++){	
				Provider provider = providerList.get(i);
				
				System.out.println("Provider: " + provider.name);
				 
				num_fields = provider.params.size();
				Map<String, String> pParams = new HashMap<String, String>();
				
				//Loop through all Service Provider Fields
				for(int j = 0; j < num_fields; j++){
					System.out.println("Adding Field: " + provider.params.get(j));
					pParams.put(provider.params.get(j), null);
				}	
				providermetadata[i] = new ProviderMetaData(provider.name, pParams);
			}	
			mProviders.put(pluginservice, providermetadata);
		}
		return mProviders;
	}
	// ===========================================================
	// Populate Tab Folder
	// ===========================================================
	public void createPartControl(Composite parent) {
	
		
		HashMap<Services, ProviderMetaData[]> mProviders = getMProviders();
		
		final TabFolder tabFolder = new TabFolder(parent, SWT.NONE);
		
		//Create Tabs		
		PointAServiceConstants.populateUIData();
		
		for(Services pluginservice:PointAServiceConstants.Services.values()){
			
			//private Control TabControl(TabFolder tabFolder, Map<String, String> mParams){
			TabItem test = createTab(tabFolder, pluginservice.name(), TabControl(tabFolder, mProviders.get(pluginservice)));
		}
		TabItem Tab_Test = new TabItem(tabFolder, SWT.NONE);
		Tab_Test.setText("Test");
		Tab_Test.setControl(TestComposite(tabFolder));
	}

	public void setFocus() {
		//viewer.getControl().setFocus();
	}
}