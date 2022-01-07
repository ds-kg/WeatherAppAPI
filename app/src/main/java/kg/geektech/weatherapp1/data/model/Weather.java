
package kg.geektech.weatherapp1.data.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import kg.geektech.weatherapp1.data.local.converter.CloudConvertor;
import kg.geektech.weatherapp1.data.local.converter.CoordConverter;
import kg.geektech.weatherapp1.data.local.converter.MainConvertor;
import kg.geektech.weatherapp1.data.local.converter.SysConvertor;
import kg.geektech.weatherapp1.data.local.converter.Weather1Convertor;
import kg.geektech.weatherapp1.data.local.converter.WindConvertor;


@Entity
public class Weather {

    @SerializedName("coord")
    @Expose
    @TypeConverters({CoordConverter.class})
    private Coord coord;
    @SerializedName("weather")
    @Expose
    @TypeConverters({Weather1Convertor.class})
    private List<Weather__1> weather = null;
    @SerializedName("base")
    @Expose
    private String base;
    @SerializedName("main")
    @Expose
    @TypeConverters({MainConvertor.class})
    private Main main;
    @SerializedName("visibility")
    @Expose
    private Integer visibility;
    @SerializedName("wind")
    @Expose
    @TypeConverters({WindConvertor.class})
    private Wind wind;
    @SerializedName("clouds")
    @Expose
    @TypeConverters({CloudConvertor.class})
    private Clouds clouds;
    @SerializedName("dt")
    @Expose
    private Integer dt;
    @SerializedName("sys")
    @Expose
    @TypeConverters({SysConvertor.class})
    private Sys sys;
    @SerializedName("timezone")
    @Expose
    private Integer timezone;
    @SerializedName("id")
    @Expose
    @PrimaryKey
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("cod")
    @Expose
    private Integer cod;

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public List<Weather__1> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather__1> weather) {
        this.weather = weather;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Integer getVisibility() {
        return visibility;
    }

    public void setVisibility(Integer visibility) {
        this.visibility = visibility;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public Integer getDt() {
        return dt;
    }

    public void setDt(Integer dt) {
        this.dt = dt;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public Integer getTimezone() {
        return timezone;
    }

    public void setTimezone(Integer timezone) {
        this.timezone = timezone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

}
