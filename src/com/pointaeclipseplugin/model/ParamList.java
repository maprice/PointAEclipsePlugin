package com.pointaeclipseplugin.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ParamList {


	private List<ParamMap> mParameterSet;

	public ParamList() {
		mParameterSet = new ArrayList<ParamMap>();
	}

	public List<ParamMap> getParameters() {
		return mParameterSet;
	}

	public void setParameters(Map<String, String> params) {
		mParameterSet.clear();

		for (Map.Entry<String, String> entry : params.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			mParameterSet.add(new ParamMap(key, value));
		}
	}
	
	public void updateParameters(Map<String, String> pOldParams) { 
		for(ParamMap lMap : mParameterSet){
			pOldParams.put(lMap.getParameterKey(), lMap.getParameterValue());
		}

	}


} 