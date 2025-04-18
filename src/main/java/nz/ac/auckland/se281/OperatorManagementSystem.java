package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Types.ActivityType;
import nz.ac.auckland.se281.Types.Location;

public class OperatorManagementSystem {

  private Database entryData = new Database();
  private Operator operator = new Operator();
  private Activity activity = new Activity();
  private ReviewSystem reviewSystem = new ReviewSystem();
  private Review expertReview = new ExpertReview();
  private Review privateReview = new PrivateReview();
  private Review publicReview = new PublicReview();
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
            activity.createActivityId(activityName, activityType, operatorId, operators);
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
    if (reviewSystem.checkActivityId(activityNames, activityId) == false) {
      MessageCli.REVIEW_NOT_ADDED_INVALID_ACTIVITY_ID.printMessage(activityId);
      return;
    }

    for (String activity : activityNames) {
      if (activity.contains(activityId)) {
        String activityName =
            activity.substring(activity.indexOf("and ") + 3, activity.length()).trim();
        String reviewId = reviewSystem.createReviewId(activity);
        publicReview.getMessage(reviewId, activityName, options);
      }
    }
  }

  public void addPrivateReview(String activityId, String[] options) {
    if (reviewSystem.checkActivityId(activityNames, activityId) == false) {
      MessageCli.REVIEW_NOT_ADDED_INVALID_ACTIVITY_ID.printMessage(activityId);
      return;
    }

    for (String activity : activityNames) {
      if (activity.contains(activityId)) {
        String activityName =
            activity.substring(activity.indexOf("and ") + 3, activity.length()).trim();
        String reviewId = reviewSystem.createReviewId(activity);
        privateReview.getMessage(reviewId, activityName, options);
      }
    }
  }

  public void addExpertReview(String activityId, String[] options) {
    if (reviewSystem.checkActivityId(activityNames, activityId) == false) {
      MessageCli.REVIEW_NOT_ADDED_INVALID_ACTIVITY_ID.printMessage(activityId);
      return;
    }

    for (String activity : activityNames) {
      if (activity.contains(activityId)) {
        String activityName =
            activity.substring(activity.indexOf("and ") + 3, activity.length()).trim();
        String reviewId = reviewSystem.createReviewId(activity);
        expertReview.getMessage(reviewId, activityName, options);
      }
    }
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
    publicReview.printReviews();
    privateReview.printReviews();
    expertReview.printReviews();
  }

  public void endorseReview(String reviewId) {
    if (reviewSystem.checkReviewId(reviewId) == false) {
      MessageCli.REVIEW_NOT_FOUND.printMessage(reviewId);
      return;
    }
    publicReview.endorseReview(reviewId);
    privateReview.endorseReview(reviewId);
    expertReview.endorseReview(reviewId);
  }

  public void resolveReview(String reviewId, String response) {
    if (reviewSystem.checkReviewId(reviewId) == false) {
      MessageCli.REVIEW_NOT_FOUND.printMessage(reviewId);
      return;
    }
    publicReview.resolveReview(reviewId, response);
    privateReview.resolveReview(reviewId, response);
    expertReview.resolveReview(reviewId, response);
  }

  public void uploadReviewImage(String reviewId, String imageName) {
    if (reviewSystem.checkReviewId(reviewId) == false) {
      MessageCli.REVIEW_NOT_FOUND.printMessage(reviewId);
      return;
    }
    publicReview.uploadImage(reviewId, imageName);
    privateReview.uploadImage(reviewId, imageName);
    expertReview.uploadImage(reviewId, imageName);
  }

  public void displayTopActivities() {
    // System.out.println(activityNames); // contains acitivity name and Id

    // Extract the reviews from public and expert reviews
    ArrayList<String> publicReviews = publicReview.getReviews();
    ArrayList<String> expertReviews = expertReview.getReviews();

    ArrayList<String> allReviews = new ArrayList<>();
    allReviews.addAll(publicReviews);
    allReviews.addAll(expertReviews);

    // Storing ratings under their corresponding activityId
    // i.e. activityId[0]'s ratings will be listOfRatings[0]
    ArrayList<String> activityIds = new ArrayList<>();
    ArrayList<ArrayList<Integer>> listOfRatings = new ArrayList<>();

    for (String review : allReviews) {
      String activityId = review.substring(0, review.indexOf("-R")).trim();
      int rating = Integer.valueOf(review.substring(review.indexOf("and ") + 4, review.length()));

      // Check if activityId exists in list
      if (activityIds.contains(activityId) == false) { // Doesn't exist, add to list
        activityIds.add(activityId);

        ArrayList<Integer> ratings = new ArrayList<>();
        ratings.add(rating);
        listOfRatings.add(ratings);

      } else { // Exists, add ratings to their corresponding activityId
        int i = activityIds.indexOf(activityId);
        listOfRatings.get(i).add(rating);
      }
    }

    // Calculate the average for each activityID and store in the corresponding index of a new array
    // list
    ArrayList<Double> averages = new ArrayList<>();

    for (int i = 0; i < activityIds.size(); i++) {
      // String activityId = activityIds.get(i);
      ArrayList<Integer> ratingsForOneActivity = listOfRatings.get(i);

      int sum = 0;
      for (int rating : ratingsForOneActivity) {
        sum += rating;
      }

      double average = (double) sum / ratingsForOneActivity.size();
      averages.add(average);

      // System.out.println("average for " + activityId);
      // System.out.println(average);
    }

    // Storing highest average rating under their corresponding locations, logic similar to previous
    ArrayList<String> locations = new ArrayList<>();
    ArrayList<Double> highestAverages = new ArrayList<>();

    for (int i = 0; i < activityIds.size(); i++) {
      String activityId = activityIds.get(i);
      double currentAverage = averages.get(i);

      String[] parts = activityId.split("-");
      String location = parts[1];

      // Check if location exists in list

      if (locations.contains(location) == false) { // Doesn't exist, add to lists
        locations.add(location);
        highestAverages.add(currentAverage);
      } else { // Exists, compare averages
        int locationIndex = locations.indexOf(location);
        if (currentAverage > highestAverages.get(locationIndex)) {
          highestAverages.set(locationIndex, currentAverage);
        }
      }
    }
    System.out.println("Locations: " + locations);
    System.out.println("Highest Averages: " + highestAverages);

    for (Location location : Location.values()) {
      MessageCli.NO_REVIEWED_ACTIVITIES.printMessage(location.getFullName());
    }
  }
}
