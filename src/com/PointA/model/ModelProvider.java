package com.PointA.model;

import java.util.ArrayList;
import java.util.List;

public class ModelProvider {
  

  private List<Provider2> persons;

  public ModelProvider() {
    persons = new ArrayList<Provider2>();
    // Image here some fancy database access to read the persons and to
    // put them into the model
    persons.add(new Provider2("Banner ID", "asfdsadfasdfa"));
    persons.add(new Provider2("Interstial ID", "asdfasdfasdf"));
    persons.add(new Provider2("Other Value", "gfsdgsdfgsd"));
  }

  public List<Provider2> getPersons() {
    return persons;
  }

} 