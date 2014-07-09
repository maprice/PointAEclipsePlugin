package com.pointaeclipseplugin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.pointaeclipseplugin.model.PointAModel;
import com.pointaeclipseplugin.model.filereader.ConfigSettings;
import com.pointaeclipseplugin.view.PointAView;



/**
 * Contains all meta data on all service providers
 * @version 1.0
 * @since July 08, 2014
 *
 */

public class PointAController {

	// ===========================================================
	// Constants
	// ===========================================================

	static final String LOG_TAG =  PointAController.class.getSimpleName();

	// ===========================================================
	// Fields
	// ===========================================================

	private PointAModel mModel;
	private PointAView mView;
	private ActionListener mButtonListener;

	// ===========================================================
	// Constructors
	// ===========================================================

	public PointAController(PointAModel pModel, PointAView pView){
		mModel = pModel;
		mView = pView;
		
		this.populateView();
		this.setListeners();
	}
	
	// ===========================================================
	// Methods
	// ===========================================================

	private void setListeners() {
		 mButtonListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
           	 // Get new config settings from view
           	 ConfigSettings lConfigSettings = null;
           	 
           	 // Tell model to make changes
           	 mModel.saveChanges(lConfigSettings);
            }
      };      
      
      // Add action listener to view
      //mView.getButton().addActionListener(actionListener);  
      
	}

	private void populateView() {
		// Populate view with data from model
	}
	
}