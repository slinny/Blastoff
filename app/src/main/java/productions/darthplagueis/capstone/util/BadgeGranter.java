package productions.darthplagueis.capstone.util;

import static productions.darthplagueis.capstone.util.Constants.ALL_LAUNCHES;
import static productions.darthplagueis.capstone.util.Constants.AR_ACTIVITY;
import static productions.darthplagueis.capstone.util.Constants.FALCON_ROCKETS;
import static productions.darthplagueis.capstone.util.Constants.GAME_ACTIVITY;
import static productions.darthplagueis.capstone.util.Constants.ROVER_PHOTOS;

/**
 *
 */
public class BadgeGranter {

    public static String grantBadge(String currentActivity, int badgeCounter) {
        switch (currentActivity) {
            case GAME_ACTIVITY:
            case FALCON_ROCKETS:
                if (badgeCounter == 1) {
                    return "You got your first badge. Great job!. Keep playing to learn more.";
                } else if (badgeCounter == 2) {
                    return "Wow! A second badge. You're on your way to becoming a full fledged Rocketeer.";
                } else if (badgeCounter == 3) {
                    return "Now that you've gotten your third badge - there's no stopping you. Rockets are awesome, aren't they?";
                } else {
                    return "This is too easy for you. Another badge awarded towards becoming a Rockstar Rocketeer!";
                }
            case AR_ACTIVITY:
            case ROVER_PHOTOS:
                if (badgeCounter == 1) {
                    return "You got your first badge. Great job!. Have fun on Mars!";
                } else if (badgeCounter == 2) {
                    return "Wow! A second badge. Soon you'll uncover all of Mar's secrets.";
                } else if (badgeCounter == 3) {
                    return "Now that you've gotten your third badge there's no stopping you. You'll go down in history.";
                } else {
                    return "This is too easy for you. Another badge awarded towards becoming an Astounding Astronaut.";
                }
            case ALL_LAUNCHES:
                if (badgeCounter == 1) {
                    return "You got your first badge. Great job!. Maybe one day soon you'll venture into space.";
                } else if (badgeCounter == 2) {
                    return "Wow! A second badge. Every flight into space brings you one step closer.";
                } else if (badgeCounter == 3) {
                    return "Now that you've gotten your third badge there's no stopping you. Can you imagine yourself flying into space?";
                } else {
                    return "This is too easy for you. Another badge awarded towards becoming an Exhilarating Explorer.";
                }
        }
        return "You have been awarded a badge for your curiosity. Look out for more.";
    }
}
