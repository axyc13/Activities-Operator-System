package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Types.Location;

public class Activity {

  private ArrayList<String> list = new ArrayList<>();
  protected ArrayList<String> activities = new ArrayList<>();
  private int number = 1;
  private String activityName;
  private String activityType;
  private String operatorId;
  private String threeDigits;
  private String operatorName;
  private int count = 0;

  public Activity() {}

  public boolean checkActivityId(String operatorId) {
    for (String activity : activities) {
      if (activity.substring(activity.indexOf("["), activity.indexOf("]")).contains(operatorId)) {
        return true; // operatorId found
      }
    }
    return false;
  }

  public void numActivitiesAtLocation(String operatorId) {
    // Counts how many activities exist under the input
    for (String data : activities) {
      if (data.contains(operatorId)) {
        this.count++;
      }
    }

    // Prints the amount of activities present
    if (count == 0) {
      MessageCli.ACTIVITIES_FOUND.printMessage("are", "no", "ies", ".");
    } else if (count == 1) {
      MessageCli.ACTIVITIES_FOUND.printMessage("is", "1", "y", ":");
      for (String data : activities) {
        if (data.contains(operatorId)) {
          System.out.println(data);
        }
      }
    } else if (count >= 2) {
      MessageCli.ACTIVITIES_FOUND.printMessage("are", Integer.toString(count), "ies", ":");
      for (String data : activities) {
        if (data.contains(operatorId)) {
          System.out.println(data);
        }
      }
    }
    this.count = 0;
  }

  public String createActivityId(
      String activityName, String activityType, String operatorId, String operatorName) {
    // Storing variables
    this.activityName = activityName;
    this.activityType = activityType;
    this.operatorId = operatorId;
    this.operatorName = operatorName;

    // Check if operatorId is in list, increment the three digits accordingly
    for (String id : list) {
      if (this.operatorId.equals(id)) {
        number++;
      }
    }
    list.add(this.operatorId);

    // Creater activity id
    String activityId = createThreeDigits();
    this.operatorId = this.operatorId + "-" + activityId;

    // Returns activity id and saves message for future use

    String theActivity =
        MessageCli.ACTIVITY_ENTRY.getMessage(
            this.activityName, this.operatorId, this.activityType, this.operatorName);
    activities.add(theActivity);

    return this.operatorId;
  }

  public String createThreeDigits() {
    // Creates the three digits
    this.threeDigits = String.format("%03d", this.number);
    this.number = 1;
    return this.threeDigits;
  }

  public void printDatabase(String keyword, String caseTwo) {
    // Prints all activities or prints select activities

    if (keyword.equals("*")) { // Case 1: prints all
      for (String activity : activities) {
        System.out.println(activity);
      }
    } else if (caseTwo != null) { // Case 2: where we are searching with location name
      for (String activity : activities) {
        // Extracting the location from the Id, first isolate code
        int start = activity.indexOf('[') + 1;
        int end = activity.indexOf('/');
        String code = activity.substring(start, end);

        // Split the code to get the location acronym
        String[] parts = code.split("-");
        String locationCode = parts[1];
        String locationName = Location.fromString(locationCode).getFullName();

        if (locationName.contains(keyword)
            || locationName.toLowerCase().contains(caseTwo.toLowerCase())) {
          System.out.println(activity);
        }
      }
    } else { // Case 3: where we are searching with activity snippet OR location name has a space
      for (String activity : activities) {
        // Extracting the location from the Id, first isolate code
        int start = activity.indexOf('[') + 1;
        int end = activity.indexOf('/');
        String code = activity.substring(start, end);

        // Split the code to get the location acronym
        String[] parts = code.split("-");
        String locationCode = parts[1];
        String locationName = Location.fromString(locationCode).getFullName();

        if (activity
                .toLowerCase()
                .substring(0, activity.indexOf("offered by "))
                .contains(keyword.toLowerCase())
            || locationName.toLowerCase().contains(keyword.toLowerCase())) {
          System.out.println(activity);
        }
      }
    }
  }

  public void countData(String keyword, String caseTwo) {
    // Counts and prints the total amount of activities and prints
    if (keyword.equals("*") && caseTwo == null) { // Case 1: where we are searching for all
      for (String place : activities) {
        this.count++;
      }
      if (count == 0) {
        MessageCli.ACTIVITIES_FOUND.printMessage("are", "no", "ies", ".");
      } else if (count == 1) {
        MessageCli.ACTIVITIES_FOUND.printMessage("is", "1", "y", ":");
      } else if (count >= 2) {
        MessageCli.ACTIVITIES_FOUND.printMessage("are", Integer.toString(count), "ies", ":");
      }
      count = 0;
    } else if (caseTwo != null) { // Case 2: where we are searching with location name
      for (String activity : activities) {
        // Extracting the location from the Id, first isolate code
        int start = activity.indexOf('[') + 1;
        int end = activity.indexOf('/');
        String code = activity.substring(start, end);

        // Split the code to get the location acronym
        String[] parts = code.split("-");
        String locationCode = parts[1];
        String locationName = Location.fromString(locationCode).getFullName();

        if (locationName.contains(keyword)
            || locationName.toLowerCase().contains(caseTwo.toLowerCase())) {
          this.count++;
        }
      }
      if (count == 0) {
        MessageCli.ACTIVITIES_FOUND.printMessage("are", "no", "ies", ".");
      } else if (count == 1) {
        MessageCli.ACTIVITIES_FOUND.printMessage("is", "1", "y", ":");
      } else if (count >= 2) {
        MessageCli.ACTIVITIES_FOUND.printMessage("are", Integer.toString(count), "ies", ":");
      }
      count = 0;
    } else { // Case where we are searching with activity snippet OR location name has a space
      for (String activity : activities) {
        // Extracting the location from the Id, first isolate code
        int start = activity.indexOf('[') + 1;
        int end = activity.indexOf('/');
        String code = activity.substring(start, end);

        // Split the code to get the location acronym
        String[] parts = code.split("-");
        String locationCode = parts[1];
        String locationName = Location.fromString(locationCode).getFullName();
        if (activity
                .toLowerCase()
                .substring(0, activity.indexOf("offered by "))
                .contains(keyword.toLowerCase())
            || locationName.toLowerCase().contains(keyword.toLowerCase())) {
          this.count++;
        }
      }
      if (count == 0) {
        MessageCli.ACTIVITIES_FOUND.printMessage("are", "no", "ies", ".");
      } else if (count == 1) {
        MessageCli.ACTIVITIES_FOUND.printMessage("is", "1", "y", ":");
      } else if (count >= 2) {
        MessageCli.ACTIVITIES_FOUND.printMessage("are", Integer.toString(count), "ies", ":");
      }
      count = 0;
    }
  }
}
