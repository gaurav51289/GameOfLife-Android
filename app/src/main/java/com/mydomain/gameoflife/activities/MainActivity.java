package com.mydomain.gameoflife.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mydomain.gameoflife.R;

public class MainActivity extends AppCompatActivity {

    Button btnAbout, btnNewGame, btnSettingsMain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Hide the Status Bar
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        btnAbout = (Button) findViewById(R.id.btnAbout);
        btnNewGame = (Button) findViewById(R.id.btnNew);
        btnSettingsMain = (Button) findViewById(R.id.btnSettingsMain);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btnAbout:
                        Intent aboutIntent = new Intent(v.getContext(), AboutActivity.class);
                        startActivity(aboutIntent);
                        break;
                    case R.id.btnNew:
                        Intent gridIntent = new Intent(v.getContext(), GridActivity.class);
                        startActivity(gridIntent);
                        break;
                    case R.id.btnSettingsMain:
                        Intent settingsIntent = new Intent(v.getContext(), SettingsActivity.class);
                        startActivity(settingsIntent);
                        break;
                }
            }
        };


        btnAbout.setOnClickListener(listener);
        btnNewGame.setOnClickListener(listener);
        btnSettingsMain.setOnClickListener(listener);

    }
}
