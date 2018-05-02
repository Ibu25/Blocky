package com.example.som.blocky4;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff;
import android.hardware.camera2.params.BlackLevelPattern;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by S.o.M on 4/30/18.
 */

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    //Class Variables
    private DrawThread drawThread;
    private Point location;
    Canvas canvas = null;
    //Paint Variables
    private Paint paint = new Paint();
    private Paint paintOpen = new Paint();
    private Paint paintBlocked = new Paint();
    private Paint paintBlocky = new Paint();
    private Paint paintEnemies = new Paint();

    MazeGenerator maze;
    int[][] Maze;
    ArrayList<All> all;
    ArrayList<Integer> dimension;
    boolean[][] Enemies;
    int xBlocky;
    int yBlocky;

    //Start of 3 Constructors
    public MySurfaceView(Context context) {
        super(context);
        initialize();
    }//End of MySurface view constructor w/ 1 inputs

    public MySurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }//End of MySurface view constructor w/ 2 inputs

    public MySurfaceView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initialize();
    }//End of MySurface view constructor w/ 3 inputs
    //End of 3 Constructors

    private void initialize() {
        getHolder().addCallback(this);
        setFocusable(true);
        //He set the paint settings here but I'm gonna go a bit different

        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(1);
        paint.setAntiAlias(true);
        paint.setStrokeCap(Cap.SQUARE);
        paint.setStyle(Style.FILL);

        paintOpen.setColor(Color.WHITE);
        paintOpen.setStyle(Style.FILL);
        paintOpen.setAntiAlias(true);

        paintBlocked.setColor(Color.BLACK);
        paintBlocked.setStyle(Style.FILL);
        paintBlocked.setAntiAlias(true);

        paintBlocky.setColor(Color.BLUE);
        paintBlocky.setStyle(Style.FILL);
        paintBlocky.setAntiAlias(true);

        paintEnemies.setColor(Color.RED);
        paintEnemies.setStyle(Style.FILL);
        paintEnemies.setAntiAlias(true);

        maze = new MazeGenerator(40, 60);

        maze.createPath();
        Maze = maze.getMaze();
        xBlocky = 1;
        yBlocky = 1;
        all = new ArrayList<All>();

        for (int i = 0; i < 40 + 2; i++) {
            Maze[i][0] = 0;
        }
        for (int i = 0; i < 40 + 2; i++) {
            Maze[i][60 + 1] = 0;
        }
        for (int i = 0; i < 60 + 2; i++) {
            Maze[0][i] = 0;
        }
        for (int i = 0; i < 60 + 2; i++) {
            Maze[40 + 1][i] = 0;
        }

        for (int row = 0; row < 40; row++) {
            for (int col = 0; col < 60; col++) {

                All butt = new All();
                butt.setxCoordinate(row);
                butt.setyCoordinate(col);
                if ((Maze[row][col] == 0)) {
                    butt.setBlocked(true);
                } else butt.setBlocked(false);
                if ((row == xBlocky) && (col == yBlocky)) {
                    butt.setBlocky(true);
                    butt.setVisited(true);
                } else butt.setBlocky(false);
                all.add(butt);
            }
        }

        location = new Point(0, 200);
    }//End of initialize method

    public void updateAll() {
        ArrayList<All> hold = new ArrayList<All>();
        for (int row = 0; row < 40; row++) {
            for (int col = 0; col < 60; col++) {
                All butt = new All();
                butt.setxCoordinate(row);
                butt.setyCoordinate(col);
                if ((Maze[row][col] == 0)) {
                    butt.setBlocked(true);
                } else butt.setBlocked(false);
                if ((row == xBlocky) && (col == yBlocky)) {
                    butt.setBlocky(true);
                    butt.setVisited(true);
                } else butt.setBlocky(false);
                hold.add(butt);
            }
        }
        for (int i = 0; i < 2400; i++) {
            (hold.get(i)).setVisited((all.get(i)).getVisited());
        }
        all = hold;
    }

    public void startThread() {
        drawThread = new DrawThread(getHolder(), this);
        drawThread.start();
    }//End of startThread

    /*public void stopThread() {
        drawThread.setRunning(false);
        drawThread.stop();
    }//End of stopThread */

    public void Up() {
        Log.i("WidthTag", "AAAAAAHHHHHHHHHH");
        if
        yBlocky -= 1;

        updateAll();
        invalidate();
    }//End of Up

    public void Down() {
        yBlocky += 1;
        updateAll();
        invalidate();
    }//End of Down

    public void Left() {
        xBlocky -= 1;
        updateAll();
        invalidate();
    }//End of Left

    public void Right() {
        xBlocky += 1;
        updateAll();
        invalidate();
    }//End of Right

    public void Attack() {
        if ((Enemies[xBlocky + 1][yBlocky] == true) || (Enemies[xBlocky - 1][yBlocky] == true) || (Enemies[xBlocky][yBlocky + 1] == true) || (Enemies[xBlocky][yBlocky - 1] == true)) {
            Random rand = new Random();
            int r = rand.nextInt(1);
            //Effectivelly what happens is that you have a 50/50 chance of killing every enemy around you
            if (r == 0) {
                Enemies[xBlocky + 1][yBlocky] = false;
                Enemies[xBlocky - 1][yBlocky] = false;
                Enemies[xBlocky][yBlocky + 1] = false;
                Enemies[xBlocky][yBlocky - 1] = false;
            }
            //else
        }
    }//End of Attack

    public void Heal() {

    }//End of Heal

    public void Wait() {

    }//End of Wait

    public void onDraw(Canvas canvas) {
        canvas.drawColor(0, PorterDuff.Mode.CLEAR);
        canvas.drawColor(Color.BLACK);
        for (int i = 0; i < all.size(); i++) {
            all.get(i);

            //If blocky is there, draw a square that is blue
            if (all.get(i).getBlocky()) {
                canvas.drawRect(all.get(i).getxCoordinate()*25, all.get(i).getyCoordinate()*25, (all.get(i).getxCoordinate()*25) + 25, (all.get(i).getyCoordinate()*25) + 25, paintBlocky);
            }

            //If blocked or unvisted, draw a square that is black
            else if (all.get(i).getBlocked() || !(all.get(i).getVisited())) {
                canvas.drawRect(all.get(i).getxCoordinate()*25, all.get(i).getyCoordinate()*25, (all.get(i).getxCoordinate()*25) + 25, (all.get(i).getyCoordinate()*25) + 25, paintBlocked);
            }

            //If enemy is there and visited, draw a red square
            else if (all.get(i).getVisited() && all.get(i).getEnemy()) {
                canvas.drawRect(all.get(i).getxCoordinate()*25, all.get(i).getyCoordinate()*25, (all.get(i).getxCoordinate()*25) + 25, (all.get(i).getyCoordinate()*25) + 25, paintEnemies);
            }
            else canvas.drawRect(all.get(i).getxCoordinate()*25, all.get(i).getyCoordinate()*25, (all.get(i).getxCoordinate()*25) + 25, (all.get(i).getyCoordinate()*25) + 25, paintOpen);
        }//End of for loop
    }//End of onDraw

    class DrawThread extends Thread {
        private SurfaceHolder surfaceHolder;
        MySurfaceView mySurfaceView;
        private boolean run = false;

        public DrawThread(SurfaceHolder surfaceHolder, MySurfaceView mySurfaceView) {
            this.surfaceHolder = surfaceHolder;
            this.mySurfaceView = mySurfaceView;
            run = false;
            //This ensures that the thread doesn't run until you click start
        }//End of DrawThread constructor

        public void setRunning(boolean run) {
            this.run = run;
        }

        public void run() {
            Canvas canvas = null;
            if (run) {
                try {
                    canvas = surfaceHolder.lockCanvas(null);
                    synchronized (surfaceHolder) {
                        mySurfaceView.onDraw(canvas);
                        //mySurfaceView.update();
                    }
                } finally {
                    if (canvas != null) {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }
                }//End of Finally
            }//End of if
        }//End of run Method
    }//End of DrawThread class

    @Override
    public void surfaceDestroyed (SurfaceHolder SurfaceHolder){
        // TODO Auto-generated method stub
    }
    @Override
    public void surfaceChanged (SurfaceHolder SurfaceHolder,int format, int width, int height){
        // TODO Auto-generated method stub
    }
    @Override
    public void surfaceCreated (SurfaceHolder SurfaceHolder){
        // TODO Auto-generated method stub
    }
}//End
