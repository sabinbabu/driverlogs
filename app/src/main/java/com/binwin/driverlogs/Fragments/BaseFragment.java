package com.binwin.driverlogs.Fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
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

import com.binwin.driverlogs.Util;
import com.binwin.driverlogs.DriverLogs;
import com.binwin.driverlogs.database.DriverLogsLab;
import com.binwin.driverlogs.R;

import static com.binwin.driverlogs.AppTexts.HOME_FRAGMENT;
import static com.binwin.driverlogs.AppTexts.LOG_ENTRY_FRAGMENT;

public class BaseFragment extends Fragment {
    View mView;
    TextView mTitleText, startTimeTextView, firstBreakTextView, secondBreakTextView, endTimeTextView;
    Button previousButton, nextButton, homeButton,
            startTimeButton, firstBreakButton, secondBreakButton, endTimeButton,
            saveLogEntry, showLogEntry;
    EditText driverNameEditText, regoEditText;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_section_view, container, false);
        mTitleText = mView.findViewById(R.id.title_textView);
        previousButton = mView.findViewById(R.id.btn_previous);
        nextButton = mView.findViewById(R.id.btn_next);
        homeButton = mView.findViewById(R.id.btn_home);

        driverNameEditText = mView.findViewById(R.id.driver_editText);
        regoEditText = mView.findViewById(R.id.rego_editText);

        saveLogEntry = mView.findViewById(R.id.btn_save_log_entry);
        showLogEntry = mView.findViewById(R.id.btn_show_log_entry);

        startTimeTextView = mView.findViewById(R.id.start_time_textView);
        firstBreakTextView = mView.findViewById(R.id.first_break_textView);
        secondBreakTextView = mView.findViewById(R.id.second_break_textView);
        endTimeTextView = mView.findViewById(R.id.end_time_textView);

        startTimeButton = mView.findViewById(R.id.btn_start_time);
        firstBreakButton = mView.findViewById(R.id.btn_first_break);
        secondBreakButton = mView.findViewById(R.id.btn_second_break);
        endTimeButton = mView.findViewById(R.id.btn_end_time);

        Util getDate = new Util();

        startTimeButton.setOnClickListener(v -> {
            startTimeTextView.setText(String.format("Start: %s", getDate.getCurrentDate()));
            startTimeTextView.setVisibility(View.VISIBLE);
            startTimeButton.setVisibility(View.INVISIBLE);
            firstBreakButton.setVisibility(View.VISIBLE);
        });

        firstBreakButton.setOnClickListener(v -> {
            firstBreakTextView.setText(String.format("1st Break: %s", getDate.getCurrentDate()));
            firstBreakTextView.setVisibility(View.VISIBLE);
            firstBreakButton.setVisibility(View.INVISIBLE);
            secondBreakButton.setVisibility(View.VISIBLE);
        });

        secondBreakButton.setOnClickListener(v -> {
            secondBreakTextView.setText(String.format("2nd Break: %s", getDate.getCurrentDate()));
            secondBreakTextView.setVisibility(View.VISIBLE);
            secondBreakButton.setVisibility(View.INVISIBLE);
            endTimeButton.setVisibility(View.VISIBLE);
        });

        endTimeButton.setOnClickListener(v -> {
            endTimeTextView.setText(String.format("End: %s", getDate.getCurrentDate()));
            endTimeTextView.setVisibility(View.VISIBLE);
            endTimeButton.setVisibility(View.INVISIBLE);
        });
        return mView;
    }

    public void saveLogEntryAction(String vehicleType) {
        saveLogEntry.setOnClickListener(v -> {

            if (driverNameEditText.getText().toString().trim().length() != 0 && regoEditText.getText().toString().trim().length() != 0 && endTimeTextView.isShown()) {
                DriverLogs driverLogs = new DriverLogs();
                driverLogs.setmVehicleType(vehicleType);
                driverLogs.setmDriverName(driverNameEditText.getText().toString().trim());
                driverLogs.setmRego(regoEditText.getText().toString().trim());
                driverLogs.setmStartTime(startTimeTextView.getText().toString());
                driverLogs.setmFirstBreak(firstBreakTextView.getText().toString());
                driverLogs.setmSecondBreak(secondBreakTextView.getText().toString());
                driverLogs.setmEndTime(endTimeTextView.getText().toString());

                DriverLogsLab.get(getActivity()).addDriverLogs(driverLogs);

                //using shared Preference to store unsaved logs
                SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                if (sharedPreferences.getString("unSavedValue",null)!=null){
                editor.putString("unSavedValue",sharedPreferences.getString("unSavedValue",null)+"_1");
                }else editor.putString("unSavedValue","1");
                editor.apply();

                Toast toast = Toast.makeText(getContext(), "Entry Saved", Toast.LENGTH_SHORT);
                toast.show();

                driverNameEditText.getText().clear();
                regoEditText.getText().clear();
                endTimeTextView.setVisibility(View.INVISIBLE);
                secondBreakTextView.setVisibility(View.INVISIBLE);
                firstBreakTextView.setVisibility(View.INVISIBLE);
                startTimeTextView.setVisibility(View.INVISIBLE);
                startTimeButton.setVisibility(View.VISIBLE);
            } else {
                Toast toast = Toast.makeText(getContext(), "Entry not saved as not all data entered. Complete all entries and try again.", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

    public void showLogEntryAction(Fragment currentFragment, String sourceFragment) {
        showLogEntry.setOnClickListener(v -> {
            LogEntryDetailFragment logEntryDetailFragment = new LogEntryDetailFragment();
            Bundle argument = new Bundle();
            argument.putString("sourceFragment", sourceFragment);
            logEntryDetailFragment.setArguments(argument);
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, logEntryDetailFragment, LOG_ENTRY_FRAGMENT).remove(currentFragment).addToBackStack(null).commit();
        });
    }

    public void homeButtonAction(Fragment currentFragment) {
        homeButton.setOnClickListener(v -> {
            HomeFragment homeFragment = new HomeFragment();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, homeFragment, HOME_FRAGMENT).remove(currentFragment).addToBackStack(null).commit();
        });
    }

    public void onBackPressedAction() {
        getActivity().getOnBackPressedDispatcher().addCallback(new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setPositiveButton("Yes", (dialog, id) -> {
                    getActivity().finish();
                });

                builder.setNegativeButton("No", (dialog, id) -> {
                    //removing the unsaved logs using shared Preferences
                    String[] tableLengthArray  = DriverLogsLab.get(getActivity()).getAllVehicleLog().toString().split(",");
                    Integer tableLength = tableLengthArray.length;

                    SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
                    String sha = sharedPreferences.getString("unSavedValue",null);
                    if (sha!=null) {
                        String[] unSavedValueArray = sha.split("_");
                        Integer unSavedValue = unSavedValueArray.length;
                        
                        int finalLength = tableLength - unSavedValue;

                        for (int i = tableLength;i>finalLength;i--){
                            DriverLogsLab.get(getActivity()).deleteSelectedLogs();
                        }

                    }

                    sharedPreferences.edit().remove("unSavedValue").apply();
                    getActivity().finish();
                });
                AlertDialog dialog = builder.create();
                dialog.setTitle("Save entries to DB first?");
                dialog.setIcon(R.mipmap.ic_launcher);
                dialog.show();
            }
        });
    }
}
