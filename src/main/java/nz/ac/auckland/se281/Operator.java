package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Types.Location;

public class Operator {
  private String locationString;
  private String operatorName;

  private String locationAcronym;
  private String operatorAcronym = "";
  private String threeDigits;
  private String locationIdentity;
  private ArrayList<String> list = new ArrayList<>();
  private ArrayList<String> list2 = new ArrayList<>();
  private int number = 1;

  public Operator() {}

  public String locationString(String location) {
    // Retrieve full name of input location
    Location locationFullName = Location.fromString(location);
    this.locationString = locationFullName.getFullName();
    return this.locationString;
  }

  public void locationAcronym(String location, String specificOperator) {
    // Create location acronym
    Location locationFullName = Location.fromString(location);
    this.locationAcronym = locationFullName.getLocationAbbreviation();

    // Check if location already exists and increment accordingly
    if (list.contains(this.locationAcronym) && !list2.contains(specificOperator)) {
      for (String places : list) {
        if (this.locationAcronym == places) {
          number++;
        }
      }
    } else {
      // List of location acronyms to be printed
      list.add(this.locationAcronym);
      // List of location acronyms to check if operator already exists
      list2.add(specificOperator);
    }
  }

  public void operatorAcronym(String operatorName) {
    // Create operator acronym
    this.operatorName = operatorName;
    String[] words = operatorName.split(" ");
    for (String word : words) {
      this.operatorAcronym += word.charAt(0);
    }
  }

  public void createThreeDigits() {
    this.threeDigits = String.format("%03d", this.number);
    this.number = 1;
  }

  public String createLocationIdentity() {
    // Create location identity
    this.locationIdentity =
        String.join("-", this.operatorAcronym, this.locationAcronym, this.threeDigits);
    this.operatorAcronym = "";
    return this.locationIdentity;
  }

  public void sendMessage() {
    MessageCli.OPERATOR_CREATED.printMessage(
        this.operatorName, this.locationIdentity, this.locationString);
  }
}
