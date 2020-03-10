package com.example.buzzapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
//import androidx.preference.PreferenceFragmentCompat;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        Spinner menu = findViewById(R.id.menu1);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.menu1, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        menu.setAdapter(adapter1);
//        menu.setOnItemSelectedListener(this);


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

                }
                if(selectedItem1.equals("Logout")){

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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