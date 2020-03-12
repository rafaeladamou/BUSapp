package com.example.buzzapp;

import android.content.Intent;
import android.icu.util.ValueIterator;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;





public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help_page);
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
                    Intent i = new Intent(HelpActivity.this, MapsActivity.class);
                    startActivity(i);
                }
                if(selectedItem1.equals("Settings")){
                    Intent i = new Intent(HelpActivity.this,SettingsActivity.class);
                    startActivity(i);


                }
                if(selectedItem1.equals("Help")){
                    Intent i = new Intent(HelpActivity.this , HelpActivity.class);
                    startActivity(i);

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