package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Types.Location;

public class OperatorManagementSystem {

  private Database entryData = new Database();
  private Operator operator = new Operator();

  // private Operator test = new Operator();

  // Do not change the parameters of the constructor
  public OperatorManagementSystem() {}

  public void searchOperators(String keyword) {

    Location locationFull = Location.fromString(keyword);

    if (keyword.equals("*")) {
      entryData.countData(keyword);
      entryData.printDatabase(keyword);
    } else if (locationFull != null) {
      entryData.countData(keyword);
      entryData.printDatabase(keyword);
    } else {
      for (Location location : Location.values()) {
        if (location.getNameEnglish().contains(keyword)) {
          keyword = location.getNameEnglish();
        } else if (location.getNameTeReo().contains(keyword)) {
          keyword = location.getNameTeReo();
        } else if (location.getLocationAbbreviation().contains(keyword)) {
          keyword = location.getLocationAbbreviation();
        }
        entryData.countData(keyword);
        entryData.printDatabase(keyword);
      }
    }
  }

  public void createOperator(String operatorName, String location) {
    // Retrieve full name of input location
    String locationString = operator.locationString(location);

    // Create locationID
    operator.locationAcronymn(location, operatorName);
    operator.operatorAcronymn(operatorName);
    operator.threeDigits();
    String locationID = operator.locationID();

    // Sending appropriate message WORK IN PROGRESS
    operator.sendMessage();

    // Storing these variables:
    entryData.storeVariables(operatorName, locationID, locationString);
    // Storing the operator into the database
    String operatorFound =
        MessageCli.OPERATOR_ENTRY.getMessage(operatorName, locationID, locationString);
    entryData.storeData(operatorFound);
  }

  public void viewActivities(String operatorId) {
    // TODO implement
  }

  public void createActivity(String activityName, String activityType, String operatorId) {
    // TODO implement
  }

  public void searchActivities(String keyword) {
    // TODO implement
  }

  public void addPublicReview(String activityId, String[] options) {
    // TODO implement
  }

  public void addPrivateReview(String activityId, String[] options) {
    // TODO implement
  }

  public void addExpertReview(String activityId, String[] options) {
    // TODO implement
  }

  public void displayReviews(String activityId) {
    // TODO implement
  }

  public void endorseReview(String reviewId) {
    // TODO implement
  }

  public void resolveReview(String reviewId, String response) {
    // TODO implement
  }

  public void uploadReviewImage(String reviewId, String imageName) {
    // TODO implement
  }

  public void displayTopActivities() {
    // TODO implement
  }
}
