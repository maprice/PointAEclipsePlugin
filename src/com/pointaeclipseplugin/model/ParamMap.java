package com.pointaeclipseplugin.model;

import java.beans.PropertyChangeSupport;

public class ParamMap {
	private String mParameterKey;
	private String mParameterValue;

	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

	public ParamMap() {
	}

	public ParamMap(String pParameterKey, String pParameterValue) {
		super();
		this.mParameterKey = pParameterKey;
		this.mParameterValue = pParameterValue;
	}

	

	public String getParameterKey() {
		return mParameterKey;
	}


	public String getParameterValue() {
		return mParameterValue;
	}

	public void setParameterValue(String pParameterValue) {
		propertyChangeSupport.firePropertyChange("paramValue", this.mParameterValue,
				this.mParameterValue = pParameterValue);
	}

	@Override
	public String toString() {
		return mParameterKey + " " + mParameterValue;
	}

} 