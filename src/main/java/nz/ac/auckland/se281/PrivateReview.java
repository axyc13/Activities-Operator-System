package nz.ac.auckland.se281;

import java.util.ArrayList;

public class PrivateReview extends Review {

  public ArrayList<String> reviews = new ArrayList<>();
  private String[] options;
  private ArrayList<String[]> allTheOptions = new ArrayList<>();
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
    if (Integer.valueOf(options[2]) > 5) {
      options[2] = "5";
    }
    if (Integer.valueOf(options[2]) < 1) {
      options[2] = "1";
    }
    allTheOptions.add(options);
    String theReview = reviewId + " and " + options[2];
    reviews.add(theReview);
    MessageCli.REVIEW_ADDED.printMessage("Private", reviewId, activityName);
    return;
  }

  @Override
  public void printReviews() {
    for (int i = 0; i < reviews.size(); i++) {
      String reviewId = reviews.get(i).substring(0, reviews.get(i).indexOf(" and "));

      MessageCli.REVIEW_ENTRY_HEADER.printMessage(
          allTheOptions.get(i)[2], "5", "Private", reviewId, allTheOptions.get(i)[0]);
      MessageCli.REVIEW_ENTRY_REVIEW_TEXT.printMessage(allTheOptions.get(i)[3]);
      if (allTheOptions.get(i)[4].equals("n")) {
        MessageCli.REVIEW_ENTRY_RESOLVED.printMessage("-");
      } else if (this.isResolved == true) {
        MessageCli.REVIEW_ENTRY_RESOLVED.printMessage(this.response);
      } else {
        MessageCli.REVIEW_ENTRY_FOLLOW_UP.printMessage(allTheOptions.get(i)[1]);
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
