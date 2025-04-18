package nz.ac.auckland.se281;

import java.util.ArrayList;

public abstract class Review {

  // Prints review successfully created message
  abstract void getMessage(String activityId, String activityName, String[] options);

  // Prints reviews
  abstract void printReviews();

  // Endorses review
  abstract void endorseReview(String reviewId);

  // Rejects review
  abstract void resolveReview(String reviewId, String response);

  // Uploads image
  abstract void uploadImage(String reviewId, String imageName);

  // Retrieve reviews
  abstract ArrayList<String> getReviews();
}
