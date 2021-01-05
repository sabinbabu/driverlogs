package com.binwin.driverlogs.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.binwin.driverlogs.R;

import static com.binwin.driverlogs.AppTexts.TIPPER;

public class TipperFragment extends BaseFragment {
    String sourceFragment; //get current fragment source
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);

        mTitleText.setText(getString(R.string.tipper)); //sets title text
        sourceFragment = TIPPER; //sets the current fragment

        saveLogEntryAction(sourceFragment); //save the log entry to local storage

        showLogEntryAction(this,sourceFragment); //display specefic log entry details

        //previous button click action handler
        previousButton.setOnClickListener(v -> {
            if (mTitleText.getText() == getString(R.string.tipper)){
                //navigates to Tent Truck
                TentTruckFragment tentFragment = new TentTruckFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,tentFragment,"tentFragment").remove(TipperFragment.this).addToBackStack(null).commit();
            }
        });

        //next button click action handler
        nextButton.setOnClickListener(v -> {
            if (mTitleText.getText() == getString(R.string.tipper)){
                //navigates to Articulated
                ArticulatedFragment articulatedFragment = new ArticulatedFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,articulatedFragment,"articulatedFragment").remove(TipperFragment.this).addToBackStack(null).commit();
            }
        });

        homeButtonAction(this); //home button click action handler
        onBackPressedAction(); //on back pressed event handler
        return mView;
    }
}