package com.mydomain.gameoflife.activities;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.mydomain.gameoflife.customviews.CustomGridView;
import com.mydomain.gameoflife.R;
import com.mydomain.gameoflife.gamealgo.GameState;


public class GridActivity extends Activity {

    CustomGridView mCustomGridView;

    ImageButton btnPlay, btnPause, btnNext, btnReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_layout);

        //Hide the Status Bar
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        mCustomGridView = (CustomGridView) findViewById(R.id.grid_view);
        GameState.getInstance().setState(GameState.PAUSE);
        mCustomGridView.setOnTouchListener(mCustomGridView);

        btnPlay = (ImageButton) findViewById(R.id.btnPlay);
        btnPause = (ImageButton) findViewById(R.id.btnPause);
        btnNext = (ImageButton) findViewById(R.id.btnNext);
        btnReset = (ImageButton) findViewById(R.id.btnReset);


        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch(v.getId()){
                    case R.id.btnPlay:
                        doPlay();
                        break;

                    case R.id.btnPause:
                        doPause();
                        break;

                    case R.id.btnNext:
                        doNext();
                        break;

                    case R.id.btnReset:
                        doReset();
                        break;
                }
            }
        };

        btnPlay.setOnClickListener(listener);
        btnPause.setOnClickListener(listener);
        btnNext.setOnClickListener(listener);
        btnReset.setOnClickListener(listener);

    }

    @Override
    protected void onPause(){
        super.onPause();
        GameState.getInstance().setState(GameState.PAUSE);
    }

    @Override
    protected void onResume(){
        super.onResume();
//        GameState.getInstance().setState(GameState.PLAY);
//        mCustomGridView.updateGridView();
    }

    private void doNext() {
        if(GameState.getInstance().getState() == GameState.PAUSE){
            mCustomGridView.updateGridView();
        }
    }

    private void doPause() {
        GameState.getInstance().setState(GameState.PAUSE);
        btnPause.setVisibility(ImageView.GONE);
        btnPlay.setVisibility(ImageView.VISIBLE);
    }

    private void doPlay() {
        GameState.getInstance().setState(GameState.PLAY);
        mCustomGridView.updateGridView();
        btnPlay.setVisibility(ImageView.GONE);
        btnPause.setVisibility(ImageView.VISIBLE);
    }

    private void doReset() {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AlertDialogCustom));
        alertBuilder
            .setTitle(R.string.reset_alert_title)
            .setMessage(R.string.reset_alert_message)
            .setPositiveButton(R.string.yes_label, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    GameState.getInstance().setState(GameState.PAUSE);
                    mCustomGridView.resetGridView();
                    btnPause.setVisibility(ImageView.GONE);
                    btnPlay.setVisibility(ImageView.VISIBLE);
                }
            })
            .setNegativeButton(R.string.no_label, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    return;
                }
            });

        alertBuilder.create().show();

    }

}
