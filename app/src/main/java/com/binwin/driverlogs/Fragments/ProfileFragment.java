package com.binwin.driverlogs.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.binwin.driverlogs.R;

import java.util.Objects;

import static com.binwin.driverlogs.AppTexts.HOME_FRAGMENT;

public class ProfileFragment extends Fragment {

    EditText userName, password, repeatPassword;
    Button saveProfile, cancelProfile;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mView;
        mView = inflater.inflate(R.layout.fragment_profile, container, false);
        userName = mView.findViewById(R.id.username_edit_text);
        password = mView.findViewById(R.id.user_password);
        repeatPassword = mView.findViewById(R.id.repeat_password);
        saveProfile = mView.findViewById(R.id.save_profile_btn);
        cancelProfile = mView.findViewById(R.id.cancel_profile_button);

        repeatPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {
                // This space intentionally left blank
            }

            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                if (!password.getText().toString().trim().equals(repeatPassword.getText().toString().trim())){
                    repeatPassword.setError("Password do not match");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                // This one too
            }
        });

        saveProfile.setOnClickListener(v -> {
            if (userName.getText().toString().trim().length() != 0 && password.getText().toString().trim().length() != 0 &&  repeatPassword.getText().toString().trim().length() != 0 && password.getText().toString().trim().equals(repeatPassword.getText().toString().trim())){
                //The details are saved , thus can be used later for authentication purpose
                SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("username",userName.getText().toString().trim());
                editor.putString("password",password.getText().toString().trim());
                editor.apply();

                sendToHome();
            }else{
                Toast toast = Toast.makeText(getContext(), "Entry not saved as not all data entered. Complete all entries and try again.", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        cancelProfile.setOnClickListener(v->{
            sendToHome();
        });

        getActivity().getOnBackPressedDispatcher().addCallback(new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
              sendToHome();
            }
        });
        return mView;
    }

    private void sendToHome() {
        HomeFragment homeFragment = new HomeFragment();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, homeFragment, HOME_FRAGMENT).remove(ProfileFragment.this).addToBackStack(null).commit();
    }
}