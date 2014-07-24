package com.pointaeclipseplugin.model.constants;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.pointaeclipseplugin.model.constants.MasterProviderInfo.Services;

public class MasterProviderMeta {
	public String name;
	public Services type; // make enum
	public String jarLocation;
	
	public List<String> params;
	private Set<Permissions> mPermissions;
	
	
	
	public MasterProviderMeta(String name, Services type, String jarLocation){
		this.name = name;
		this.type = type;
		this.jarLocation = jarLocation;
		this.params = new ArrayList<String>();
		this.mPermissions = new HashSet<Permissions>();
	}




	public void addPermission(Permissions pPermission) {
		mPermissions.add(pPermission);
	}
	
	public Set<Permissions> getPermission() {
		return mPermissions;
	}

	
}
