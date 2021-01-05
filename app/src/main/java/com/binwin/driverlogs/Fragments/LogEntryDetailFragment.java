package com.binwin.driverlogs.Fragments;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.binwin.driverlogs.DriverLogs;
import com.binwin.driverlogs.database.DriverLogsLab;
import com.binwin.driverlogs.LogEntryDetailAdapter;
import com.binwin.driverlogs.R;

import java.util.ArrayList;

import static com.binwin.driverlogs.AppTexts.ARTICULATED;
import static com.binwin.driverlogs.AppTexts.CAR;
import static com.binwin.driverlogs.AppTexts.FIVET_TRUCK;
import static com.binwin.driverlogs.AppTexts.HOME_FRAGMENT;
import static com.binwin.driverlogs.AppTexts.TENT_TRUCK;
import static com.binwin.driverlogs.AppTexts.TIPPER;


public class LogEntryDetailFragment extends Fragment {
    View mView;
    Button returnButton;
    RecyclerView mRecyclerView;
    ArrayList<DriverLogs> mDriverLogs = new ArrayList<>();
    LogEntryDetailAdapter logEntryDetailAdapter;
    String returnText = HOME_FRAGMENT;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_log_entry_detail, container, false);

        returnButton = (Button) mView.findViewById(R.id.return_button);
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.recycler_view);

        if (getArguments().getString("sourceFragment")!=null){
            this.returnText = getArguments().getString("sourceFragment");
        }

        switch (returnText) {
            case CAR:
                returnButton.setText(R.string.return_to_car);
                break;
            case FIVET_TRUCK:
                returnButton.setText(R.string.return_to_5T_truck);
                break;
            case TENT_TRUCK:
                returnButton.setText(R.string.return_to_tent_truck);
                break;
            case TIPPER:
                returnButton.setText(R.string.return_to_tipper);
                break;
            case ARTICULATED:
                returnButton.setText(R.string.return_to_articulated);
                break;
        }

        returnButton.setOnClickListener(v -> {
           returnCase(returnText);
        });


        DriverLogsLab driverLogsLab = new DriverLogsLab(getActivity());

        switch (returnText) {
            case CAR:
                this.mDriverLogs = driverLogsLab.getVehicleLog(CAR);
                break;
            case FIVET_TRUCK:
                this.mDriverLogs = driverLogsLab.getVehicleLog(FIVET_TRUCK);
                break;
            case TENT_TRUCK:
                this.mDriverLogs = driverLogsLab.getVehicleLog(TENT_TRUCK);
                break;
            case TIPPER:
                this.mDriverLogs = driverLogsLab.getVehicleLog(TIPPER);
                break;
            case ARTICULATED:
                this.mDriverLogs = driverLogsLab.getVehicleLog(ARTICULATED);
                break;
        }

        setupAdapter();

        getActivity().getOnBackPressedDispatcher().addCallback(new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
               returnCase(returnText); }
        });

        return mView;
    }

    private void returnCase(String returnText) {
        switch (returnText) {
            case CAR:
                CarFragment carFragment = new CarFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,carFragment,"carFragment").remove(LogEntryDetailFragment.this).addToBackStack(null).commit();
                break;
            case FIVET_TRUCK:
                FivetTruckFragment fivetFragment = new FivetTruckFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fivetFragment,"fivetFragment").remove(LogEntryDetailFragment.this).addToBackStack(null).commit();
                break;
            case TENT_TRUCK:
                TentTruckFragment tentFragment = new TentTruckFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,tentFragment,"tentFragment").remove(LogEntryDetailFragment.this).addToBackStack(null).commit();
                break;
            case TIPPER:
                TipperFragment tipperFragment = new TipperFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,tipperFragment,"tipperFragment").remove(LogEntryDetailFragment.this).addToBackStack(null).commit();
                break;
            case ARTICULATED:
                ArticulatedFragment articulatedFragment = new ArticulatedFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,articulatedFragment,"articulatedFragment").remove(LogEntryDetailFragment.this).addToBackStack(null).commit();
                break;
        }
    }

    void setupAdapter(){
        if (getActivity() == null || mRecyclerView == null) return;

        if(mDriverLogs != null) {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            logEntryDetailAdapter = new LogEntryDetailAdapter(getActivity(),mDriverLogs);
            mRecyclerView.setAdapter(logEntryDetailAdapter);
        } else {
            mRecyclerView.setAdapter(null);
        }
    }

}