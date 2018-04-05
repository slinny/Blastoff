package productions.darthplagueis.capstone.networking;

import android.util.Log;

import java.util.List;

import productions.darthplagueis.capstone.abstractclasses.AbstractRetrofitFactory;
import productions.darthplagueis.capstone.model.spacex.SpaceXResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 *
 */
public class SpaceXRetrofitFactory extends AbstractRetrofitFactory {

    private static final String TAG = SpaceXRetrofitFactory.class.getSimpleName();

    private static SpaceXRetrofitFactory retrofitFactory;

    private LaunchListListener launchListListener = null;

    public static SpaceXRetrofitFactory getInstance() {
        if (retrofitFactory == null) {
            retrofitFactory = new SpaceXRetrofitFactory();
        }
        return retrofitFactory;
    }

    private SpaceXRetrofitFactory() {
    }

    public void setLaunchListListener(LaunchListListener launchListListener) {
        this.launchListListener = launchListListener;
    }

    public void getLaunchesResponseList(String typeOfLaunch) {
        SpaceXService service = buildRetrofit().create(SpaceXService.class);
        Call<List<SpaceXResponse>> responseCall = service.getLaunches(typeOfLaunch);
        responseCall.enqueue(new Callback<List<SpaceXResponse>>() {
            @Override
            public void onResponse(Call<List<SpaceXResponse>> call, Response<List<SpaceXResponse>> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + response.isSuccessful());
                    List<SpaceXResponse> responseList = response.body();
                    Log.d(TAG, "onResponse: listSize=" + responseList.size());
                    launchListListener.responseListCallBack(responseList);
                }
            }

            @Override
            public void onFailure(Call<List<SpaceXResponse>> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
                launchListListener.onErrorCallBack(t);
            }
        });
    }

    @Override
    public String getBaseUrl() {
        return BaseUrls.SpaceXApi.getUrl();
    }

    public interface LaunchListListener {
        void responseListCallBack(List<SpaceXResponse> responseList);

        void onErrorCallBack(Throwable t);
    }
}
