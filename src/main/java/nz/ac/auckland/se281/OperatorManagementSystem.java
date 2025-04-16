package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Types.ActivityType;
import nz.ac.auckland.se281.Types.Location;

public class OperatorManagementSystem {

  private Database entryData = new Database();
  private Operator operator = new Operator();
  private Activity activity = new Activity();
  private ReviewSystem reviewSystem = new ReviewSystem();
  private ArrayList<String> operatorNames = new ArrayList<>();
  protected ArrayList<String> activityNames = new ArrayList<>();

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
      System.out.println("CASE TWO OUTSIDE");
      // Case 2: Searching with the full location/operator keyword

      String locationName = locationFull.getFullName();
      entryData.countData(locationName, keyword);
      entryData.printDatabase(locationName, keyword);

    } else {
      // Case 3: Searching with a snippet of a location/operator keyword
      System.out.println("CASE THREE OUTSIDE");
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
        entryData.countData(keyword, keyword);
        entryData.printDatabase(keyword, keyword);
      }
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
    // Initialising variables
    operatorId = operatorId.trim();
    activityName = activityName.trim();
    activityType = activityType.trim().toLowerCase();
    activityType = activityType.substring(0, 1).toUpperCase() + activityType.substring(1);

    // Checks if operator is in database
    if (entryData.checkOperatorId(operatorId) == false) {
      MessageCli.ACTIVITY_NOT_CREATED_INVALID_OPERATOR_ID.printMessage(operatorId);
      return;
    }
    // Checks if activity name is less than 3 characters
    if (activityName.length() < 3) {
      MessageCli.ACTIVITY_NOT_CREATED_INVALID_ACTIVITY_NAME.printMessage(activityName);
      return;
    }

    // Checks if activity is an 'other':
    if (ActivityType.fromString(activityType) == ActivityType.OTHER) {
      activityType = ActivityType.OTHER.getName();
    }

    for (String operators : operatorNames) {
      String operatorInQuestion = "";
      String[] words = operators.split(" ");
      for (String word : words) {
        operatorInQuestion += word.charAt(0);
      }
      if (operatorId.contains(operatorInQuestion)) {
        String activtyId =
            activity.printActivity(activityName, activityType, operatorId, operators);
        MessageCli.ACTIVITY_CREATED.printMessage(activityName, activtyId, activityType, operators);
        activityNames.add(activtyId + " and " + activityName);
        return;
      }
    }
  }

  public void searchActivities(String keyword) {

    keyword = keyword.trim();

    // 3 Cases:
    Location locationFull = Location.fromString(keyword);

    if (keyword.equals("*")) {
      // Case 1: Searching for all activities

      activity.countData(keyword, null);
      activity.printDatabase(keyword, null);

    } else if (locationFull != null) {
      System.out.println("CASE TWO OUTSIDE");
      // Case 2: Searching with the location of activity

      String locationName = locationFull.getFullName();

      activity.countData(locationName, keyword);
      activity.printDatabase(locationName, keyword);

    } else {
      // Case 3: Searching with a snippet from the activity
      System.out.println("CASE THREE OUTSIDE");
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
    if (activity.checkActivityId(activityId) == false) {
      MessageCli.ACTIVITY_NOT_FOUND.printMessage(activityId);
      return;
    }
    for (String activity : activityNames) {
      if (activity.contains(activityId)) {
        activityId = activity.substring(activity.indexOf("and ") + 2);
      }
    }
    reviewSystem.checkIfActivityIsAtLocation(activityId);
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
