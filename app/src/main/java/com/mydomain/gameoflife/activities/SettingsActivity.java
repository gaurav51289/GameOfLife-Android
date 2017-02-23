package com.mydomain.gameoflife.activities;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import com.mydomain.gameoflife.R;

public class SettingsActivity extends PreferenceActivity {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences);
    }


}
