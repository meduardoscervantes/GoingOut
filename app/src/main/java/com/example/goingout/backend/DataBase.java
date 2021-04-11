package com.example.goingout.backend;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = Place.class, version = 1)
public abstract class DataBase extends RoomDatabase {
    private static DataBase instance;
    public abstract Dao dao();

    public static synchronized DataBase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    DataBase.class, "database")
                    .fallbackToDestructiveMigration()
                    .addCallback(callback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback callback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void,Void,Void>{
        private Dao dao;
        private PopulateDbAsyncTask(DataBase dataBase){
            this.dao = dataBase.dao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            dao.insertPlace(new Place("1919", true));
            return null;
        }
    }
}
