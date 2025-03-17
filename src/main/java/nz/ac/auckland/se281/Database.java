package nz.ac.auckland.se281;

import java.util.ArrayList;

public class Database {
  private ArrayList<String> list = new ArrayList<>();

  // private String Test = "hi";

  public Database() {}

  public void storeData(String data) {
    list.add(data);
    // Test = data;
  }

  public void printDatabase() {
    for (String place : list) {
      System.out.println(list.get(0));
    }
    // System.out.println(Test);
  }
}
