package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Types.Location;

public class Operator {
  private String locationString;
  private String operatorName;

  private String locationAcronym;
  private String operatorAcronymn = "";
  private String threeDigits;
  private String locationID;

  public Operator() {}

  public String locationString(String location) {
    Location locationFullName = Location.fromString(location);
    this.locationString = locationFullName.getFullName();
    return this.locationString;
  }

  public void locationAcronymn(String location) {
    Location locationFullName = Location.fromString(location);
    this.locationAcronym = locationFullName.getLocationAbbreviation();
  }

  public void operatorAcronymn(String operatorName) {
    this.operatorName = operatorName;
    String[] words = operatorName.split(" ");
    for (String word : words) {
      this.operatorAcronymn += word.charAt(0);
    }
  }

  public void threeDigits() {
    // WORK IN PROGRESS
    int number = 1;
    this.threeDigits = String.format("%03d", number);
  }

  public String locationID() {
    this.locationID =
        String.join("-", this.operatorAcronymn, this.locationAcronym, this.threeDigits);
    return this.locationID;
  }

  public void sendMessage() {
    MessageCli.OPERATOR_CREATED.printMessage(
        this.operatorName, this.locationID, this.locationString);
  }
}
