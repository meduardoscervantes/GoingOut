package com.example.goingout.backend;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@androidx.room.Dao
public interface Dao{

    @Insert
    void insertPlace(Place place);
    @Update
    void updatePlace(Place place);
    @Delete
    void deletePlace(Place place);
    @Query("SELECT * FROM Place WHERE visited = 1 ORDER BY name")
    LiveData<List<Place>> getVisitedPlaces();
    @Query("SELECT * FROM Place WHERE visited = 0 ORDER BY name")
    LiveData<List<Place>> getUnvisitedPlaces();
    @Query("SELECT * FROM Place ORDER BY name")
    LiveData<List<Place>> getAllPlaces();
}
