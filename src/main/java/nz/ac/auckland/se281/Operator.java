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

  public String createLocationString(String location) {
    // Retrieve full name of input location

    Location locationFullName = Location.fromString(location);
    if (locationFullName == null) {
      MessageCli.OPERATOR_NOT_CREATED_INVALID_LOCATION.printMessage(location);
      return null;
    } else {
      this.locationString = locationFullName.getFullName();
      return this.locationString;
    }
  }

  public void createLocationAcronym(String location, String specificOperator) {
    // Create location acronym
    Location locationFullName = Location.fromString(location);
    if (locationFullName != null) {
      this.locationAcronym = locationFullName.getLocationAbbreviation();
    }

    // Check if location already exists and increment accordingly
    if (list.contains(this.locationAcronym) && !list2.contains(specificOperator)) {
      for (String places : list) {
        if (this.locationAcronym.equals(places)) {
          number++;
        }
      }
      list.add(this.locationAcronym);
    } else {
      // List of location acronyms to be printed
      list.add(this.locationAcronym);
      // List of location acronyms to check if operator already exists
      list2.add(specificOperator);
    }
  }

  public void createOperatorAcronym(String operatorName) {
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
    if (this.locationString != null) {
      MessageCli.OPERATOR_CREATED.printMessage(
          this.operatorName, this.locationIdentity, this.locationString);
    }
  }
}
