package com.example.som.blocky4;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff;
import android.hardware.camera2.params.BlackLevelPattern;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.Random;

import static com.example.som.blocky4.R.color.colorPrimaryDark;

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
    ArrayList<ArrayList<All>> a;
    ArrayList<Integer> dimension;
    boolean[][] Enemies;
    int xBlocky;
    int yBlocky;
    int oldxBlocky;
    int oldyBlocky;

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

        paintBlocky.setColor(Color.rgb(48, 63, 159));
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
        a= new ArrayList<ArrayList<All>>();

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
            ArrayList<All> b=new ArrayList<All>();
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
                b.add(butt);
            }
            a.add(b);
        }

        location = new Point(0, 200);
    }//End of initialize method

    public void updateAll() {
        if (!((a.get(xBlocky).get(yBlocky)).getBlocked())) {
            ((a.get(oldxBlocky)).get(oldyBlocky)).setBlocky(false);
            ((a.get(xBlocky)).get(yBlocky)).setBlocky(true);
            ((a.get(xBlocky)).get(yBlocky)).setVisited(true);
            paintBlocky.setColor(Color.rgb(48, 63, 159));
        }
        else {
            xBlocky=oldxBlocky;
            yBlocky=oldyBlocky;
            paintBlocky.setColor(Color.RED);
        }

    }

    public void startThread() {
        drawThread = new DrawThread(getHolder(), this);
        drawThread.setRunning(true);
        drawThread.start();
    }//End of startThread

    /*public void stopThread() {
        drawThread.setRunning(false);
        drawThread.stop();
    }//End of stopThread */

    public void Up() {
        Log.i("WidthTag", "AAAAAAHHHHHHHHHH");
        oldxBlocky=xBlocky;
        oldyBlocky=yBlocky;
        yBlocky -= 1;

        updateAll();
        invalidate();
    }//End of Up

    public void Down() {
        oldxBlocky=xBlocky;
        oldyBlocky=yBlocky;
        yBlocky += 1;
        updateAll();
        invalidate();
    }//End of Down

    public void Left() {
        oldxBlocky=xBlocky;
        oldyBlocky=yBlocky;
        xBlocky -= 1;
        updateAll();
        invalidate();
    }//End of Left

    public void Right() {
        oldxBlocky=xBlocky;
        oldyBlocky=yBlocky;
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
        int width = canvas.getWidth();
        int w= width/60;
        int height=canvas.getHeight();
        int h=height/40;
        int u;
        if (w < h) { u=w; } else u=h;
        for (int i = 0; i < 40; i++) {
            for (int z = 0; z < 60; z++) {

                //If  is there, draw a square that is blue
                if ((a.get(i)).get(z).getBlocky()) {
                    canvas.drawRect((a.get(i)).get(z).getxCoordinate() * u, (a.get(i)).get(z).getyCoordinate() * u, ((a.get(i)).get(z).getxCoordinate() * u) + u, ((a.get(i)).get(z).getyCoordinate() * u) + u, paintBlocky);
                }

                //If blocked or unvisted, draw a square that is black
                else if ((a.get(i)).get(z).getBlocked() || !(a.get(i)).get(z).getVisited()) {
                    canvas.drawRect((a.get(i)).get(z).getxCoordinate() * u, (a.get(i)).get(z).getyCoordinate() * u, ((a.get(i)).get(z).getxCoordinate() * u) + u, ((a.get(i)).get(z).getyCoordinate() * u) + u, paintBlocked);
                }

                //If enemy is there and visited, draw a red square
                else if ((a.get(i)).get(z).getVisited() && (a.get(i)).get(z).getEnemy()) {
                    canvas.drawRect((a.get(i)).get(z).getxCoordinate() * u, (a.get(i)).get(z).getyCoordinate() * u, ((a.get(i)).get(z).getxCoordinate() * u) + u, ((a.get(i)).get(z).getyCoordinate() * u) + u, paintEnemies);
                } else
                    canvas.drawRect((a.get(i)).get(z).getxCoordinate() * u, (a.get(i)).get(z).getyCoordinate() * u, ((a.get(i)).get(z).getxCoordinate() * u) + u, ((a.get(i)).get(z).getyCoordinate() * u) + u, paintOpen);

            }//End of for loop
        }
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

        @SuppressLint("WrongCall")
        public void run() {
            Canvas canvas = null;
            while (run) {
                try {
                    canvas = surfaceHolder.lockCanvas(null);
                    synchronized (surfaceHolder) {
                        mySurfaceView.onDraw(canvas);
                        mySurfaceView.updateAll();
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
