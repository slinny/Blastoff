package productions.darthplagueis.capstone.networking;

import java.util.List;

import productions.darthplagueis.capstone.model.spacex.SpaceXResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 *
 */
public interface SpaceXService {

    String pastLaunchesEndpoint = "v2/launches/{type}";
    String typePath = "type";
    String upcomingLaunchesEndpoint = "v2/launches/upcoming";

    @GET(pastLaunchesEndpoint)
    Call<List<SpaceXResponse>> getLaunches(@Path(typePath) String typeOfLaunch);
}
