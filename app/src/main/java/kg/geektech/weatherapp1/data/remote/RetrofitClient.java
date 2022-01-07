package kg.geektech.weatherapp1.data.remote;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private OkHttpClient okHttpClient(){
        return new OkHttpClient.Builder().writeTimeout(10, TimeUnit.SECONDS).connectTimeout
                (10,TimeUnit.SECONDS).readTimeout(10,TimeUnit.SECONDS).addInterceptor(provide()).build();
    }

    private Interceptor provide() {
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    private Retrofit retrofit = new Retrofit.Builder().client(okHttpClient()).
            baseUrl("https://api.openweathermap.org/data/2.5/").addConverterFactory(GsonConverterFactory.create()).build();

    public WeatherApi weatherApi(){
        return retrofit.create(WeatherApi.class);
    }
}
