package productions.darthplagueis.capstone.model.spacex;

import productions.darthplagueis.capstone.model.spacex.launchresponsemodel.LaunchSite;
import productions.darthplagueis.capstone.model.spacex.launchresponsemodel.Links;
import productions.darthplagueis.capstone.model.spacex.launchresponsemodel.Rocket;

/**
 *
 */
public class LaunchesResponse {

    private int flight_number;
    private String launch_year;
    private String launch_date_utc;
    private Rocket rocket;
    private LaunchSite launch_site;
    private Links links;

    public int getFlight_number() {
        return flight_number;
    }

    public String getLaunch_year() {
        return launch_year;
    }

    public String getLaunch_date_utc() {
        return launch_date_utc;
    }

    public Rocket getRocket() {
        return rocket;
    }

    public LaunchSite getLaunch_site() {
        return launch_site;
    }

    public Links getLinks() {
        return links;
    }
}
