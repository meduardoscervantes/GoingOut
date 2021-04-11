package com.example.goingout.backend;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class Repository {
    private Dao dao;
    private LiveData<List<Place>> allPlaces;
    private LiveData<List<Place>> visitedPlaces;
    private LiveData<List<Place>> unVisitedPlaces;
    public Repository(Application application){
        DataBase dataBase = DataBase.getInstance(application);
        dao = dataBase.dao();
        allPlaces = dao.getAllPlaces();
        visitedPlaces = dao.getVisitedPlaces();
        unVisitedPlaces = dao.getUnvisitedPlaces();
    }
    public LiveData<List<Place>> getAllPlaces() {
        return allPlaces;
    }
    public LiveData<List<Place>> getUnVisitedPlaces() {
        return unVisitedPlaces;
    }
    public LiveData<List<Place>> getVisitedPlaces() {
        return visitedPlaces;
    }
    public void insertPlace(Place place){
        new InsertAsyncTask(dao).execute(place);
    }
    public void deletePlace(Place place){
        new DeleteAsyncTask(dao).execute(place);
    }
    public void updatePlace(Place place){
        new UpdateAsyncTask(dao).execute(place);
    }
    private static class InsertAsyncTask extends AsyncTask<Place,Void,Void>{
        private Dao dao;
        private InsertAsyncTask(Dao dao){this.dao = dao;}
        @Override
        protected Void doInBackground(Place... places) {
            dao.insertPlace(places[0]);
            return null;
        }
    }
    private static class DeleteAsyncTask extends AsyncTask<Place,Void,Void>{
        private Dao dao;
        private DeleteAsyncTask(Dao dao){this.dao = dao;}
        @Override
        protected Void doInBackground(Place... places) {
            dao.deletePlace(places[0]);
            return null;
        }
    }
    private static class UpdateAsyncTask extends AsyncTask<Place,Void,Void>{
        private Dao dao;
        private UpdateAsyncTask(Dao dao){this.dao = dao;}
        @Override
        protected Void doInBackground(Place... places) {
            dao.updatePlace(places[0]);
            return null;
        }
    }
}
