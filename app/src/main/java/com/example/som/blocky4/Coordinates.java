package com.example.som.blocky4;

/**
 * Created by S.o.M on 5/1/18.
 */

public class Coordinates {
    private int row;
    private int column;

    public Coordinates(int Row, int Column, boolean State) {
        //The constructor will take the list array and assign every digit a coordinate value
        row=Row;
        column=Column;
        state=State;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public boolean getState() {
        return state;
    }

    public String toString() {
        return row + " " + column + " " + state;
    }
}
}
