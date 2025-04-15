package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Types.ActivityType;
import nz.ac.auckland.se281.Types.Location;

public class OperatorManagementSystem {

  private Database entryData = new Database();
  private Operator operator = new Operator();
  private Activity activity = new Activity();
  private ArrayList<String> operatorNames = new ArrayList<>();

  // Do not change the parameters of the constructor
  public OperatorManagementSystem() {}

  public void searchOperators(String keyword) {
    // 3 Cases:
    Location locationFull = Location.fromString(keyword);

    if (keyword.equals("*")) {
      // Case 1: Searching for all operators

      entryData.countData(keyword, null);
      entryData.printDatabase(keyword, null);

    } else if (locationFull != null) {
      // Case 2: Searching with the full location/operator keyword

      String locationName = locationFull.getFullName();
      entryData.countData(locationName, keyword);
      entryData.printDatabase(locationName, keyword);

    } else {
      // Case 3: Searching with a snippet of a location/operator keyword

      keyword = keyword.replaceAll("\\s", "");

      // Convert location snippet into full version
      for (Location location : Location.values()) {
        if (location.getNameEnglish().contains(keyword.toLowerCase())) {
          keyword = location.getNameEnglish();
        } else if (location.getNameTeReo().contains(keyword.toLowerCase())) {
          keyword = location.getNameTeReo();
        } else if (location.getLocationAbbreviation().contains(keyword.toLowerCase())) {
          keyword = location.getLocationAbbreviation();
        } else {
        }
      }
      entryData.countData(keyword, keyword);
      entryData.printDatabase(keyword, keyword);
    }
  }

  public void createOperator(String operatorName, String location) {
    // Checks if operator is less than 3 characters

    if (operatorName.length() < 3) {
      MessageCli.OPERATOR_NOT_CREATED_INVALID_OPERATOR_NAME.printMessage(operatorName);
      return;
    }

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
    if (locationString != null) {
      entryData.storeVariables(operatorName, locationIdentity, locationString);
      String operatorFound =
          MessageCli.OPERATOR_ENTRY.getMessage(operatorName, locationIdentity, locationString);
      entryData.storeData(operatorFound);
    }
    operatorNames.add(operatorName);
  }

  public void viewActivities(String operatorId) {
    if (entryData.checkOperatorId(operatorId) == false) {
      MessageCli.OPERATOR_NOT_FOUND.printMessage(operatorId);
      return;
    }
    activity.checkIfActivityIsAtLocation(operatorId);
  }

  public void createActivity(String activityName, String activityType, String operatorId) {
    // Checks if operator is in database:
    if (entryData.checkOperatorId(operatorId) == false) {
      MessageCli.ACTIVITY_NOT_CREATED_INVALID_OPERATOR_ID.printMessage(operatorId);
      return;
    }
    // Checks if activity name is less than 3 characters:
    if (activityName.length() < 3) {
      MessageCli.ACTIVITY_NOT_CREATED_INVALID_ACTIVITY_NAME.printMessage(activityName);
      return;
    }
    // Checks if activity is valid:
    if (ActivityType.fromString(activityType) == ActivityType.OTHER) {
      MessageCli.ACTIVITY_NOT_CREATED_INVALID_ACTIVITY_NAME.printMessage(activityType);
      return;
    }

    for (String operators : operatorNames) {
      String operatorInQuestion = "";
      String[] words = operators.split(" ");
      for (String word : words) {
        operatorInQuestion += word.charAt(0);
      }
      if (operatorId.contains(operatorInQuestion)) {
        activity.printActivity(activityName, activityType, operatorId, operators);
        return;
      }
    }
  }

  public void searchActivities(String keyword) {

    // 3 Cases:
    Location locationFull = Location.fromString(keyword);

    if (keyword.equals("*")) {
      // Case 1: Searching for all activities

      activity.countData(keyword, null);
      activity.printDatabase(keyword, null);

    } else if (locationFull != null) {

      // Case 2: Searching with the location of activity

      String locationName = locationFull.getFullName();

      activity.countData(locationName, keyword);
      activity.printDatabase(locationName, keyword);

    } else {
      // Case 3: Searching with a snippet from the activity
      keyword = keyword.replaceAll("\\s", "");
      activity.countData(keyword, null);
      activity.printDatabase(keyword, null);
    }
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
