package com.mydomain.gameoflife.activities;

import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

import com.mydomain.gameoflife.R;

public class SettingsActivity extends PreferenceActivity {

    private static String UNDERPOP = "underpopulate_number";
    private static String UNDERPOP_DEF = "2";
    private static String OVERPOP = "overpopulate_number";
    private static String OVERPOP_DEF = "3";
    private static String SPAWN = "spawn_number";
    private static String SPAWN_DEF = "3";

    private static String INIT_PATTERN = "init_pattern";
    private static String INIT_PATTERN_DEF = "1";

    private static String GRID_SIZE = "grid_size";
    private static String GRID_SIZE_DEF = "60";

    private static String COLOR_BG = "color_bg";
    private static int COLOR_BG_DEF = -1654076;

    private static String COLOR_CELL = "color_cell";
    private static int COLOR_CELL_DEF = -1952190;


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences);
    }

    public static String getUnderPopNum(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getString(UNDERPOP, UNDERPOP_DEF);
    }

    public static String getOverPopNum(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getString(OVERPOP, OVERPOP_DEF);
    }

    public static String getSpawnNum(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getString(SPAWN, SPAWN_DEF);
    }

    public static String getInitPattern(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getString(INIT_PATTERN, INIT_PATTERN_DEF);
    }

    public static String getGridSize(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getString(GRID_SIZE, GRID_SIZE_DEF);
    }

    public static int getBackgroundColor(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getInt(COLOR_BG, COLOR_BG_DEF);
    }

    public static int getCellColor(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getInt(COLOR_CELL, COLOR_CELL_DEF);
    }
}
