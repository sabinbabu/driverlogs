package com.binwin.driverlogs.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.binwin.driverlogs.R;

import static com.binwin.driverlogs.AppTexts.TIPPER;

public class TipperFragment extends BaseFragment {
    String sourceFragment;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);

        mTitleText.setText(getString(R.string.tipper));
        sourceFragment = TIPPER;

        saveLogEntryAction(sourceFragment);

        showLogEntryAction(this,sourceFragment);

        previousButton.setOnClickListener(v -> {
            if (mTitleText.getText() == getString(R.string.tipper)){
                TentTruckFragment tentFragment = new TentTruckFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,tentFragment,"tentFragment").remove(TipperFragment.this).addToBackStack(null).commit();
            }
        });

        nextButton.setOnClickListener(v -> {
            if (mTitleText.getText() == getString(R.string.tipper)){
                ArticulatedFragment articulatedFragment = new ArticulatedFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,articulatedFragment,"articulatedFragment").remove(TipperFragment.this).addToBackStack(null).commit();
            }
        });

        homeButtonAction(this);
        onBackPressedAction();
        return mView;
    }
}