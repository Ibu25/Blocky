package com.example.som.blocky4;

import java.util.ArrayList;
import java.util.Random;

public class MazeGenerator {
    public static int Columns;
    public static int Rows;
    public static int rowP;
    public static int colP;
    public static int[][] Maze;
    public static int[][] TutorialMaze;
    public static int value;
    public static ArrayList<Integer> list = new ArrayList<Integer>();
    public static ArrayList<Integer> draw = new ArrayList<Integer>();

    public MazeGenerator(int row, int column)
    {
        Columns=column; Rows=row;
        Maze= new int[Rows+2][Columns+2];
        for (int i=0; i<Rows+2; i++) { Maze[i][0]=1;}
        for (int i=0; i<Rows+2; i++) { Maze[i][Columns+1]=1;}
        for (int i=0; i<Columns+2; i++) { Maze[0][i]=1;}
        for (int i=0; i<Columns+2; i++) { Maze[Rows+1][i]=1;}
        rowP=1; colP=1;
        MazeGenerator.Maze[1][1]=1;
        value=0;

        TutorialMaze= new int[][]{
                {0, 0, 0, 0, 0},
                {1, 1, 1, 0, 0},
                {0, 0, 1, 1, 1}
        };

    }//End of Constructor

    public void createPath() {
        Random rand = new Random();//Creates instance of Random
        while (Maze[Rows][Columns] != 1) {
            value=rand.nextInt(4);//Give value a random value (0||1||2||3)
            if (Methods.Trapped()) {
                value=rand.nextInt(4);
                if (value==0 && Methods.checkMove()) {
                    rowP+=1;
                    Maze[rowP][colP]=1;
                    list.add(value);
                    draw.add(value);
                    //System.out.println("Move Value="+value);
                }
                if (value==1 && Methods.checkMove()) {
                    rowP-=1;
                    Maze[rowP][colP]=1;
                    list.add(value);
                    draw.add(value);
                    //System.out.println("Move Value="+value);
                }
                if (value==2 && Methods.checkMove()) {
                    colP+=1;
                    Maze[rowP][colP]=1;
                    list.add(value);
                    draw.add(value);
                    //System.out.println("Move Value="+value);
                }
                if (value==3 && Methods.checkMove()) {
                    colP-=1;
                    Maze[rowP][colP]=1;
                    list.add(value);
                    draw.add(value);
                    //System.out.println("Move Value="+value);
                }
            }
            else {
                //for (int i=0; i<Generator.list.size(); i++) {
                //System.out.print(Generator.list.get(i)); }
                //System.out.println(" ");
                Methods.Untrap();
            }
        }

    }//End of createPath Method

    public static int[][] getMaze() {
        Maze[Rows][Columns] = 2;
        return Maze;
    }

    public static int[][] getTutorialMaze() {
        return TutorialMaze;
    }

}//End of Generator Class


