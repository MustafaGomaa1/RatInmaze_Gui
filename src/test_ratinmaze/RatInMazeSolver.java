package test_ratinmaze;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lenovo
 */
class RatInMazeSolver {
    private final int[][] maze;
    private final int mazeRows;
    private final int mazeCols;
    private final int[][] solution;
    private final List<Thread> threads;

    
    
    public RatInMazeSolver(int[][] maze) {
        this.maze = maze;
        this.mazeRows = maze.length;
        this.mazeCols = maze[0].length;
        this.solution = new int[mazeRows][mazeCols];
        this.threads = new ArrayList<>();
    }

    public void solve() {
       try{
        if (!solveMazeUtil(0, 0)) {
            System.out.println("No solution exists!");
            return;
        }
        displaySolution();
       }catch(Exception ex){
           System.out.println(ex);
       }
    }

    private boolean solveMazeUtil(int row, int col) {
        try{
            if (row == mazeRows - 1 && col == mazeCols - 1 && maze[row][col] == 1) {
                solution[row][col] = 1;
                return true;
            }

            if (isSafe(row, col)) {
                solution[row][col] = 1;

                Thread downThread = new Thread(() -> solveMazeUtil(row + 1, col));
                Thread rightThread = new Thread(() -> solveMazeUtil(row, col + 1));

                threads.add(downThread);
                threads.add(rightThread);

                downThread.start();
                rightThread.start();

                try {
                    downThread.join();
                    rightThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (solution[row + 1][col] == 1 || solution[row][col + 1] == 1) {
                    return true;
                }
                solution[row][col] = 0;
            }
            return false;
        }catch(Exception ex){
            System.out.println(ex);
        }
        return false;
    }

    private boolean isSafe(int row, int col) {
        return row >= 0 && row < mazeRows && col >= 0 && col < mazeCols && maze[row][col] == 1;
    }

    private void displaySolution() {
        System.out.println("Solution:");
        for (int[] row : solution) {
            for (int col : row) {
                System.out.print(col + " ");
            }
            System.out.println();
        }
    }
    
    public int[][]getSolution(){
        return solution;
    }
    public int size(){
        return mazeRows;
    }
    
    @Override
    public String toString(){
        String str="";
    for (int[] maze1 : solution) {
        for (int j = 0; j<solution.length; j++) {
            str += String.valueOf(maze1[j]) + " ";
        }
        str+="\n";
    }
        return str;
    }
}
