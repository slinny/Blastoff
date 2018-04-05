package productions.darthplagueis.capstone.networking;

import android.util.Log;

import java.util.List;

import productions.darthplagueis.capstone.abstractclasses.AbstractRetrofitFactory;
import productions.darthplagueis.capstone.model.marsrover.RoverResponse;
import productions.darthplagueis.capstone.model.marsrover.responsemodel.Photos;
import productions.darthplagueis.capstone.util.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Concrete implementation of the AbstractRetrofitFactory.
 * It is used to make service calls and then pass the response over
 * to activity/fragment that will display it.
 */
public class NasaRetrofitFactory extends AbstractRetrofitFactory {

    private static final String TAG = NasaRetrofitFactory.class.getSimpleName();

    private static NasaRetrofitFactory retrofitFactory;

    private PhotoListListener photoListListener = null;

    private int solNumber;

    public static NasaRetrofitFactory getInstance() {
        if (retrofitFactory == null) {
            retrofitFactory = new NasaRetrofitFactory();
        }
        return retrofitFactory;
    }

    private NasaRetrofitFactory() {
    }

    public void setPhotoListListener(PhotoListListener photoListListener) {
        this.photoListListener = photoListListener;
    }

    public void setSolNumber(int solNumber) {
        this.solNumber = solNumber;
    }

    public void retrieveListOfRoverPhotos(String roverName) {
        NasaMarsRoverService service = buildRetrofit().create(NasaMarsRoverService.class);
        Call<RoverResponse> responseCall = service.getListOfRoverPhotos(roverName, solNumber, 1, Constants.NASA_API_KEY);
        responseCall.enqueue(new Callback<RoverResponse>() {
            @Override
            public void onResponse(Call<RoverResponse> call, Response<RoverResponse> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + response.isSuccessful());
                    Log.d(TAG, "onResponse: listSize=" + response.body().getPhotos().size());
                    photoListListener.responseListCallBack(response.body().getPhotos());
                }
            }

            @Override
            public void onFailure(Call<RoverResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
                photoListListener.onErrorCallBack(t);
            }
        });
    }

    @Override
    public String getBaseUrl() {
        return BaseUrls.NasaApi.getUrl();
    }

    /**
     * Interface used to pass the response list from the retrofit call
     * to the activity/fragment that's going to use the list of photos.
     */
    public interface PhotoListListener {
        void responseListCallBack(List<Photos> responseList);

        void onErrorCallBack(Throwable t);
    }
}
