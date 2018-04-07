package productions.darthplagueis.capstone.networking;

import productions.darthplagueis.capstone.model.marsrover.RoverResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Uses the Mars rover endpoint to make an API call and retrieve a list of
 * the different rover photos.
 */
public interface NasaService {

    String marsRoverPhotosEndpoint = "mars-photos/api/v1/rovers/{rover-name}/photos";
    String namePath = "rover-name";

    // For the name parameter, one of three strings can be used:
    // curiosity , opportunity , or spirit .
    @GET(marsRoverPhotosEndpoint)
    Call<RoverResponse> getListOfRoverPhotos(@Path(namePath) String name, @Query("sol") int sol, @Query("page") int page, @Query("api_key") String apiKey);
}
