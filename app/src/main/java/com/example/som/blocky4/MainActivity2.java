package com.example.som.blocky4;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;

import static android.R.attr.button;
import static android.content.ContentValues.TAG;


/**
 * Created by S.o.M on 4/29/18.
 */

public class MainActivity2 extends Activity implements OnClickListener {

    MySurfaceView mySurfaceView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);

            //Start Making the App Full Screen
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            setContentView(R.layout.activity_main2);
            //Done Making the App Full Screen

            //Start initializing all the Buttons
            findViewById(R.id.btnUp).setOnClickListener(this);
            findViewById(R.id.btnDown).setOnClickListener(this);
            findViewById(R.id.btnLeft).setOnClickListener(this);
            findViewById(R.id.btnRight).setOnClickListener(this);
            findViewById(R.id.btnAttack).setOnClickListener(this);
            findViewById(R.id.btnHeal).setOnClickListener(this);
            findViewById(R.id.btnWait).setOnClickListener(this);
            findViewById(R.id.btnSave).setOnClickListener(this);
            findViewById(R.id.btnStart).setOnClickListener(this);
            //Done Initializing all the Buttons

            mySurfaceView = (MySurfaceView) (findViewById(R.id.surface));
        } catch (Exception e) {
            Log.d(TAG, "Failed to create; " + e.getMessage());
            e.printStackTrace();
        }

    }
    @Override
    public void onClick (View v){
        switch (v.getId()) {
            case R.id.btnStart:
                try {
                    mySurfaceView.startThread();
                    v.setVisibility(View.GONE);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case R.id.btnUp:
                try {
                    mySurfaceView.Up();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case R.id.btnDown:
                try {
                    mySurfaceView.Down();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case R.id.btnLeft:
                try {
                    mySurfaceView.Left();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case R.id.btnRight:
                try {
                    mySurfaceView.Right();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case R.id.btnAttack:
                try {
                    mySurfaceView.Attack();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case R.id.btnHeal:
                try {
                    mySurfaceView.Heal();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case R.id.btnWait:
                try {
                    mySurfaceView.Wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
