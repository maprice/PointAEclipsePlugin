package com.pointaeclipseplugin.view;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;

import com.pointaeclipseplugin.controller.PointAController;
import com.pointaeclipseplugin.model.ProviderMetaData;
import com.pointaeclipseplugin.view.table.ParameterTableViewer;



public class ServiceView extends ViewPart {
	// ===========================================================
	// Constants
	// ===========================================================

	public static final String ID = "de.vogella.jface.tableviewer.view";

	private final SelectionListener mViewListener = new SelectionListener() {

		@Override
		public void widgetSelected(SelectionEvent e) {	
			if(e.widget == mPriorityCombo){
				mController.onPriorityChanged(Integer.valueOf(mPriorityCombo.getText()));
			}
			else if(e.widget == mSaveButton){
				mController.onSaveButtonPressed();
			}
			else if(e.widget == mRevertButton){
				mController.onRevertButtonPressed();
			}
			else if(e.widget == mEnableButton){
				mController.onEnableChanged(mEnableButton.getSelection());	
				setProviderEnabled(mEnableButton.getSelection());
			}
		}

		@Override
		public void widgetDefaultSelected(SelectionEvent e) {
			// Nothing to do here
		}

	};
	private String currentFocus = "";
	
	private ISelectionListener mProviderSelectionListener = new ISelectionListener() {
		public void selectionChanged(IWorkbenchPart sourcepart, ISelection selection) {
			// We ignore our own selections
			if (sourcepart != ServiceView.this && !currentFocus.equals(selection.toString())) {

				String lRaw = selection.toString();
				currentFocus = lRaw;
				String lProviderName = lRaw.substring(1, lRaw.length()-1);
				ProviderMetaData newProvider = mController.getProvider(lProviderName);
				mController.onCurrentSelectionChanged(newProvider);
				update(newProvider);
			}
		}
	};



	// ===========================================================
	// Fields
	// ===========================================================

	private PointAController mController = PointAController.INSTANCE;

	private ParameterTableViewer mTableViewer;
	private Button mEnableButton;
	private Label mServiceTypeLabel;
	private Label mProviderNameLabel;
	private Label mEnableButtonLabel;
	private Label mPriorityComboLabel;
	private Combo mPriorityCombo;
	private Button mSaveButton;
	private Button mRevertButton;

	// ===========================================================
	// Methods
	// ===========================================================


	public void createPartControl(Composite pParent) {
		GridLayout gridLayout = new GridLayout(2,false);
		pParent.setLayout(gridLayout);

		// Create our combo box array
		String[] lPriorities = new String[5];
		for(int i = 0; i < 5; i++) lPriorities[i] = Integer.toString(i + 1);

		// Create all our view elements
		mServiceTypeLabel = new Label(pParent, SWT.NONE);
		mProviderNameLabel = new Label(pParent, SWT.NONE);
		mEnableButtonLabel = new Label(pParent, SWT.NONE);
		mEnableButton = new Button(pParent, SWT.CHECK);
		mPriorityComboLabel = new Label(pParent, SWT.NONE);
		mPriorityCombo = new Combo(pParent, SWT.DROP_DOWN);
		mTableViewer = new ParameterTableViewer(pParent, mController.getCurrentProviderParams());
		mSaveButton = new Button(pParent, SWT.PUSH);
		mRevertButton = new Button(pParent, SWT.PUSH);

		// Set Listeners
		mPriorityCombo.addSelectionListener(mViewListener);
		mSaveButton.addSelectionListener(mViewListener);
		mRevertButton.addSelectionListener(mViewListener);
		mEnableButton.addSelectionListener(mViewListener);

		// Set Text (Should all be in constants somewhere)
		mServiceTypeLabel.setText("Provider");
		mProviderNameLabel.setText("Provider Name");
		mEnableButtonLabel.setText("Enable");
		mPriorityComboLabel.setText("Priority");
		mPriorityCombo.setItems(lPriorities);
		mSaveButton.setText("Save and export");
		mRevertButton.setText("Revert Changes");

		// Set Global Listeners
		getSite().setSelectionProvider(mTableViewer);
		getSite().getWorkbenchWindow().getSelectionService().addSelectionListener(mProviderSelectionListener);

		setProviderEnabled(false);
	}

	public void update(ProviderMetaData pProvider){
		if(pProvider!= null){
			mProviderNameLabel.setText(pProvider.getName());

			setProviderEnabled(pProvider.getEnabled());
			mPriorityCombo.select(pProvider.getPriority());

			mTableViewer.refresh();
		}
	}

	private void setProviderEnabled(boolean pEnabled) {
		mEnableButton.setSelection(pEnabled);
		mTableViewer.getTable().setEnabled(pEnabled);
		mTableViewer.getTable().setEnabled(pEnabled);
		mPriorityComboLabel.setEnabled(pEnabled);
		mPriorityCombo.setEnabled(pEnabled);
	}

	public void setFocus() {
		mTableViewer.getControl().setFocus();
	}
} 