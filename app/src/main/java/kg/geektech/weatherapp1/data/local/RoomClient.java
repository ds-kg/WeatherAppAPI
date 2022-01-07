package kg.geektech.weatherapp1.data.local;

import android.content.Context;

import androidx.room.Room;

public class RoomClient {
    public AppDataBase appDataBase(Context context){
        return Room.databaseBuilder(context,AppDataBase.class, "dataBase").fallbackToDestructiveMigration().
                allowMainThreadQueries().build();
    }

}
