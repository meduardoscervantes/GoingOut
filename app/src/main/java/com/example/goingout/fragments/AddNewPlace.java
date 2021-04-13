package com.example.goingout.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.goingout.R;
import com.example.goingout.backend.Place;
import com.example.goingout.backend.ViewModel;

public class AddNewPlace extends Fragment {
    private ViewModel viewModel;
    private NavController navController;
    private CheckBox isVisited;
    private EditText name;
    public AddNewPlace() {
        // Required empty public constructor
    }

    public static AddNewPlace newInstance() {
        AddNewPlace fragment = new AddNewPlace();
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
        navController = Navigation.findNavController(view);
        view.findViewById(R.id.insert_place).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = view.findViewById(R.id.place_name);
                isVisited = view.findViewById(R.id.isVisited);
                if (!name.getText().toString().isEmpty()){
                    viewModel.insertPlace(new Place(name.getText().toString(),
                            isVisited.isChecked()));
                    Toast.makeText(view.getContext(),
                            name.getText().toString() + " Added", Toast.LENGTH_SHORT).show();
                    navController.navigate(AddNewPlaceDirections
                            .actionAddNewPlaceToHomeScreenFragment());
                }else{
                    Toast.makeText(view.getContext(),
                            "What's the name?!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_new_place, container, false);
    }
}