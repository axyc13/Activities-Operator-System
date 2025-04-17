package nz.ac.auckland.se281;

import java.util.ArrayList;

public class ReviewSystem {

  private ArrayList<String> reviews = new ArrayList<>();
  protected String activityNameandId;
  protected ArrayList<String> list = new ArrayList<>();
  private int count = 0;
  private String activityName;
  private String activityId;
  private int number = 1;
  private String reviewDigits;

  public ReviewSystem() {}

  public boolean checkActivityId(ArrayList<String> activities, String activityId) {
    for (String activity : activities) {
      if (activity.contains(activityId)) {
        return true; // Activity ID found
      }
    }
    return false;
  }

  public void checkIfActivityIsAtLocation(String activityId) {
    activityId = activityId.trim().substring(2);
    // Check if the activity ID already exists

    for (String review : reviews) {
      if (review.contains(activityId)) {
        this.count++;
      }
    }

    if (count == 0) {
      // REVIEWS_FOUND("There %s %s review%s for activity '%s'."),
      MessageCli.REVIEWS_FOUND.printMessage("are", "no", "s", activityId);
    } else if (count == 1) {
      MessageCli.REVIEWS_FOUND.printMessage("is", "1", "", activityId + "");
      // for (String review : reviews) {
      //   if (review.contains(activityId)) {
      //     System.out.println(review);
      //   }
      // }
    } else if (count >= 2) {
      MessageCli.REVIEWS_FOUND.printMessage("are", Integer.toString(count), "s", activityId + "");
      // for (String review : reviews) {
      //   if (review.contains(activityId)) {
      //     System.out.println(review);
      //   }
      // }
    }
    this.count = 0;
  }

  public boolean checkReviewId(String reviewId) {
    for (String review : reviews) {
      if (review.contains(reviewId)) {
        return true; // Operator ID found
      }
    }
    return false;
  }

  public String createReviewId(String activityNameandId) {

    this.activityId = activityNameandId.substring(0, activityNameandId.indexOf("and")).trim();

    this.activityName =
        activityNameandId
            .substring(activityNameandId.indexOf("and ") + 3, activityNameandId.length())
            .trim();

    for (String id : list) {
      if (this.activityId.equals(id)) {
        number++;
      }
    }
    list.add(this.activityId);

    // Create review id
    String reviewId = createReviewDigits();
    this.activityId = this.activityId + "-" + reviewId;

    String theReview = MessageCli.REVIEW_ADDED.getMessage("Public", activityId, activityName);
    reviews.add(theReview);

    return this.activityId;
  }

  public String createReviewDigits() {
    // Creates the review digits
    this.reviewDigits = "R" + this.number;
    this.number = 1;
    return this.reviewDigits;
  }
}
