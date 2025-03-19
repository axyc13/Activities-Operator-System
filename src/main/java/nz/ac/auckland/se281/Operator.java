package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Types.Location;

public class Operator {
  private String locationString;
  private String operatorName;

  private String locationAcronymn;
  private String operatorAcronymn = "";
  private String threeDigits;
  private String locationID;
  private ArrayList<String> list = new ArrayList<>();
  private ArrayList<String> list2 = new ArrayList<>();
  private int number = 1;

  public Operator() {}

  public String locationString(String location) {
    Location locationFullName = Location.fromString(location);
    this.locationString = locationFullName.getFullName();
    return this.locationString;
  }

  public void locationAcronymn(String location, String specificOperator) {
    Location locationFullName = Location.fromString(location);
    this.locationAcronymn = locationFullName.getLocationAbbreviation();

    if (list.contains(this.locationAcronymn) && !list2.contains(specificOperator)) {
      for (String places : list) {
        if (this.locationAcronymn == places) {
          number++;
        }
      }
    } else {
      list.add(this.locationAcronymn);
      list2.add(specificOperator);
    }
  }

  public void operatorAcronymn(String operatorName) {
    this.operatorName = operatorName;
    String[] words = operatorName.split(" ");
    for (String word : words) {
      this.operatorAcronymn += word.charAt(0);
    }
  }

  public void threeDigits() {
    this.threeDigits = String.format("%03d", this.number);
    this.number = 1;
  }

  public String locationID() {
    this.locationID =
        String.join("-", this.operatorAcronymn, this.locationAcronymn, this.threeDigits);
    this.operatorAcronymn = "";
    return this.locationID;
  }

  public void sendMessage() {
    MessageCli.OPERATOR_CREATED.printMessage(
        this.operatorName, this.locationID, this.locationString);
  }
}
