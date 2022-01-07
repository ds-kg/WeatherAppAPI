package kg.geektech.weatherapp1.di;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import kg.geektech.weatherapp1.data.local.AppDataBase;
import kg.geektech.weatherapp1.data.local.RoomClient;
import kg.geektech.weatherapp1.data.local.WeatherDao;
import kg.geektech.weatherapp1.data.remote.RetrofitClient;
import kg.geektech.weatherapp1.data.remote.WeatherApi;
import kg.geektech.weatherapp1.data.repository.MainRepository;

@Module
@InstallIn(SingletonComponent.class)
public abstract class AppModule {

    @Provides
    public static WeatherApi provideApi(){
        return new RetrofitClient().weatherApi();
    }

    @Provides
    public static MainRepository provideMainRepository(WeatherApi api, WeatherDao dao){
        return new MainRepository(api,dao);
    }
    @Provides
    public static AppDataBase provideAppDatabase(@ApplicationContext Context context){
        return new RoomClient().appDataBase(context);
    }
    @Provides
    public static WeatherDao provideWeatherDao(AppDataBase database){
        return database.weatherDao();
    }
}

