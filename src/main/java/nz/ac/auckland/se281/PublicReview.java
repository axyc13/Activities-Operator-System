package nz.ac.auckland.se281;

import java.util.ArrayList;

public class PublicReview extends Review {

  public ArrayList<String> reviews = new ArrayList<>();
  private String[] options;

  @Override
  public void getMessage(String activityId, String activityName, String[] options) {
    this.options = options;
    String theReview = MessageCli.REVIEW_ADDED.getMessage("Public", activityId, activityName);
    reviews.add(theReview);
    MessageCli.REVIEW_ADDED.printMessage("Public", activityId, activityName);
    return;
  }

  @Override
  public void printReviews() {}
}
