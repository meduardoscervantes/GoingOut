package com.example.goingout.backend;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Calendar;
import java.util.Date;

@Entity
public class Place {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private boolean visited;
    public Place(String name, boolean visited){
        this.name = name;
        this.visited = visited;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public boolean getVisited(){
        return visited;
    }
    public void setVisited(boolean visited){
        this.visited = visited;
    }
}
