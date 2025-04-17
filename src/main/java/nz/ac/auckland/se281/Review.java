package nz.ac.auckland.se281;

public abstract class Review {

  abstract void getMessage(String activityId, String activityName, String[] options);

  abstract void printReviews();

  abstract void endorseReview(String reviewId);
}
