package com.mydomain.gameoflife;

import android.app.Activity;
import android.os.Bundle;


public class GridActivity extends Activity {

    GridView mGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_layout);

        mGridView = (GridView) findViewById(R.id.grid_view);
        mGridView.setViewState(GridView.RUNNING);
    }

    @Override
    protected void onPause(){
        super.onPause();
        mGridView.setViewState(GridView.PAUSE);
    }


}
