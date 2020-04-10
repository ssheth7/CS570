
import java.util.*;
/**
 * Class that solves maze problems with backtracking.
 * @author Koffman and Wolfgang
 **/
public class Maze implements GridColors {

    /** The maze */
    private TwoDimGrid maze;

    public Maze(TwoDimGrid m) {
        maze = m;
    }

    /** Wrapper method. */
    public boolean findMazePath() {
        return findMazePath(0, 0); // (0, 0) is the start point.
    }

    /**
     * Attempts to find a path through point (x, y).
     * @pre Possible path cells are in BACKGROUND color;
     *      barrier cells are in ABNORMAL color.
     * @post If a path is found, all cells on it are set to the
     *       PATH color; all cells that were visited but are
     *       not on the path are in the TEMPORARY color.
     * @param x The x-coordinate of current point
     * @param y The y-coordinate of current point
     * @return If a path through (x, y) is found, true;
     *         otherwise, false
     */
    public boolean findMazePath(int x, int y) {
        // COMPLETE HERE FOR PROBLEM 1
        if(x < 0 || y < 0 || x >= maze.getNCols() || y >= maze.getNRows())
            return false;
        else if(maze.getColor(x, y) != NON_BACKGROUND)
            return false;
        else if(x == maze.getNCols()-1 && y == maze.getNRows()-1){
            maze.recolor(x, y, PATH);
            return true;
        }
        maze.recolor(x , y, PATH);
        boolean pl = findMazePath(x-1, y);
        boolean pr = findMazePath(x+1, y);
        boolean pu = findMazePath(x, y+1);
        boolean pd = findMazePath(x, y-1);
        if(pl ==false && pr == false && pu == false && pd == false){ 
            maze.recolor(x, y, TEMPORARY);
            return false;
            }
        else return true;
        }

    // ADD METHOD FOR PROBLEM 2 HERE
    public ArrayList<ArrayList<PairInt>> findAllMazePaths(int x,int y){
        ArrayList<ArrayList<PairInt>> result = new ArrayList<>();
        Stack<PairInt> trace = new Stack<PairInt>();
        findAllHelper(x, y, result, trace);
        return result;
    }

    private boolean findAllHelper(int x, int y, ArrayList<ArrayList<PairInt>> result, Stack<PairInt> trace){
        if(x < 0 || y < 0 || x >= maze.getNCols() || y >= maze.getNRows())
            return false;
        else if(maze.getColor(x, y) != NON_BACKGROUND)
            return false;
        else if(x == maze.getNCols()-1 && y == maze.getNRows()-1){
            trace.push(new PairInt(x, y));
            result.add(new ArrayList<PairInt>(trace));
            maze.recolor(x, y, NON_BACKGROUND);
            trace.pop();
            return true;
        }
        else{
        trace.push(new PairInt(x, y));
        maze.recolor(x, y, PATH);
        boolean pl =findAllHelper(x-1, y, result, trace);
        boolean pr =findAllHelper(x+1, y, result, trace);
        boolean pu =findAllHelper(x, y+1, result, trace);
        boolean pd =findAllHelper(x, y-1, result, trace);

        maze.recolor(x, y, NON_BACKGROUND);
        trace.pop();
        return true;
         }
    }
    
    // ADD METHOD FOR PROBLEM 3 HERE
    public ArrayList<PairInt> findMazePathMin( int x, int y){
        ArrayList<ArrayList<PairInt>> allPaths = findAllMazePaths(x, y);
        ArrayList<PairInt> shortestPath = new ArrayList<PairInt>();
        shortestPath = allPaths.get(0);
        for(int i = 1 ; i < allPaths.size();i++)
            if(allPaths.get(i).size() < shortestPath.size())
                shortestPath = allPaths.get(i);
        return shortestPath;
    }

    /*<exercise chapter="5" section="6" type="programming" number="2">*/
    public void resetTemp() {
        maze.recolor(TEMPORARY, BACKGROUND);
    }
    /*</exercise>*/

    /*<exercise chapter="5" section="6" type="programming" number="3">*/
    public void restore() {
        resetTemp();
        maze.recolor(PATH, BACKGROUND);
        maze.recolor(NON_BACKGROUND, BACKGROUND);
    }
    /*</exercise>*/
}
/*</listing>*/
