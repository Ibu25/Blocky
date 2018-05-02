package com.example.som.blocky4;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    Button NewGame;
    Button LoadGame;
    Button Tutorial;
    Boolean checkSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //This means the first thing that displays is the main menu
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        NewGame= (Button) findViewById(R.id.btn_NewGame);
        LoadGame= (Button) findViewById(R.id.btn_LoadGame);
        Tutorial= (Button) findViewById(R.id.btn_Tutorial);

        NewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                startActivity(intent);
            }
        });

        LoadGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        /*Tutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tutorial = new Intent(MainActivity.this,Tutorial.class);  //create an Intent to launch the Game Activity
                startActivity(tutorial);
           }
        });*/

    }

}
