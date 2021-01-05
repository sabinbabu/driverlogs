package com.binwin.driverlogs.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.binwin.driverlogs.R;

import static com.binwin.driverlogs.AppTexts.TENT_TRUCK;

public class TentTruckFragment extends BaseFragment {
    String sourceFragment; //get current fragment source
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);

        mTitleText.setText(getString(R.string._10t_truck)); //sets title text
        sourceFragment = TENT_TRUCK; //sets the current fragment

        saveLogEntryAction(sourceFragment); //save the log entry to local storage

        showLogEntryAction(this,sourceFragment); //display specific log entry details

        //previous button click action handler
        previousButton.setOnClickListener(v -> {
            if (mTitleText.getText() == getString(R.string._10t_truck)){
                //navigates to FiveT Truck
                FivetTruckFragment fivetFragment = new FivetTruckFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fivetFragment,"fivetFragment").remove(TentTruckFragment.this).addToBackStack(null).commit();
            }
        });

        //next button click action handler
        nextButton.setOnClickListener(v -> {
            if (mTitleText.getText() == getString(R.string._10t_truck)){
                //navigates to Tipper
                TipperFragment tipperFragment = new TipperFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,tipperFragment,"tipperFragment").remove(TentTruckFragment.this).addToBackStack(null).commit();
            }
        });

        homeButtonAction(this); //home button click action handler
        onBackPressedAction(); //on back pressed event handler
        return mView;
    }
}