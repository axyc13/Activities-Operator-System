package nz.ac.auckland.se281;

import java.util.ArrayList;

public abstract class Review {

  abstract void getMessage(String activityId, String activityName, String[] options);

  abstract void printReviews();

  abstract void endorseReview(String reviewId);

  abstract void resolveReview(String reviewId, String response);

  abstract void uploadImage(String reviewId, String imageName);

  abstract ArrayList<String> getReviews();
}
