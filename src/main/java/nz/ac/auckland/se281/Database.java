package nz.ac.auckland.se281;

import java.util.ArrayList;

public class Database {
  protected ArrayList<String> list = new ArrayList<>();

  private int count = 0;
  private String operatorName;
  private String locationIdentity;
  private String locationString;

  public Database() {}

  public void storeVariables(String operatorName, String locationIdentity, String locationString) {
    this.operatorName = operatorName;
    this.locationIdentity = locationIdentity;
    this.locationString = locationString;
  }

  public boolean checkOperatorId(String operatorId) {
    for (String data : list) {
      // System.out.println(data);
      if (data.substring(data.indexOf("('"), data.indexOf("' ")).contains(operatorId)) {
        return true; // Operator ID found
      }
    }
    return false;
  }

  public void storeData(String data) {
    // Stores data
    if (list.contains(data)) {
      MessageCli.OPERATOR_NOT_CREATED_ALREADY_EXISTS_SAME_LOCATION.printMessage(
          operatorName, locationString);
    } else {
      list.add(data);
    }
  }

  public void printDatabase(String keyword, String caseTwo) {
    // Prints specific/all data
    if (keyword.equals("*")) {
      for (String place : list) {
        System.out.println(place);
      }
    } else if (caseTwo != null) { // Special case where operator name contains location name
      for (String place : list) {
        if (place.contains(keyword) || place.toLowerCase().contains(caseTwo.toLowerCase())) {
          System.out.println(place);
        }
      }
    } else {
      for (String place : list) {
        if (place.contains(keyword)) {
          System.out.println(place);
        }
      }
    }
  }

  public void countData(String keyword, String caseTwo) {
    // Counts the total amount of data and prints accordingly
    if (keyword.equals("*") && caseTwo == null) {
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
      count = 0;
    } else if (caseTwo != null) { // Special case where where operator name contains location name
      for (String place : list) {
        if (place.contains(keyword) || place.toLowerCase().contains(caseTwo.toLowerCase())) {
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
    } else { // Counts the amount of specified data and prints accordingly
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
