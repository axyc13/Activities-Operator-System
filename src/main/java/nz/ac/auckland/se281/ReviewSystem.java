package nz.ac.auckland.se281;

import java.util.ArrayList;

public class ReviewSystem {

  private ArrayList<String> reviews = new ArrayList<>();
  protected ArrayList<String> test = new ArrayList<>();
  private int count = 0;

  private Activity activity = new Activity();
  private String activityName;

  public ReviewSystem() {}

  public void checkIfActivityIsAtLocation(String activityId) {
    activityId = activityId.trim().substring(2);
    // Check if the activity ID already exists

    // activityId = activity.getActivityName(activityId);
    // System.out.println(activityId);

    for (String review : reviews) {
      if (review.contains(activityId)) {
        this.count++;
      }
    }

    if (count == 0) {
      // REVIEWS_FOUND("There %s %s review%s for activity '%s'."),
      MessageCli.REVIEWS_FOUND.printMessage("are", "no", "s", activityId);
    } else if (count == 1) {
      MessageCli.REVIEWS_FOUND.printMessage("is", "1", "", activityId + ":");
      for (String review : reviews) {
        if (review.contains(activityId)) {
          System.out.println(review);
        }
      }
    } else if (count >= 2) {
      MessageCli.REVIEWS_FOUND.printMessage("are", Integer.toString(count), "s", activityId + ":");
      for (String review : reviews) {
        if (review.contains(activityId)) {
          System.out.println(review);
        }
      }
    }
    this.count = 0;
  }
}
