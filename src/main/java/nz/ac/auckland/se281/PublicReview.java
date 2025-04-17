package nz.ac.auckland.se281;

import java.util.ArrayList;

public class PublicReview extends Review {

  public ArrayList<String> reviews = new ArrayList<>();
  private String[] options;
  private String activityId;

  @Override
  public void getMessage(String activityId, String activityName, String[] options) {
    this.activityId = activityId;
    this.options = options;
    String theReview = MessageCli.REVIEW_ADDED.getMessage("Public", activityId, activityName);
    reviews.add(theReview);
    MessageCli.REVIEW_ADDED.printMessage("Public", activityId, activityName);
    return;
  }

  @Override
  public void printReviews() {
    for (String review : reviews) {
      if (review.contains(activityId)) {
        // REVIEW_ENTRY_HEADER("  * [%s/%s] %s review (%s) by '%s'"),
        MessageCli.REVIEW_ENTRY_HEADER.printMessage(
            options[2], "5", "Public", activityId, options[0]);
        MessageCli.REVIEW_ENTRY_REVIEW_TEXT.printMessage(options[3]);
      }
    }
  }
}
