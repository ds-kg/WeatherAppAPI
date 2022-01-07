package kg.geektech.weatherapp1.data.repository;

import androidx.lifecycle.MutableLiveData;

import javax.inject.Inject;

import kg.geektech.weatherapp1.common.Resource;
import kg.geektech.weatherapp1.data.local.WeatherDao;
import kg.geektech.weatherapp1.data.model.Weather;
import kg.geektech.weatherapp1.data.remote.WeatherApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainRepository {
  //  private String city;
    private WeatherApi api;
    private WeatherDao dao;

    @Inject
    public MainRepository(WeatherApi api, WeatherDao dao) {
        this.api = api;
        this.dao = dao;
    }

//    //public void setCity(String city) {
//        this.city = city;
//    }

    public MutableLiveData<Resource<Weather>> getWeatherMap(String lon, String lat){
        MutableLiveData<Resource<Weather>> liveData = new MutableLiveData<>();
        liveData.setValue(Resource.loading());
        api.getTemp(lat, lon, "17faa337223ce310f99b3891640a9171", "metric").enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                if (response.isSuccessful()){
                    liveData.setValue(Resource.success(response.body()));
                    dao.Insert(response.body());
                }
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                liveData.setValue(Resource.error(t.getLocalizedMessage(),null));
            }
        });
        return liveData;
    }
}
