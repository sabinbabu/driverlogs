package com.binwin.driverlogs.Fragments;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.binwin.driverlogs.R;

public class HomeFragment extends Fragment {

    private Button mCarButton,m5tTruckButton,m10tTruckButton,mTipperButton,mArticulatedButton;
    View mView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_home, container, false);
        mCarButton = (Button) mView.findViewById(R.id.btn_car);
        mCarButton.setOnClickListener(v -> {
            CarFragment carFragment = new CarFragment();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,carFragment,"carFragment").addToBackStack(null).commit();
        });
        m5tTruckButton = (Button) mView.findViewById(R.id.btn_5t_truck);
        m5tTruckButton.setOnClickListener(v -> {
            FivetTruckFragment fivetFragment = new FivetTruckFragment();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fivetFragment,"fivetFragment").addToBackStack(null).commit();
        });
        m10tTruckButton = (Button) mView.findViewById(R.id.btn_10t_truck);
        m10tTruckButton.setOnClickListener(v -> {
            TentTruckFragment tentFragment = new TentTruckFragment();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,tentFragment,"tentFragment").addToBackStack(null).commit();
        });
        mTipperButton = (Button) mView.findViewById(R.id.btn_tipper);
        mTipperButton.setOnClickListener(v -> {
            TipperFragment tipperFragment = new TipperFragment();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,tipperFragment,"tipperFragment").addToBackStack(null).commit();
        });
        mArticulatedButton = (Button) mView.findViewById(R.id.btn_articulated);
        mArticulatedButton.setOnClickListener(v -> {
            ArticulatedFragment articulatedFragment = new ArticulatedFragment();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,articulatedFragment,"articulatedFragment").addToBackStack(null).commit();
        });

        getActivity().getOnBackPressedDispatcher().addCallback(new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
               getActivity().finish(); }
        });

        return mView;
    }
}