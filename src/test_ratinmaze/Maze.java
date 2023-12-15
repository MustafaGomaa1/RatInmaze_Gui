package test_ratinmaze;

import java.util.Random;

public class Maze {
private static int[][]maze;
    
    
    Maze(int x){
         Maze.maze=new int[x][x];
      Random r=new Random();
         for (int[] maze1 : maze) {
             for (int j = 0; j < maze1.length; j++) {
                 maze1[j] = r.nextInt(2);
             }
         }
        if(maze[0][0]==0 || maze[x-1][x-1]==0){
            maze[0][0]=maze[x-1][x-1]=1;
        }
    }
    
    public int[][]maze(){
        return Maze.maze;
    }
    
    /**
     *
     * @return
     */
    @Override
    public String toString(){
        String str="";
    for (int[] maze1 : maze) {
        for (int j = 0; j<maze.length; j++) {
            str += String.valueOf(maze1[j]) + " ";
        }
        str+="\n";
    }
        return str;
    }
}

