package com.example.som.blocky4;

import java.util.ArrayList;

/**
 * Created by S.o.M on 4/30/18.
 */

public class All {

    private int xCoordinate;
    private int yCoordinate;
    private boolean Visited;
    private boolean Enemy;
    private boolean Blocked;
    private boolean Blocky;

    public All() {
        xCoordinate=0;
        yCoordinate=0;
        Visited=false;
        Enemy=false;
        Blocked=false;
        Blocky=false;
        //I given them all a tempeorary value until the setMethods do their work
    }

    public void setxCoordinate(int x) { xCoordinate = x; }
    public void setyCoordinate(int y) { yCoordinate = y; }
    public void setVisited(boolean v) { Visited = v; }
    public void setEnemy(boolean e) { Enemy = e; }
    public void setBlocked(boolean b) { Blocked = b; }
    public void setBlocky(boolean b) { Blocky = b; }

    public int getxCoordinate() { return xCoordinate; }
    public int getyCoordinate() { return yCoordinate; }
    public boolean getVisited() { return Visited; }
    public boolean getEnemy() { return Enemy; }
    public boolean getBlocked() { return Blocked; }
    public boolean getBlocky() { return Blocky; }

}
