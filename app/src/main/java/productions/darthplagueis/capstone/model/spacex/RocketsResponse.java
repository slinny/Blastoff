package productions.darthplagueis.capstone.model.spacex;

import productions.darthplagueis.capstone.model.spacex.rocketresponsemodel.Diameter;
import productions.darthplagueis.capstone.model.spacex.rocketresponsemodel.Height;
import productions.darthplagueis.capstone.model.spacex.rocketresponsemodel.Mass;

/**
 *
 */
public class RocketsResponse {

    private String id;
    private String name;
    private String type;
    private int stages;
    private boolean active;
    private int success_rate_pct;
    private String first_flight;
    private Height height;
    private Diameter diameter;
    private Mass mass;
    private String description;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getStages() {
        return stages;
    }

    public boolean isActive() {
        return active;
    }

    public int getSuccess_rate_pct() {
        return success_rate_pct;
    }

    public String getFirst_flight() {
        return first_flight;
    }

    public Height getHeight() {
        return height;
    }

    public Diameter getDiameter() {
        return diameter;
    }

    public Mass getMass() {
        return mass;
    }

    public String getDescription() {
        return description;
    }
}
