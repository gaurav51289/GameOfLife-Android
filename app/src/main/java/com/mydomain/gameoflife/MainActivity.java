package com.mydomain.gameoflife;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAbout = (Button) findViewById(R.id.btnAbout);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btnAbout:
                        Intent i = new Intent(v.getContext(), AboutActivity.class);
                        startActivity(i);
                        break;
                }
            }
        };


        btnAbout.setOnClickListener(listener);

    }


}
