package com.example.goingout.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.goingout.R;
import com.example.goingout.backend.Place;

import java.util.ArrayList;
import java.util.List;

public class PlacesAdapter extends RecyclerView.Adapter<PlacesAdapter.Holder> {
    private List<Place> places = new ArrayList<>();
    public void setPlaces(List<Place> places){
        this.places = places;
    }
    public Place getPlaceAt(int pos){
        return places.get(pos);
    }
    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.places_adapter, parent, false);
        return new Holder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Place place = places.get(position);
        holder.name.setText(place.getName());
    }

    @Override
    public int getItemCount() {
        return places.size();
    }

    static class Holder extends RecyclerView.ViewHolder{
        private TextView name;
        public Holder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.place_name);
        }
    }
}
