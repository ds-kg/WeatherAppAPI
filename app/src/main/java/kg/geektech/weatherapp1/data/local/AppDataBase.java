package kg.geektech.weatherapp1.data.local;


import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import kg.geektech.weatherapp1.data.local.converter.MainConvertor;
import kg.geektech.weatherapp1.data.model.Weather;

@Database(entities = {Weather.class}, version = 1)
@TypeConverters({MainConvertor.class})
public abstract class AppDataBase extends RoomDatabase {
    public abstract WeatherDao weatherDao();
}
