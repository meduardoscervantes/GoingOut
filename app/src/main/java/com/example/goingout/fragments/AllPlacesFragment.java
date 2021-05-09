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

public class AllPlacesFragment extends Fragment {
    private ViewModel viewModel;
    private RecyclerView recycler;
    private PlacesAdapter adapter;
    public AllPlacesFragment() {
        // Required empty public constructor
    }

    public static AllPlacesFragment newInstance() {
        AllPlacesFragment fragment = new AllPlacesFragment();
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
        recycler = view.findViewById(R.id.all_places_recycler);
        adapter = new PlacesAdapter();
        recycler.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recycler.setAdapter(adapter);
        viewModel.getAllPlaces().observe(getViewLifecycleOwner(), new Observer<List<Place>>() {
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
                Toast.makeText(getContext(),
                        adapter.getPlaceAt(viewHolder.getAdapterPosition()).getName()
                                + " deleted"
                        , Toast.LENGTH_SHORT).show();
                viewModel.deletePlace(adapter.getPlaceAt(viewHolder.getAdapterPosition()));
            }
        }).attachToRecyclerView(recycler);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_all_places, container, false);
    }
}