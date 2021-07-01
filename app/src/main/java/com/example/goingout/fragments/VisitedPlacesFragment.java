package com.example.goingout.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.goingout.R;
import com.example.goingout.adapters.PlacesAdapter;
import com.example.goingout.backend.Place;
import com.example.goingout.backend.ViewModel;

import java.util.List;


public class VisitedPlacesFragment extends Fragment {
    private ViewModel viewModel;
    private RecyclerView recycler;
    private PlacesAdapter adapter;
    public VisitedPlacesFragment() {
        // Required empty public constructor
    }

    public static VisitedPlacesFragment newInstance() {
        VisitedPlacesFragment fragment = new VisitedPlacesFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(ViewModel.class);
        recycler = view.findViewById(R.id.visited_recycler);
        adapter = new PlacesAdapter();
        recycler.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recycler.setAdapter(adapter);
        viewModel.getAllVisitedPlaces().observe(getViewLifecycleOwner()
                , new Observer<List<Place>>() {
            @Override
            public void onChanged(List<Place> places) {
                adapter.setPlaces(places);
                adapter.notifyDataSetChanged();
            }
        });
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView,
                                  @NonNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                if(direction == ItemTouchHelper.LEFT){
                    Toast.makeText(getContext(),
                            adapter.getPlaceAt(viewHolder.getAdapterPosition()).getName()
                                    + " deleted."
                            , Toast.LENGTH_SHORT).show();
                    viewModel.deletePlace(adapter.getPlaceAt(viewHolder.getAdapterPosition()));
                }else {
                    Place updatePlace = adapter.getPlaceAt(viewHolder.getAdapterPosition());
                    updatePlace.setVisited(!updatePlace.getVisited());
                    if(updatePlace.getVisited()){
                        Toast.makeText(getContext(),
                                adapter.getPlaceAt(viewHolder.getAdapterPosition()).getName()
                                        + " has been visited."
                                , Toast.LENGTH_SHORT).show();
                    } else{
                        Toast.makeText(getContext(),
                                adapter.getPlaceAt(viewHolder.getAdapterPosition()).getName()
                                        + " has yet to be visited."
                                , Toast.LENGTH_SHORT).show();
                    }
                    viewModel.updatePlace(updatePlace);
                }
            }
        }).attachToRecyclerView(recycler);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_visited_places, container, false);
    }
}