package nz.ac.auckland.se281;

import java.util.ArrayList;

public class Activity {

  private ArrayList<String> list = new ArrayList<>();
  private int number = 1;
  private String activityName;
  private String activityType;
  private String operatorId;
  private String threeDigits;
  private String operatorName;

  public Activity() {}

  public void printActivity(
      String activityName, String activityType, String operatorId, String operatorName) {
    this.activityName = activityName;
    this.activityType = activityType;
    this.operatorId = operatorId;
    this.operatorName = operatorName;

    for (String id : list) {
      if (this.operatorId.equals(id)) {
        number++;
      }
    }
    list.add(this.operatorId);

    String activityId = createThreeDigits();
    this.operatorId = this.operatorId + "-" + activityId;

    MessageCli.ACTIVITY_CREATED.printMessage(
        this.activityName, this.operatorId, this.activityType, this.operatorName);
  }

  public String createThreeDigits() {
    this.threeDigits = String.format("%03d", this.number);
    this.number = 1;
    return this.threeDigits;
  }
}
