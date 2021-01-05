package com.binwin.driverlogs.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;

import com.binwin.driverlogs.R;

public class HomeFragment extends Fragment {

    View mView;
    private Button mCarButton, m5tTruckButton, m10tTruckButton, mTipperButton, mArticulatedButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_home, container, false);
        mCarButton = mView.findViewById(R.id.btn_car);
        //car button click event handler
        mCarButton.setOnClickListener(v -> {
            CarFragment carFragment = new CarFragment();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, carFragment, "carFragment").addToBackStack(null).commit();
        });
        m5tTruckButton = mView.findViewById(R.id.btn_5t_truck);
        //five tent button click event handler
        m5tTruckButton.setOnClickListener(v -> {
            FivetTruckFragment fivetFragment = new FivetTruckFragment();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fivetFragment, "fivetFragment").addToBackStack(null).commit();
        });
        m10tTruckButton = mView.findViewById(R.id.btn_10t_truck);
        //ten tent button click event handler
        m10tTruckButton.setOnClickListener(v -> {
            TentTruckFragment tentFragment = new TentTruckFragment();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, tentFragment, "tentFragment").addToBackStack(null).commit();
        });
        mTipperButton = mView.findViewById(R.id.btn_tipper);
        //tipper button click event handler
        mTipperButton.setOnClickListener(v -> {
            TipperFragment tipperFragment = new TipperFragment();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, tipperFragment, "tipperFragment").addToBackStack(null).commit();
        });
        mArticulatedButton = mView.findViewById(R.id.btn_articulated);
        //articulated button click event handler
        mArticulatedButton.setOnClickListener(v -> {
            ArticulatedFragment articulatedFragment = new ArticulatedFragment();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, articulatedFragment, "articulatedFragment").addToBackStack(null).commit();
        });

        //on back pressed event handler
        getActivity().getOnBackPressedDispatcher().addCallback(new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                getActivity().finish(); //exit the application
            }
        });

        return mView;
    }
}