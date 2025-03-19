package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Types.Location;

public class OperatorManagementSystem {

  private Database entryData = new Database();
  private Operator operator = new Operator();

  // Do not change the parameters of the constructor
  public OperatorManagementSystem() {}

  public void searchOperators(String keyword) {
    // 3 Cases:
    Location locationFull = Location.fromString(keyword);

    if (keyword.equals("*")) {
      // 1. Searching for all operators

      entryData.countData(keyword);
      entryData.printDatabase(keyword);
    } else if (locationFull != null) {
      // 2. Searching with a location keyword

      entryData.countData(keyword);
      entryData.printDatabase(keyword);
    } else {
      // 3. Searching with a snippet of a location keyword

      // Convert location snippet into full version
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
    String locationString = operator.createLocationString(location);

    // Create location ID
    operator.createLocationAcronym(location, operatorName);
    operator.createOperatorAcronym(operatorName);
    operator.createThreeDigits();
    String locationIdentity = operator.createLocationIdentity();

    // Sending appropriate message
    operator.sendMessage();

    // Storing variables
    entryData.storeVariables(operatorName, locationIdentity, locationString);
    String operatorFound =
        MessageCli.OPERATOR_ENTRY.getMessage(operatorName, locationIdentity, locationString);
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
