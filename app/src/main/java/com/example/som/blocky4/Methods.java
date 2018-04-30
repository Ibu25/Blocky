package com.example.som.blocky4;

public class Methods {
    public Methods(){}

    public static boolean Trapped() {
        boolean result= false;
        if ( MazeGenerator.Maze[MazeGenerator.rowP+1][MazeGenerator.colP] != 1 ) result=true; //Check Right
        if ( MazeGenerator.Maze[MazeGenerator.rowP-1][MazeGenerator.colP] != 1 ) result=true; //Check Left
        if ( MazeGenerator.Maze[MazeGenerator.rowP][MazeGenerator.colP+1] != 1 ) result=true; //Check Down
        if ( MazeGenerator.Maze[MazeGenerator.rowP][MazeGenerator.colP-1] != 1 ) result=true; //Check Up
        return result;
    }//End of Trapped Method

    public static boolean checkMove() {
        if (MazeGenerator.value==0) {
            if (MazeGenerator.Maze[MazeGenerator.rowP+1][MazeGenerator.colP] != 1) return true; }
        if (MazeGenerator.value==1) {
            if (MazeGenerator.Maze[MazeGenerator.rowP-1][MazeGenerator.colP] != 1) return true; }
        if (MazeGenerator.value==2) {
            if (MazeGenerator.Maze[MazeGenerator.rowP][MazeGenerator.colP+1] != 1) return true; }
        if (MazeGenerator.value==3) {
            if (MazeGenerator.Maze[MazeGenerator.rowP][MazeGenerator.colP-1] != 1) return true; }
        return false;
    }//End of checkMove Method

    public static boolean Untrap() {
        //I believe there is something wrong in your list outprinting/iteration
        //System.out.println("Untrap Run");
        //System.out.println("rowP=" + Generator.rowP + " colP=" + Generator.colP);
        for (int i=MazeGenerator.list.size()-1; i>=0; i--) {
            //System.out.println("i="+i);
            //System.out.println("UntrapValue=" + Generator.list.get(i));
            if (MazeGenerator.list.get(i)==0) {
                MazeGenerator.rowP-=1;
                MazeGenerator.draw.add(1);
                MazeGenerator.list.remove(i);
                if (Trapped()) return true;}
            else if (MazeGenerator.list.get(i)==1) {
                MazeGenerator.rowP+=1;
                MazeGenerator.draw.add(0);
                MazeGenerator.list.remove(i);
                if (Trapped()) return true;}
            else if (MazeGenerator.list.get(i)==2) {
                MazeGenerator.colP-=1;
                MazeGenerator.draw.add(3);
                MazeGenerator.list.remove(i);
                if (Trapped()) return true;}
            else if (MazeGenerator.list.get(i)==3) {
                MazeGenerator.colP+=1;
                MazeGenerator.draw.add(2);
                MazeGenerator.list.remove(i);
                if (Trapped()) return true;}
            //System.out.println("UrowP=" + Generator.rowP + " UcolP=" + Generator.colP);
        }//End of for loop
        return false;
    }//End of Untrap Method
}//End of Methods Class

