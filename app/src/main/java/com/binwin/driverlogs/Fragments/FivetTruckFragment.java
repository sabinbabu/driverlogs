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
    String sourceFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        mTitleText.setText(getString(R.string._5t_truck));
        sourceFragment = FIVET_TRUCK;

        saveLogEntryAction(sourceFragment);

        showLogEntryAction(this, sourceFragment);


        previousButton.setOnClickListener(v -> {
            if (mTitleText.getText() == getString(R.string._5t_truck)) {
                CarFragment carFragment = new CarFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, carFragment, CAR).remove(FivetTruckFragment.this).addToBackStack(null).commit();
            }
        });

        nextButton.setOnClickListener(v -> {
            if (mTitleText.getText() == getString(R.string._5t_truck)) {
                TentTruckFragment tentFragment = new TentTruckFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, tentFragment, TENT_TRUCK).remove(FivetTruckFragment.this).addToBackStack(null).commit();
            }
        });

        homeButtonAction(this);
        onBackPressedAction();

        return mView;
    }
}
