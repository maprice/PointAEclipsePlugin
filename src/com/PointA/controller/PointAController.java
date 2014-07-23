package com.PointA.controller;

import java.util.List;

import com.PointA.model.ModelProvider;
import com.PointA.model.Provider2;

public enum PointAController {
	INSTANCE;

	// ===========================================================
	// Constants
	// ===========================================================

	static final String LOG_TAG =  PointAController.class.getSimpleName();

	// ===========================================================
	// Fields
	// ===========================================================

	private ModelProvider mModel;


	// ===========================================================
	// Constructors
	// ===========================================================

	private PointAController(){
		mModel = new ModelProvider();
	}

	// ===========================================================
	// Methods
	// ===========================================================

	public void onPriorityChanged(int pNewPriority){
		System.out.println("Priority changed to: "+pNewPriority);	
	}

	public void onCurrentSelectionChanged(String pNewSelection){
		System.out.println("Current selection changed to: "+pNewSelection);	
	}

	public void onEnableChanged(Boolean pEnabled){
		System.out.println("Enable button changed to: "+pEnabled);	
	}


	public List<Provider2> getCurrentProviderParams() {
		return mModel.getPersons();
	}

	public void onSaveButtonPressed() {
		System.out.println("Save button pressed");		
	}

	public void onRevertButtonPressed() {
		System.out.println("Revert button pressed");		
	}



}
