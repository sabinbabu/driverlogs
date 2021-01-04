package com.binwin.driverlogs;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.binwin.driverlogs.Fragments.CarFragment;
import com.binwin.driverlogs.Fragments.HomeFragment;
import com.binwin.driverlogs.Fragments.ProfileFragment;
import com.binwin.driverlogs.database.DriverLogsLab;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class DriverLogsActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment()
    {
        return new HomeFragment();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_item_send:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setPositiveButton("ok", (dialog, id) -> {
                 sendEmail();
                 DriverLogsLab.get(this).deleteAllLogs();
                });

                builder.setNegativeButton("cancel", (dialog, id) -> {
                    // User cancelled the dialog
                });
                AlertDialog dialog = builder.create();
                dialog.setTitle("Are you sure? This will delete all entries.");
                dialog.setIcon(R.mipmap.ic_launcher);
                dialog.show();

                return true;
            case R.id.menu_item_save:
                Toast toast = Toast.makeText(this, "Entries saved to database", Toast.LENGTH_SHORT);
                toast.show();
                 return true;
            case R.id.menu_item_profile:
                ProfileFragment profileFragment = new ProfileFragment();
                this.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,profileFragment,"carFragment").addToBackStack(null).commit();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void sendEmail() {
        SharedPreferences sharedPreferences = this.getPreferences(Context.MODE_PRIVATE);
        String userName = sharedPreferences.getString("username",null);
        if (userName==null){
            userName = " ";
        }
        String finalUsername = userName;

        ArrayList<DriverLogs> driverLogsDetail = DriverLogsLab.get(this).getAllVehicleLog();

        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"recipient@cqu.edu.au"});
        i.putExtra(Intent.EXTRA_CC, new String[]{"recipient@cqu.edu.au"});
        i.putExtra(Intent.EXTRA_SUBJECT,  "New logger data");
        i.putExtra(Intent.EXTRA_TEXT   , finalUsername+"\n"+driverLogsDetail.toString().replace(",","").replace("[","").replace("]",""));
        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driverlogs);
    }
}