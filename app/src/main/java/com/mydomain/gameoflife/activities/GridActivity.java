package com.mydomain.gameoflife.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.mydomain.gameoflife.customviews.CustomGridView;
import com.mydomain.gameoflife.R;
import com.mydomain.gameoflife.gamealgo.GameState;


public class GridActivity extends Activity {

    CustomGridView mCustomGridView;

    ImageButton btnPlay, btnPause;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_layout);

        mCustomGridView = (CustomGridView) findViewById(R.id.grid_view);
        GameState.getInstance().setState(GameState.PAUSE);
        mCustomGridView.setOnTouchListener(mCustomGridView);

        btnPlay = (ImageButton) findViewById(R.id.btnPlay);
        btnPause = (ImageButton) findViewById(R.id.btnPause);


        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch(v.getId()){
                    case R.id.btnPlay:
                        GameState.getInstance().setState(GameState.PLAY);
                        mCustomGridView.updateGridView();
                        btnPlay.setVisibility(ImageView.GONE);
                        btnPause.setVisibility(ImageView.VISIBLE);
                        break;

                    case R.id.btnPause:
                        GameState.getInstance().setState(GameState.PAUSE);
                        btnPause.setVisibility(ImageView.GONE);
                        btnPlay.setVisibility(ImageView.VISIBLE);
                        break;
                }
            }
        };

        btnPlay.setOnClickListener(listener);
        btnPause.setOnClickListener(listener);

    }

    @Override
    protected void onPause(){
        super.onPause();
        GameState.getInstance().setState(GameState.PAUSE);
    }

    @Override
    protected void onResume(){
        super.onResume();
        GameState.getInstance().setState(GameState.PLAY);
        mCustomGridView.updateGridView();
    }


}
