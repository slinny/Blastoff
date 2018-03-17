package productions.darthplagueis.capstone.networking;

/**
 * Enum that contains base urls for the APIs being called.
 */
public enum BaseUrls {

    NasaApi("https://api.nasa.gov/");

    private final String url;

    BaseUrls(String url) {
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }
}
