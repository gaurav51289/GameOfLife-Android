package com.mydomain.gameoflife.activities;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
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

    ImageButton btnPlay, btnPause, btnNext, btnReset, btnSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_layout);


        hideStatusBar();
        mCustomGridView = (CustomGridView) findViewById(R.id.grid_view);
        GameState.getInstance().setState(GameState.PAUSE);
        mCustomGridView.setOnTouchListener(mCustomGridView);

        btnPlay = (ImageButton) findViewById(R.id.btnPlay);
        btnPause = (ImageButton) findViewById(R.id.btnPause);
        btnNext = (ImageButton) findViewById(R.id.btnNext);
        btnReset = (ImageButton) findViewById(R.id.btnReset);
        btnSettings = (ImageButton) findViewById(R.id.btnSettings);


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

                    case R.id.btnSettings:
                        openSettings(v);
                        break;

                }
            }
        };

        btnPlay.setOnClickListener(listener);
        btnPause.setOnClickListener(listener);
        btnNext.setOnClickListener(listener);
        btnReset.setOnClickListener(listener);
        btnSettings.setOnClickListener(listener);

    }


    @Override
    protected void onPause(){
        super.onPause();
        doPause();

//        String FILENAME = "gameoflife";
//        boolean[][] boolArr = new boolean[2][2];
//        boolArr[0][0] = true;
//        boolArr[1][1] = true;
//
//        FileOutputStream fos = null;
//        try {
//            fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
//            for(int i = 0; i < boolArr.length; i++){
//                for(int j = 0; j < boolArr[0].length; j++){
//                    fos.write(boolArr[i][j] ? 1 : 0);
//                }
//            }
//            fos.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }

    @Override
    protected void onResume(){
        super.onResume();
        mCustomGridView.updateGridView();

//        String FILENAME = "gameoflife";
//        FileInputStream fis;
//
//        try {
//            fis = openFileInput(FILENAME);
//            int content;
//            while ((content = fis.read()) != -1) {
//
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


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

        onPause();

        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AlertDialogCustom));

        alertBuilder
            .setTitle(R.string.reset_alert_title)
            .setMessage(R.string.reset_alert_message)
            .setPositiveButton(R.string.yes_label, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    hideStatusBar();
                    GameState.getInstance().setState(GameState.PAUSE);
                    mCustomGridView.resetGridView();
                    btnPause.setVisibility(ImageView.GONE);
                    btnPlay.setVisibility(ImageView.VISIBLE);
                    onResume();
                    return;
                }
            })
            .setNegativeButton(R.string.no_label, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    hideStatusBar();
                    GameState.getInstance().setState(GameState.PLAY);
                    onResume();
                    return;
                }
            });

        alertBuilder.create().show();

    }

    private void openSettings(View v) {

        Intent settingsIntent = new Intent(v.getContext(), SettingsActivity.class);
        startActivity(settingsIntent);

    }


    private void hideStatusBar(){
        //Hide the Status Bar
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }

}
