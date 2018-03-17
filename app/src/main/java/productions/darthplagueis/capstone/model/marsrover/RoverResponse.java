package productions.darthplagueis.capstone.model.marsrover;

import java.util.List;

import productions.darthplagueis.capstone.model.marsrover.responsemodel.Photos;

/**
 * The response from the Nasa Mars Rover Service contains an array of
 * photos.
 */
public class RoverResponse {

    private List<Photos> photos;

    public List<Photos> getPhotos() {
        return photos;
    }
}
