package productions.darthplagueis.capstone.model.marsrover.responsemodel;

/**
 * Model class for the individual photos contained in the list
 * returned from the service call.
 */
public class Photos {

    private int id;
    private int sol;
    private String img_src;
    private String earth_date;
    private Rover rover;

    public int getId() {
        return id;
    }

    public int getSol() {
        return sol;
    }

    public String getImg_src() {
        return img_src;
    }

    public String getEarth_date() {
        return earth_date;
    }

    public Rover getRover() {
        return rover;
    }
}
