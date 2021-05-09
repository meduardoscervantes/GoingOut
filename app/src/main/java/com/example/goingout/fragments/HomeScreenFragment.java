package com.example.goingout.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.goingout.R;
import com.example.goingout.backend.Place;
import com.example.goingout.backend.ViewModel;

import java.util.List;
import java.util.Random;

public class HomeScreenFragment extends Fragment {
    private NavController navController;
    private ViewModel vm;
    public HomeScreenFragment() {
        // Required empty public constructor
    }
    public static HomeScreenFragment newInstance() {
        HomeScreenFragment fragment = new HomeScreenFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        vm = new ViewModelProvider(this).get(ViewModel.class);
        view.findViewById(R.id.choose_person).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(HomeScreenFragmentDirections
                        .actionHomeScreenFragmentToPersonFragment());
            }
        });
        view.findViewById(R.id.all_places).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(HomeScreenFragmentDirections
                    .actionHomeScreenFragmentToAllPlacesFragment());
            }
        });
        view.findViewById(R.id.to_visit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(HomeScreenFragmentDirections
                        .actionHomeScreenFragmentToPlacesToVisitFragment());
            }
        });
        view.findViewById(R.id.visited).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(HomeScreenFragmentDirections
                        .actionHomeScreenFragmentToVisitedPlacesFragment());
            }
        });
        view.findViewById(R.id.add_place).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(HomeScreenFragmentDirections
                        .actionHomeScreenFragmentToAddNewPlace());
            }
        });
        view.findViewById(R.id.random).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vm.getAllPlaces().observe(getViewLifecycleOwner(), new Observer<List<Place>>() {
                    @Override
                    public void onChanged(List<Place> places) {
                        if(places.size() > 0 ){
                            int rand = new Random().nextInt(places.size());
                            Toast.makeText(getContext(),
                                    places.get(rand).getName() + " it is!" ,
                                    Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getContext(), "Nowhere to go!",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_screen, container, false);
    }
}