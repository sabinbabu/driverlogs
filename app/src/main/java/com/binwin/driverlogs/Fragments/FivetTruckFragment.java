package com.binwin.driverlogs.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.binwin.driverlogs.R;

import static com.binwin.driverlogs.AppTexts.CAR;
import static com.binwin.driverlogs.AppTexts.FIVET_TRUCK;
import static com.binwin.driverlogs.AppTexts.TENT_TRUCK;

public class FivetTruckFragment extends BaseFragment {
    String sourceFragment; //get current fragment source

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        mTitleText.setText(getString(R.string._5t_truck)); //sets title text
        sourceFragment = FIVET_TRUCK; //sets the current fragment

        saveLogEntryAction(sourceFragment); //save the log entry to local storage

        showLogEntryAction(this, sourceFragment); //display specific log entry details

        //previous button click action handler
        previousButton.setOnClickListener(v -> {
            if (mTitleText.getText() == getString(R.string._5t_truck)) {
                //navigates to Car
                CarFragment carFragment = new CarFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, carFragment, CAR).remove(FivetTruckFragment.this).addToBackStack(null).commit();
            }
        });

        //next button click action handler
        nextButton.setOnClickListener(v -> {
            if (mTitleText.getText() == getString(R.string._5t_truck)) {
                //navigates to tent Truck
                TentTruckFragment tentFragment = new TentTruckFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, tentFragment, TENT_TRUCK).remove(FivetTruckFragment.this).addToBackStack(null).commit();
            }
        });

        homeButtonAction(this); //home button click action handler
        onBackPressedAction(); //on back pressed event handler
        return mView;
    }
}
