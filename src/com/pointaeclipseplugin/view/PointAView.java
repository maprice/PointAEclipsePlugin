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

import com.pointaeclipseplugin.controller.PointAController;
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
	}
	private void makeDropDown(Composite c, String label, String[] options){
		new Label(c,SWT.LEFT).setText(label);
		final Combo combo = new Combo(c, SWT.DROP_DOWN);
		combo.setItems(options);
	}
	private void makeLabelCheckBox(Composite c, String label){
		new Label(c, SWT.LEFT).setText(label);
		new Button(c, SWT.CHECK).setText("Disable");
	}
	private void makeBlankLine(Composite c){
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
		gridLayout.numColumns = 2;
		c.setLayout(gridLayout);
		
		String[] providers = new String[mProviders.length];
		String[] priorities = new String[mProviders.length];
		
		for(int i = 0; i < mProviders.length; i++){
			providers[i] = mProviders[i].getName();
			priorities[i] = Integer.toString(i + 1);
		}
		
		makeBlankLine(c);
		
		Map<String, String> mParams = new HashMap<String, String>();
		for(int i = 0; i < mProviders.length; i++){
			
			mParams = mProviders[i].getParams();
			
			makeLabelCheckBox(c, providers[i]);
			makeDropDown(c, "Priority", priorities);
			
			for (String key : mParams.keySet()){
				if (!key.equals("Priority")) makeTextInput(c, key);
			}
			makeBlankLine(c);
		}
		
		return c;
	}

	// ===========================================================
	// Populate Tab Folder
	// ===========================================================
	public void createPartControl(Composite parent) {
	
		PointAController paController = new PointAController(null, null);
		HashMap<Services, ProviderMetaData[]> mProviders = paController.getMProviders();
		
		final TabFolder tabFolder = new TabFolder(parent, SWT.NONE);
		
		//Create Tabs		
		PointAServiceConstants.populateUIData();
		
		for(Services pluginservice:PointAServiceConstants.Services.values()){
			
			TabItem test = createTab(tabFolder, pluginservice.name(), TabControl(tabFolder, mProviders.get(pluginservice)));
		}
	}

	public void setFocus() {
		//viewer.getControl().setFocus();
	}
}