package nz.ac.auckland.se281;

import java.util.ArrayList;

public class Database {
  private ArrayList<String> list = new ArrayList<>();
  private int count = 0;
  private String operatorName;
  private String locationID;
  private String locationString;

  public Database() {}

  public void storeVariables(String operatorName, String locationID, String locationString) {
    this.operatorName = operatorName;
    this.locationID = locationID;
    this.locationString = locationString;
  }

  public void storeData(String data) {
    if (list.contains(data)) {
      MessageCli.OPERATOR_NOT_CREATED_ALREADY_EXISTS_SAME_LOCATION.printMessage(
          operatorName, locationString);
    } else {
      list.add(data);
    }
  }

  public void printDatabase(String keyword) {
    if (keyword.equals("*")) {
      for (String place : list) {
        System.out.println(place);
      }
    } else {
      for (String place : list) {
        if (place.contains(keyword)) {
          System.out.println(place);
        }
      }
    }
  }

  public void countData(String keyword) {
    if (keyword.equals("*")) {
      for (String place : list) {
        this.count++;
      }
      if (count == 0) {
        MessageCli.OPERATORS_FOUND.printMessage("are", "no", "s", ".");
      } else if (count == 1) {
        MessageCli.OPERATORS_FOUND.printMessage("is", "1", "", ":");
      } else if (count >= 2) {
        MessageCli.OPERATORS_FOUND.printMessage("are", Integer.toString(count), "s", ":");
      }
    } else {
      for (String place : list) {
        if (place.contains(keyword)) {
          this.count++;
        }
      }
      if (count == 0) {
        MessageCli.OPERATORS_FOUND.printMessage("are", "no", "s", ".");
      } else if (count == 1) {
        MessageCli.OPERATORS_FOUND.printMessage("is", "1", "", ":");
      } else if (count >= 2) {
        MessageCli.OPERATORS_FOUND.printMessage("are", Integer.toString(count), "s", ":");
      }
      count = 0;
    }
  }
}
