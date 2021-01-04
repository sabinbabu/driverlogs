package com.binwin.driverlogs.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.binwin.driverlogs.R;

import static com.binwin.driverlogs.AppTexts.CAR;
import static com.binwin.driverlogs.AppTexts.FIVET_TRUCK;
import static com.binwin.driverlogs.AppTexts.HOME_FRAGMENT;


public class CarFragment extends BaseFragment {
    String sourceFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        mTitleText.setText(getString(R.string.car));
        sourceFragment = CAR;

        saveLogEntryAction(sourceFragment);

        showLogEntryAction(this, sourceFragment);

        previousButton.setOnClickListener(v -> {
            if (mTitleText.getText() == getString(R.string.car)) {
                HomeFragment homeFragment = new HomeFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, homeFragment, HOME_FRAGMENT).remove(CarFragment.this).addToBackStack(null).commit();
            }
        });

        nextButton.setOnClickListener(v -> {
            if (mTitleText.getText() == getString(R.string.car)) {
                FivetTruckFragment fivetFragment = new FivetTruckFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fivetFragment, FIVET_TRUCK).remove(CarFragment.this).addToBackStack(null).commit();
            }
        });

        homeButtonAction(this);
        onBackPressedAction();

        return mView;
    }

}
