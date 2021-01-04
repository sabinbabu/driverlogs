package com.binwin.driverlogs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;

import com.binwin.driverlogs.Fragments.FivetTruckFragment;
import com.binwin.driverlogs.Fragments.HomeFragment;
import com.binwin.driverlogs.Fragments.LogEntryDetailFragment;
import com.binwin.driverlogs.database.DriverLogsLab;

import static com.binwin.driverlogs.AppTexts.CAR;
import static com.binwin.driverlogs.AppTexts.FIVET_TRUCK;
import static com.binwin.driverlogs.AppTexts.HOME_FRAGMENT;
import static com.binwin.driverlogs.AppTexts.LOG_ENTRY_FRAGMENT;


public class CarFragmentCOpy extends Fragment {
    View mView;
    TextView mTitleText, startTimeTextView, firstBreakTextView, secondBreakTextView, endTimeTextView;
    Button previousButton, nextButton, homeButton,
            startTimeButton, firstBreakButton, secondBreakButton, endTimeButton,
            saveLogEntry,showLogEntry;
    EditText driverNameEditText, regoEditText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_section_view, container, false);
        mTitleText = (TextView) mView.findViewById(R.id.title_textView);
        mTitleText.setText(getString(R.string.car));

        driverNameEditText = (EditText) mView.findViewById(R.id.driver_editText);
        regoEditText = (EditText) mView.findViewById(R.id.rego_editText);

        saveLogEntry = (Button) mView.findViewById(R.id.btn_save_log_entry);
        showLogEntry = (Button) mView.findViewById(R.id.btn_show_log_entry);

        startTimeTextView = (TextView) mView.findViewById(R.id.start_time_textView);
        firstBreakTextView = (TextView) mView.findViewById(R.id.first_break_textView);
        secondBreakTextView = (TextView) mView.findViewById(R.id.second_break_textView);
        endTimeTextView = (TextView) mView.findViewById(R.id.end_time_textView);

        startTimeButton = (Button) mView.findViewById(R.id.btn_start_time);
        firstBreakButton = (Button) mView.findViewById(R.id.btn_first_break);
        secondBreakButton = (Button) mView.findViewById(R.id.btn_second_break);
        endTimeButton = (Button) mView.findViewById(R.id.btn_end_time);

        DateUtil getDate = new DateUtil();

        startTimeButton.setOnClickListener(v -> {
                    startTimeTextView.setText("Start: "+getDate.getCurrentDate());
                    startTimeTextView.setVisibility(View.VISIBLE);
                    startTimeButton.setVisibility(View.INVISIBLE);
                    firstBreakButton.setVisibility(View.VISIBLE);
                });

        firstBreakButton.setOnClickListener(v -> {
                    firstBreakTextView.setText("1st Break: "+getDate.getCurrentDate());
                    firstBreakTextView.setVisibility(View.VISIBLE);
                    firstBreakButton.setVisibility(View.INVISIBLE);
                    secondBreakButton.setVisibility(View.VISIBLE);
                });

        secondBreakButton.setOnClickListener(v -> {
                    secondBreakTextView.setText("2nd Break: "+getDate.getCurrentDate());
                    secondBreakTextView.setVisibility(View.VISIBLE);
                    secondBreakButton.setVisibility(View.INVISIBLE);
                    endTimeButton.setVisibility(View.VISIBLE);
                });

        endTimeButton.setOnClickListener(v -> {
            endTimeTextView.setText("End: "+getDate.getCurrentDate());
            endTimeTextView.setVisibility(View.VISIBLE);
            endTimeButton.setVisibility(View.INVISIBLE);
        });


        saveLogEntry.setOnClickListener(v -> {

            DriverLogs driverLogs = new DriverLogs();
            driverLogs.setmVehicleType(CAR);
            driverLogs.setmDriverName(driverNameEditText.getText().toString().trim());
            driverLogs.setmRego(regoEditText.getText().toString().trim());
            driverLogs.setmStartTime(startTimeTextView.getText().toString());
            driverLogs.setmFirstBreak(firstBreakTextView.getText().toString());
            driverLogs.setmSecondBreak(secondBreakTextView.getText().toString());
            driverLogs.setmEndTime(endTimeTextView.getText().toString());

            DriverLogsLab driverLogsLab = new DriverLogsLab(getActivity());
            driverLogsLab.addDriverLogs(driverLogs);

            if (driverNameEditText.getText().toString().trim().length() ==0){
                Toast toast = Toast.makeText(getContext(),"Entry not saved as not all data entered. Complete all entries and try again.",Toast.LENGTH_SHORT);
                toast.show();
            }
            Toast toast = Toast.makeText(getContext(),"Entry Saved",Toast.LENGTH_SHORT);
            toast.show();
            driverNameEditText.getText().clear();
            regoEditText.getText().clear();
            endTimeTextView.setVisibility(View.INVISIBLE);
            secondBreakTextView.setVisibility(View.INVISIBLE);
            firstBreakTextView.setVisibility(View.INVISIBLE);
            startTimeTextView.setVisibility(View.INVISIBLE);
            startTimeButton.setVisibility(View.VISIBLE);
        });

        showLogEntry.setOnClickListener(v->{
            LogEntryDetailFragment logEntryDetailFragment = new LogEntryDetailFragment();
            Bundle argument = new Bundle();
            argument.putString("sourceFragment",CAR);
            logEntryDetailFragment.setArguments(argument);
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, logEntryDetailFragment, LOG_ENTRY_FRAGMENT).remove(CarFragmentCOpy.this).addToBackStack(null).commit();
        });


        previousButton = (Button) mView.findViewById(R.id.btn_previous);
        previousButton.setOnClickListener(v -> {
            if (mTitleText.getText() == getString(R.string.car)) {
                HomeFragment homeFragment = new HomeFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, homeFragment, HOME_FRAGMENT).remove(CarFragmentCOpy.this).addToBackStack(null).commit();
            }
        });
        nextButton = (Button) mView.findViewById(R.id.btn_next);
        nextButton.setOnClickListener(v -> {
            if (mTitleText.getText() == getString(R.string.car)) {
                FivetTruckFragment fivetFragment = new FivetTruckFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fivetFragment, FIVET_TRUCK).remove(CarFragmentCOpy.this).addToBackStack(null).commit();
            }
        });

        homeButton = (Button) mView.findViewById(R.id.btn_home);
        homeButton.setOnClickListener(v -> {
            HomeFragment homeFragment = new HomeFragment();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, homeFragment, HOME_FRAGMENT).remove(CarFragmentCOpy.this).addToBackStack(null).commit();
        });

        getActivity().getOnBackPressedDispatcher().addCallback(new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                HomeFragment homeFragment = new HomeFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, homeFragment, HOME_FRAGMENT).remove(CarFragmentCOpy.this).addToBackStack(null).commit();
            }
        });

        return mView;
    }
}
