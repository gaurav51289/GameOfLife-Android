package com.mydomain.gameoflife;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnAbout, btnNewGame;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAbout = (Button) findViewById(R.id.btnAbout);
        btnNewGame = (Button) findViewById(R.id.btnNew);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btnAbout:
                        Intent aboutIntent = new Intent(v.getContext(), AboutActivity.class);
                        startActivity(aboutIntent);
                        break;
                    case R.id.btnNew:
                        Intent gridIntent = new Intent(v.getContext(), GridActivity.class);
                        startActivity(gridIntent);
                        break;
                }
            }
        };


        btnAbout.setOnClickListener(listener);
        btnNewGame.setOnClickListener(listener);

    }


}