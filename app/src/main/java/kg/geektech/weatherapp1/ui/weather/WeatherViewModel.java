package kg.geektech.weatherapp1.ui.weather;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import kg.geektech.weatherapp1.common.Resource;
import kg.geektech.weatherapp1.data.model.Weather;
import kg.geektech.weatherapp1.data.repository.MainRepository;

@HiltViewModel
public class WeatherViewModel extends ViewModel {
    private MainRepository mainRepository;
   // private String city;
    public LiveData<Resource<Weather>> data;

    @Inject
    public WeatherViewModel(MainRepository mainRepository) {
        this.mainRepository = mainRepository;
    }
//
//    public void fetchTemp(){
//        mainRepository.setCity(city);
//        data = mainRepository.getWeatherCity();
//    }
    public void fetchMap(String lat, String lon){
        data = mainRepository.getWeatherMap(lat, lon);
    }
}
