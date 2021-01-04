package com.binwin.driverlogs.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.binwin.driverlogs.R;

import static com.binwin.driverlogs.AppTexts.ARTICULATED;

public class ArticulatedFragment extends BaseFragment {
    String sourceFragment;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);

        mTitleText.setText(getString(R.string.articulated));
        sourceFragment = ARTICULATED;

        saveLogEntryAction(sourceFragment);

        showLogEntryAction(this,sourceFragment);

        previousButton.setOnClickListener(v -> {
            if (mTitleText.getText() == getString(R.string.articulated)){
                TipperFragment tipperFragment = new TipperFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,tipperFragment,"tipperFragment").remove(ArticulatedFragment.this).addToBackStack(null).commit();
            }
        });

        nextButton.setOnClickListener(v -> {
            if (mTitleText.getText() == getString(R.string.articulated)){
                HomeFragment homeFragment = new HomeFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,homeFragment,"homeFragment").remove(ArticulatedFragment.this).addToBackStack(null).commit();
            }
        });

        homeButtonAction(this);
        onBackPressedAction();
        return mView;
    }
}