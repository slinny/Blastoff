package productions.darthplagueis.capstone.abstractclasses;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Abstract class used to create a Retrofit singleton from which
 * a concrete class can build it's api calls.
 */
public abstract class AbstractRetrofitFactory {

    private Retrofit retrofit;

    /**
     * Receives base url from concrete class and uses it to create
     * an instance of retrofit.
     */
    public abstract String getBaseUrl();

    protected Retrofit buildRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(getBaseUrl())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
