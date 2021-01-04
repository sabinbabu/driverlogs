package com.binwin.driverlogs.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.binwin.driverlogs.R;

import static com.binwin.driverlogs.AppTexts.TENT_TRUCK;

public class TentTruckFragment extends BaseFragment {
    String sourceFragment;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);

        mTitleText.setText(getString(R.string._10t_truck));
        sourceFragment = TENT_TRUCK;

        saveLogEntryAction(sourceFragment);

        showLogEntryAction(this,sourceFragment);

        previousButton.setOnClickListener(v -> {
            if (mTitleText.getText() == getString(R.string._10t_truck)){
                FivetTruckFragment fivetFragment = new FivetTruckFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fivetFragment,"fivetFragment").remove(TentTruckFragment.this).addToBackStack(null).commit();
            }
        });

        nextButton.setOnClickListener(v -> {
            if (mTitleText.getText() == getString(R.string._10t_truck)){
                TipperFragment tipperFragment = new TipperFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,tipperFragment,"tipperFragment").remove(TentTruckFragment.this).addToBackStack(null).commit();
            }
        });

        homeButtonAction(this);
        onBackPressedAction();
        return mView;
    }
}