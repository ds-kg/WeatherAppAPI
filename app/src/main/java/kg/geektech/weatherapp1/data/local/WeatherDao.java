package kg.geektech.weatherapp1.data.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import kg.geektech.weatherapp1.data.model.Weather;

@Dao
public interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void Insert(Weather weather);
    @Query("SELECT*FROM weather")
    Weather getWeather();
}
