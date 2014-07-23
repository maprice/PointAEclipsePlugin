package com.PointA.view;

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

import com.PointA.controller.PointAController;
import com.PointA.view.table.ParameterTableViewer;



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
			else if(e.widget == mPriorityCombo){
				mController.onEnableChanged(mPriorityCombo.getEnabled());
			}
		}

		@Override
		public void widgetDefaultSelected(SelectionEvent e) {
			// Nothing to do here
		}

	};

	private ISelectionListener mProviderSelectionListener = new ISelectionListener() {
		public void selectionChanged(IWorkbenchPart sourcepart, ISelection selection) {
			// We ignore our own selections
			if (sourcepart != ServiceView.this) {
				mController.onCurrentSelectionChanged(selection.toString());
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
		mEnableButton = new Button(pParent, SWT.CHECK);
		mEnableButtonLabel = new Label(pParent, SWT.NONE);
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
		mServiceTypeLabel.setText("Ad: ");
		mProviderNameLabel.setText("AdMob");
		mEnableButtonLabel.setText("Enable");
		mPriorityComboLabel.setText("Priority");
		mPriorityCombo.setItems(lPriorities);
		mSaveButton.setText("Save and export");
		mRevertButton.setText("Revert Changes");

		// Set Global Listeners
		getSite().setSelectionProvider(mTableViewer);
		getSite().getWorkbenchWindow().getSelectionService().addSelectionListener(mProviderSelectionListener);

	}

	public void setFocus() {
		mTableViewer.getControl().setFocus();
	}
} 