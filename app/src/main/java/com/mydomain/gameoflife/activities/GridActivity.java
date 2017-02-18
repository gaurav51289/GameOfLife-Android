package com.mydomain.gameoflife.activities;

import android.app.Activity;
import android.os.Bundle;

import com.mydomain.gameoflife.customviews.CustomGridView;
import com.mydomain.gameoflife.R;


public class GridActivity extends Activity {

    CustomGridView mCustomGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_layout);

        mCustomGridView = (CustomGridView) findViewById(R.id.grid_view);
        mCustomGridView.setViewState(CustomGridView.RUNNING);
    }

    @Override
    protected void onPause(){
        super.onPause();
        mCustomGridView.setViewState(CustomGridView.PAUSE);
    }


}
