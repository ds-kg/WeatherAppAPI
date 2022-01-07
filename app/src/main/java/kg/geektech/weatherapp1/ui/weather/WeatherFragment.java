package kg.geektech.weatherapp1.ui.weather;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import kg.geektech.weatherapp1.R;
import kg.geektech.weatherapp1.common.Resource;
import kg.geektech.weatherapp1.data.local.WeatherDao;
import kg.geektech.weatherapp1.data.model.Coord;
import kg.geektech.weatherapp1.data.model.Main;
import kg.geektech.weatherapp1.data.model.Sys;
import kg.geektech.weatherapp1.data.model.Weather;
import kg.geektech.weatherapp1.data.model.Weather__1;
import kg.geektech.weatherapp1.data.model.Wind;
import kg.geektech.weatherapp1.databinding.FragmentWeatherBinding;

@AndroidEntryPoint
public class WeatherFragment extends Fragment {

    private NavController navController;
    private WeatherViewModel model;
    private Wind wind;
    private Main main;
    private WeatherFragmentArgs args;
    private Weather weather;
    private Coord coord;
    private Sys sys;
    private ArrayList<Weather__1> weather__1s = new ArrayList<>();
    private FragmentWeatherBinding binding;

    @Inject
    WeatherDao dao;

    public WeatherFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NavHostFragment navHostFragment = (NavHostFragment) requireActivity()
                .getSupportFragmentManager().findFragmentById(R.id.nav_host);
        assert navHostFragment != null;
        navController = navHostFragment.getNavController();
        if (getArguments() != null)
            args = WeatherFragmentArgs.fromBundle(getArguments());
        model = new ViewModelProvider(requireActivity()).get(WeatherViewModel.class);
        model.fetchMap(args.getLatitude(), args.getLongitude());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentWeatherBinding.inflate(inflater);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        model.data.observe(getViewLifecycleOwner(), response -> {
            switch (response.status){
                case SUCCESS:
                    wind = response.data.getWind();
                    weather = response.data;
                    main = response.data.getMain();
                    sys = response.data.getSys();
                    weather__1s = (ArrayList<Weather__1>) response.data.getWeather();
                    setWeather();
                    binding.progress.setVisibility(View.GONE);
                    coord = response.data.getCoord();
                    break;

                case LOADING:
                    binding.progress.setVisibility(View.VISIBLE);
                    break;

                case ERROR:
                    Toast.makeText(requireContext(), "Internet not connected!Loading last data", Toast.LENGTH_SHORT).show();
                    binding.progress.setVisibility(View.GONE);
                    wind = dao.getWeather().getWind();
                    main = dao.getWeather().getMain();
                    sys = dao.getWeather().getSys();
                    weather = dao.getWeather();
                    weather__1s = (ArrayList<Weather__1>) dao.getWeather().getWeather();
                    coord = dao.getWeather().getCoord();
                    break;

            }
        });
        binding.cityBtn.setOnClickListener(v -> navController.navigate(R.id.action_weatherFragment_to_locationFragment));
    }

    @SuppressLint("SetTextI18n")
    private void setWeather() {

        binding.weatherNowTv.setText(weather__1s.get(0).getMain());
        Glide.with(requireContext())
                .load("https://openweathermap.org/img/wn/" + weather__1s.get(0).getIcon() + ".png")
                .override(100, 100)
                .into(binding.weatherIv);
        binding.tempmaxTv.setText(String.valueOf((int) Math.round(main.getTempMax())));
        binding.windTv.setText((int) Math.round(wind.getSpeed()) + " m/ s");
        binding.cityBtn.setText(weather.getName());
        binding.tempnowTv.setText(String.valueOf((int) Math.round(main.getTemp())));
        binding.barometrTv.setText(main.getPressure() + "mBar");
        binding.textViewHumidity.setText(main.getHumidity() + "%");
        binding.tempminTv.setText(String.valueOf((int) Math.round(main.getTempMin())));
    }

}