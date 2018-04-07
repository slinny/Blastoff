package productions.darthplagueis.capstone.networking;

import android.util.Log;

import java.util.List;

import productions.darthplagueis.capstone.abstractclasses.AbstractRetrofitFactory;
import productions.darthplagueis.capstone.model.spacex.LaunchesResponse;
import productions.darthplagueis.capstone.model.spacex.RocketsResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 *
 */
public class SpaceXRetrofitFactory extends AbstractRetrofitFactory {

    private static final String TAG = SpaceXRetrofitFactory.class.getSimpleName();

    private static SpaceXRetrofitFactory retrofitFactory;

    private SpaceXLaunchListener spaceXLaunchListener = null;
    private SpaceXRocketListener spaceXRocketListener = null;

    public static SpaceXRetrofitFactory getInstance() {
        if (retrofitFactory == null) {
            retrofitFactory = new SpaceXRetrofitFactory();
        }
        return retrofitFactory;
    }

    private SpaceXRetrofitFactory() {
    }

    public void setSpaceXLaunchListener(SpaceXLaunchListener spaceXLaunchListener) {
        this.spaceXLaunchListener = spaceXLaunchListener;
    }

    public void setSpaceXRocketListener(SpaceXRocketListener spaceXRocketListener) {
        this.spaceXRocketListener = spaceXRocketListener;
    }

    public void getLaunchesResponseList(String typeOfLaunch) {
        SpaceXService service = buildRetrofit().create(SpaceXService.class);
        Call<List<LaunchesResponse>> responseCall = service.getLaunches(typeOfLaunch);
        responseCall.enqueue(new Callback<List<LaunchesResponse>>() {
            @Override
            public void onResponse(Call<List<LaunchesResponse>> call, Response<List<LaunchesResponse>> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + response.isSuccessful());
                    List<LaunchesResponse> responseList = response.body();
                    Log.d(TAG, "onResponse: listSize=" + responseList.size());
                    spaceXLaunchListener.launchListCallBack(responseList);
                }
            }

            @Override
            public void onFailure(Call<List<LaunchesResponse>> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
                spaceXLaunchListener.onErrorCallBack(t);
            }
        });
    }

    public void getRocketResponseList() {
        SpaceXService service = buildRetrofit().create(SpaceXService.class);
        Call<List<RocketsResponse>> responseCall = service.getRockets();
        responseCall.enqueue(new Callback<List<RocketsResponse>>() {
            @Override
            public void onResponse(Call<List<RocketsResponse>> call, Response<List<RocketsResponse>> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + response.isSuccessful());
                    List<RocketsResponse> responseList = response.body();
                    Log.d(TAG, "onResponse: listSize=" + responseList.size());
                    spaceXRocketListener.rocketListCallBack(responseList);
                }
            }

            @Override
            public void onFailure(Call<List<RocketsResponse>> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
                spaceXRocketListener.onErrorCallBack(t);
            }
        });
    }

    @Override
    public String getBaseUrl() {
        return BaseUrls.SpaceXApi.getUrl();
    }

    public interface SpaceXLaunchListener {
        void launchListCallBack(List<LaunchesResponse> responseList);

        void onErrorCallBack(Throwable t);
    }

    public interface SpaceXRocketListener {
        void rocketListCallBack(List<RocketsResponse> responseList);

        void onErrorCallBack(Throwable t);
    }
}
