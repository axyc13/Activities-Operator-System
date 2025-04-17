package nz.ac.auckland.se281;

import java.util.ArrayList;

public class ExpertReview extends Review {

  public ArrayList<String> reviews = new ArrayList<>();
  private String[] options;
  private String reviewId;

  @Override
  public void getMessage(String reviewId, String activityName, String[] options) {
    this.reviewId = reviewId;
    this.options = options;
    String theReview = MessageCli.REVIEW_ADDED.getMessage("Public", reviewId, activityName);
    reviews.add(theReview);
    MessageCli.REVIEW_ADDED.printMessage("Expert", reviewId, activityName);
    return;
  }

  @Override
  public void printReviews() {
    for (String review : reviews) {
      if (review.contains(reviewId)) {
        // REVIEW_ENTRY_HEADER("  * [%s/%s] %s review (%s) by '%s'"),
        MessageCli.REVIEW_ENTRY_HEADER.printMessage(
            options[1], "5", "Expert", reviewId, options[0]);
        MessageCli.REVIEW_ENTRY_REVIEW_TEXT.printMessage(options[2]);
        if (options[3].equals("y")) {
          MessageCli.REVIEW_ENTRY_RECOMMENDED.printMessage();
        }
      }
    }
  }

  @Override
  public void endorseReview(String reviewId) {
    for (String review : reviews) {
      if (review.contains(reviewId)) {
        MessageCli.REVIEW_NOT_ENDORSED.printMessage(reviewId);
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
