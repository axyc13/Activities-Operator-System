package nz.ac.auckland.se281;

import java.util.ArrayList;

public class Activity {

  private ArrayList<String> list = new ArrayList<>();
  private ArrayList<String> activities = new ArrayList<>();
  private int number = 1;
  private String activityName;
  private String activityType;
  private String operatorId;
  private String threeDigits;
  private String operatorName;
  private int count = 0;

  public Activity() {}

  public void checkIfActivityIsAtLocation(String operatorId) {
    // Check if the activity ID already exists
    for (String data : activities) {
      if (data.contains(operatorId)) {
        this.count++;
      }
    }

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

  public void printActivity(
      String activityName, String activityType, String operatorId, String operatorName) {
    // Storing variables
    this.activityName = activityName;
    this.activityType = activityType;
    this.operatorId = operatorId;
    this.operatorName = operatorName;

    // Add operator id into an array list, check if it already exists and increment the three digits
    // accordingly
    for (String id : list) {
      if (this.operatorId.equals(id)) {
        number++;
      }
    }
    list.add(this.operatorId);

    // Creater activity id
    String activityId = createThreeDigits();
    this.operatorId = this.operatorId + "-" + activityId;

    // Prints message and saves message for future use
    MessageCli.ACTIVITY_CREATED.printMessage(
        this.activityName, this.operatorId, this.activityType, this.operatorName);
    String theActivity =
        MessageCli.ACTIVITY_ENTRY.getMessage(
            this.activityName, this.operatorId, this.activityType, this.operatorName);
    activities.add(theActivity);
  }

  public String createThreeDigits() {
    // Creates the three digits
    this.threeDigits = String.format("%03d", this.number);
    this.number = 1;
    return this.threeDigits;
  }
}
