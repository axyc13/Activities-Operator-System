package nz.ac.auckland.se281;

import java.util.ArrayList;

public class ExpertReview extends Review {

  public ArrayList<String> reviews = new ArrayList<>();
  private String[] options;
  private ArrayList<String[]> allTheOptions = new ArrayList<>();
  private String reviewId;
  public ArrayList<String> images = new ArrayList<>();
  private boolean hasImages = false;

  @Override
  public ArrayList<String> getReviews() {
    return reviews;
  }

  @Override
  public void getMessage(String reviewId, String activityName, String[] options) {
    this.reviewId = reviewId;
    this.options = options;
    if (Integer.valueOf(options[1]) > 5) {
      options[1] = "5";
    }
    if (Integer.valueOf(options[1]) < 1) {
      options[1] = "1";
    }
    allTheOptions.add(options);
    String theReview = reviewId + " and " + options[1];
    reviews.add(theReview);
    MessageCli.REVIEW_ADDED.printMessage("Expert", reviewId, activityName);
    return;
  }

  @Override
  public void printReviews() {
    for (int i = 0; i < reviews.size(); i++) {
      String reviewId = reviews.get(i).substring(0, reviews.get(i).indexOf(" and "));

      MessageCli.REVIEW_ENTRY_HEADER.printMessage(
          allTheOptions.get(i)[1], "5", "Expert", reviewId, allTheOptions.get(i)[0]);
      MessageCli.REVIEW_ENTRY_REVIEW_TEXT.printMessage(allTheOptions.get(i)[2]);
      if (allTheOptions.get(i)[3].equals("y")) {
        MessageCli.REVIEW_ENTRY_RECOMMENDED.printMessage();
      }
      if (this.hasImages == true) {
        String images = this.images.toString();
        images = images.substring(1, images.length() - 1);
        images = images.replace(", ", ",");
        MessageCli.REVIEW_ENTRY_IMAGES.printMessage(images);
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

  @Override
  public void uploadImage(String reviewId, String imageName) {
    for (String review : reviews) {
      if (review.contains(reviewId)) {
        MessageCli.REVIEW_IMAGE_ADDED.printMessage(imageName, reviewId);
        images.add(imageName);
        this.hasImages = true;
        return;
      }
    }
  }
}
