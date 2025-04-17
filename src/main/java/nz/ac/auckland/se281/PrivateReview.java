package nz.ac.auckland.se281;

import java.util.ArrayList;

public class PrivateReview extends Review {

  public ArrayList<String> reviews = new ArrayList<>();
  private String[] options;
  private String reviewId;
  private boolean isResolved = false;
  private String response;

  @Override
  public ArrayList<String> getReviews() {
    return reviews;
  }

  @Override
  public void getMessage(String reviewId, String activityName, String[] options) {
    this.reviewId = reviewId;
    this.options = options;
    String theReview = reviewId + " and " + options[2];
    reviews.add(theReview);
    MessageCli.REVIEW_ADDED.printMessage("Private", reviewId, activityName);
    return;
  }

  @Override
  public void printReviews() {
    for (String review : reviews) {
      if (review.contains(reviewId)) {
        // REVIEW_ENTRY_HEADER("  * [%s/%s] %s review (%s) by '%s'"),
        MessageCli.REVIEW_ENTRY_HEADER.printMessage(
            options[2], "5", "Private", reviewId, options[0]);
        MessageCli.REVIEW_ENTRY_REVIEW_TEXT.printMessage(options[3]);
        if (options[4].equals("n")) {
          MessageCli.REVIEW_ENTRY_RESOLVED.printMessage("-");
        } else if (this.isResolved == true) {
          MessageCli.REVIEW_ENTRY_RESOLVED.printMessage(this.response);
        } else {
          MessageCli.REVIEW_ENTRY_FOLLOW_UP.printMessage(options[1]);
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
    this.response = response;
    for (String review : reviews) {
      if (review.contains(reviewId)) {
        MessageCli.REVIEW_RESOLVED.printMessage(reviewId);
        this.isResolved = true;
        return;
      }
    }
  }

  @Override
  public void uploadImage(String reviewId, String imageName) {
    for (String review : reviews) {
      if (review.contains(reviewId)) {
        MessageCli.REVIEW_IMAGE_NOT_ADDED_NOT_EXPERT.printMessage(reviewId);
        return;
      }
    }
  }
}
