package nz.ac.auckland.se281;

import java.util.ArrayList;

public class PublicReview extends Review {

  public ArrayList<String> reviews = new ArrayList<>();
  private String[] options;
  private String reviewId;
  private boolean isEndorsed = false;

  @Override
  public void getMessage(String reviewId, String activityName, String[] options) {
    this.reviewId = reviewId;
    this.options = options;
    String theReview = MessageCli.REVIEW_ADDED.getMessage("Public", reviewId, activityName);
    reviews.add(theReview);
    MessageCli.REVIEW_ADDED.printMessage("Public", reviewId, activityName);
    return;
  }

  @Override
  public void printReviews() {
    for (String review : reviews) {
      if (review.contains(reviewId)) {
        // REVIEW_ENTRY_HEADER("  * [%s/%s] %s review (%s) by '%s'"),
        MessageCli.REVIEW_ENTRY_HEADER.printMessage(
            options[2], "5", "Public", reviewId, options[0]);
        MessageCli.REVIEW_ENTRY_REVIEW_TEXT.printMessage(options[3]);
        if (this.isEndorsed == true) {
          MessageCli.REVIEW_ENTRY_ENDORSED.printMessage();
        }
      }
    }
  }

  @Override
  public void endorseReview(String reviewId) {
    for (String review : reviews) {
      if (review.contains(reviewId)) {
        MessageCli.REVIEW_ENDORSED.printMessage(reviewId);
        this.isEndorsed = true;
        return;
      }
    }
  }

  @Override
  public void resolveReview(String reviewId, String response) {
    for (String review : reviews) {
      if (review.contains(reviewId)) {
        MessageCli.REVIEW_NOT_RESOLVED.printMessage(reviewId);
        return;
      }
    }
  }
}
