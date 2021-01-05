package com.binwin.driverlogs.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.binwin.driverlogs.R;

import static com.binwin.driverlogs.AppTexts.ARTICULATED;

public class ArticulatedFragment extends BaseFragment {
    String sourceFragment; //get current fragment source
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);

        mTitleText.setText(getString(R.string.articulated)); //sets title text
        sourceFragment = ARTICULATED; //sets the current fragment

        saveLogEntryAction(sourceFragment); //save the log entry to local storage

        showLogEntryAction(this,sourceFragment); //display specific log entry details

        //previous button click action handler
        previousButton.setOnClickListener(v -> {
            if (mTitleText.getText() == getString(R.string.articulated)){
                //navigates to Tipper
                TipperFragment tipperFragment = new TipperFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,tipperFragment,"tipperFragment").remove(ArticulatedFragment.this).addToBackStack(null).commit();
            }
        });

        //next button click action handler
        nextButton.setOnClickListener(v -> {
            if (mTitleText.getText() == getString(R.string.articulated)){
                //navigates to Articulated
                HomeFragment homeFragment = new HomeFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,homeFragment,"homeFragment").remove(ArticulatedFragment.this).addToBackStack(null).commit();
            }
        });

        homeButtonAction(this); //home button click action handler
        onBackPressedAction(); //on back pressed event handler
        return mView;
    }
}