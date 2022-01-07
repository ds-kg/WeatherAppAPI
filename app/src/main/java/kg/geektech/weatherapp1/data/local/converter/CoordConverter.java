package kg.geektech.weatherapp1.data.local.converter;


import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import kg.geektech.weatherapp1.data.model.Coord;
import kg.geektech.weatherapp1.data.model.Wind;

public class CoordConverter {
    @TypeConverter
    public String fromMainString(Coord coord){
        if (coord == null){
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Coord>() {}.getType();
        return gson.toJson(coord,type);
    }
    @TypeConverter
    public Coord fromMainString(String windString){
        if (windString == null){
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Coord>() {}.getType();
        return gson.fromJson(windString,type);
    }
}
