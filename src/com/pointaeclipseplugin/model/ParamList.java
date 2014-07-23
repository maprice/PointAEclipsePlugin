package com.pointaeclipseplugin.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ParamList {
  

  private List<ProviderParamSet> persons;

  public ParamList() {
    persons = new ArrayList<ProviderParamSet>();
    // Image here some fancy database access to read the persons and to
    // put them into the model
    persons.add(new ProviderParamSet("Banner ID", "asfdsadfasdfa"));
    persons.add(new ProviderParamSet("Interstial ID", "asdfasdfasdf"));
    persons.add(new ProviderParamSet("Other Value", "gfsdgsdfgsd"));
  }

  public List<ProviderParamSet> getPersons() {
    return persons;
  }

public void setPersons(Map<String, String> params) {
	persons.clear();
	
	for (Map.Entry<String, String> entry : params.entrySet()) {
	    String key = entry.getKey();
	    String value = entry.getValue();
	    persons.add(new ProviderParamSet(key, value));
	}
}

} 