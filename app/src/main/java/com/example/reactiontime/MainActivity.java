package com.example.reactiontime;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SwitchCompat sch = findViewById(R.id.sch_mode);
        Button btnSolo = findViewById(R.id.btnSolo);

        //Save user color preference
        SharedPreferences preferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
        final SharedPreferences.Editor editor = preferences.edit();
        final boolean darkModeEnabled = preferences.getBoolean("darkModeEnabled", false);
        if(darkModeEnabled) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            sch.setText(getString(R.string.night_mode_on));
        }
        else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            sch.setText(getString(R.string.night_mode_off));
        }

        //Switch to night mode based on the toggle position
        sch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                sch.setText(getString(R.string.night_mode_on));
                editor.putBoolean("darkModeEnabled", true);
                editor.apply();

            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                sch.setText(getString(R.string.night_mode_off));
                editor.putBoolean("darkModeEnabled", false);
                editor.apply();
            }
        });

        //set on click listeners
        btnSolo.setOnClickListener(view -> SoloActivity());
    }
    //fire the appropriate activity
    public void SoloActivity(){
        Intent intent = new Intent(this, SoloActivity.class);
        startActivity(intent);
    }

}