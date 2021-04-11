package com.example.goingout.backend;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ViewModel extends AndroidViewModel {
    private Repository repository;
    private LiveData<List<Place>> allPlaces;
    private LiveData<List<Place>> allVisitedPlaces;
    private LiveData<List<Place>> allUnVisitedPlaces;
    public ViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
        allPlaces = repository.getAllPlaces();
        allVisitedPlaces = repository.getVisitedPlaces();
        allUnVisitedPlaces = repository.getUnVisitedPlaces();
    }
    public void insertPlace(Place place){
        repository.insertPlace(place);
    }
    public void deletePlace(Place place){
        repository.deletePlace(place);
    }
    public void updatePlace(Place place){
        repository.updatePlace(place);
    }
    public LiveData<List<Place>> getAllPlaces() {
        return allPlaces;
    }
    public LiveData<List<Place>> getAllUnVisitedPlaces() {
        return allUnVisitedPlaces;
    }
    public LiveData<List<Place>> getAllVisitedPlaces() {
        return allVisitedPlaces;
    }
}
