package kg.geektech.weatherapp1.data.remote;

import kg.geektech.weatherapp1.data.model.Weather;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {
    @GET("weather?")
    Call<Weather> getTemp(
            @Query("lat") String lat,
            @Query("lon") String lon,
            @Query("appid") String appId,
            @Query("units") String units
            );
//    @GET("weather?")
//    Call<Weather> getCity(
//        @Query("q") String city,
//        @Query("appid") String appId,
//        @Query("units") String units
//    );

}
