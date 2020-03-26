package com.example.buzzapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.firebase.auth.FirebaseAuth;
//import androidx.preference.PreferenceFragmentCompat;

public class SettingsActivity extends AppCompatActivity {
    Switch darkmode;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        Spinner menu = findViewById(R.id.menu1);


        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.menu1, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        menu.setAdapter(adapter1);

        menu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem1 = parent.getItemAtPosition(position).toString();
                if(selectedItem1.equals("Maps")){
                    Intent i = new Intent(SettingsActivity.this, MapsActivity.class);
                    startActivity(i);
                }
                if(selectedItem1.equals("Settings")){

                }
                if(selectedItem1.equals("Help")){
                    Intent help = new Intent (SettingsActivity.this , HelpActivity.class);
                    startActivity(help);

                }
                if(selectedItem1.equals("Logout")){
                    FirebaseAuth.getInstance().signOut();
                    finish();
                    Intent i = new Intent(SettingsActivity.this, LoginActivity.class);
                    startActivity(i);
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        darkmode = findViewById(R.id.darkModeSwitch);
        darkmode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }
                else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
            }

        });

        getSupportFragmentManager()
                .beginTransaction()
                //.replace(R.id.settings, new SettingsFragment())
                .commit();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    //public static class SettingsFragment extends PreferenceFragmentCompat {
       // @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            //setPreferencesFromResource(R.xml.root_preferences, rootKey);
        }
    }
//}