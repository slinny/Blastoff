package productions.darthplagueis.capstone.networking;

import java.util.List;

import productions.darthplagueis.capstone.model.spacex.LaunchesResponse;
import productions.darthplagueis.capstone.model.spacex.RocketsResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 *
 */
public interface SpaceXService {

    String pastLaunchesEndpoint = "v2/launches/{type}";
    String rocketsEndpoint = "v2/rockets";
    String typePath = "type";

    @GET(pastLaunchesEndpoint)
    Call<List<LaunchesResponse>> getLaunches(@Path(typePath) String typeOfLaunch);

    @GET(rocketsEndpoint)
    Call<List<RocketsResponse>> getRockets();
}
