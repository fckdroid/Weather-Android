package sasd97.java_blog.xyz.yandexweather.data.models.places;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Maksim Sukhotski on 7/25/2017.
 */

public class PlaceDetailsResponse {
    public static final String CITY_NAME = "locality";
    private static final String UNKNOWN_CITY = "Unknown city";
    private static final String COMMA = ", ";
    public static final String NULL_POINTER_ERROR = "Value result and resultArray is null.";

    @SerializedName("result")
    @Expose
    private Result result;

    @SerializedName("results")
    @Expose
    private Result[] resultArray;

    @SerializedName("status")
    @Expose
    private String status;

    public LatLng getCoords() {
        if (result != null) {
            return new LatLng(result.geometry.location.lat, result.geometry.location.lng);
        } else if (resultArray != null) {
            return new LatLng(resultArray[0].geometry.location.lat, resultArray[0].geometry.location.lng);
        } else {
            throw new IllegalStateException(NULL_POINTER_ERROR);
        }
    }

    /**
     * @return city or place name from formatted address like
     * "A44, Юж. Судан" or "пер. Корженевского 4, Минск, Беларусь"
     */
    public String getCity() {
        if (resultArray[0].formattedAddress == null) {
            return UNKNOWN_CITY;
        }
        String[] addressParts = resultArray[0].formattedAddress.split(COMMA);
        int cityNamePos = addressParts.length == 1 ? 0 : addressParts.length - 2;
        return addressParts[cityNamePos];
    }
}
