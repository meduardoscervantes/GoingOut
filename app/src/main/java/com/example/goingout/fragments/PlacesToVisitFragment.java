package com.example.goingout.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.goingout.R;
import com.example.goingout.adapters.PlacesAdapter;
import com.example.goingout.backend.Place;
import com.example.goingout.backend.ViewModel;

import java.util.List;

public class PlacesToVisitFragment extends Fragment {
    private ViewModel viewModel;
    private RecyclerView recycler;
    private PlacesAdapter adapter;
    public PlacesToVisitFragment() {
        // Required empty public constructor
    }

    public static PlacesToVisitFragment newInstance() {
        PlacesToVisitFragment fragment = new PlacesToVisitFragment();
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
        recycler = view.findViewById(R.id.to_visit_recycler);
        adapter = new PlacesAdapter();
        recycler.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recycler.setAdapter(adapter);
        viewModel.getAllUnVisitedPlaces().observe(getViewLifecycleOwner()
                , new Observer<List<Place>>() {
            @Override
            public void onChanged(List<Place> places) {
                adapter.setPlaces(places);
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_places_to_visit, container, false);
    }
}