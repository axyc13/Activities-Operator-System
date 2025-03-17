package nz.ac.auckland.se281;

import java.util.ArrayList;

public class Database {
  private ArrayList<String> list = new ArrayList<>();
  private int count = 0;

  // private String Test = "hi";

  public Database() {}

  public void storeData(String data) {
    list.add(data);
    // Test = data;
  }

  public void printDatabase() {
    for (String place : list) {
      System.out.println(place);
    }
  }

  public void countData() {
    for (String place : list) {
      this.count++;
    }
    if (count == 0) {
      MessageCli.OPERATORS_FOUND.printMessage("are", "no", "s", ".");
    } else if (count == 1) {
      MessageCli.OPERATORS_FOUND.printMessage("is", "1", "", ":");
    } else if (count == 2) {
      MessageCli.OPERATORS_FOUND.printMessage("are", "2", "s", ":");
    }
  }
}
