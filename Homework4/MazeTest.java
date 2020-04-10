
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JPanel;

/**
 * A class to test Maze.java.
 * @author Koffman and Wolfgang
 */
public class MazeTest extends JFrame implements GridColors {

    // data field
    private TwoDimGrid theGrid; // a 2-D grid of buttons

    /** Reads data file and defines array bitMap to match data file */
    public static void main(String[] args) {
        try {
            if (args.length < 1) {
                // no file name given
                String reply =
                        JOptionPane.showInputDialog("Enter number of rows");
                int nRows = Integer.parseInt(reply);
                reply =
                        JOptionPane.showInputDialog("Enter number of columns");
                int nCols = Integer.parseInt(reply);
                TwoDimGrid aGrid = new TwoDimGrid(nRows, nCols);
                new MazeTest(aGrid);
            } else {
                // Create array bitMap from a data file
                BufferedReader br =
                        new BufferedReader(new FileReader(args[0]));

                // Read each data line (a string) into
                // gridArrayList. Each element is a char array.
                ArrayList<char[]> gridArrayList = new ArrayList<char[]>();
                String line;
                while ((line = br.readLine()) != null) {
                    char[] row = line.toCharArray();
                    gridArrayList.add(row);
                }

                // bitMap is a 2-D array based on data in gridArrayList
                char[][] bitMap =
                        gridArrayList.toArray(new char[gridArrayList.size()][]);
                int nRows = bitMap.length;
                int nCols = bitMap[0].length;

                // create a new TwoDimGrid and recolor it based on bitMap
                TwoDimGrid aGrid = new TwoDimGrid(nRows, nCols);
                aGrid.recolor(bitMap, NON_BACKGROUND);
                new MazeTest(aGrid);
            }
        } catch (Exception ex) {
            System.err.println("Exception " + ex);
            ex.printStackTrace();
            System.exit(1);
        }
    }

    // Builds the GUI
    private MazeTest(TwoDimGrid aGrid) {
        theGrid = aGrid;
        getContentPane().add(aGrid, BorderLayout.CENTER);
 //       Blob aBlob = new Blob(aGrid);
        JTextArea instruct = new JTextArea(2, 20);
        instruct.setText("Toggle a button to change its color"
                + "\nPress SOLVE when ready");
        getContentPane().add(instruct, BorderLayout.NORTH);
        JButton solveButton = new JButton("SOLVE");
        solveButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                solve();
            }
        });
        JButton resetButton = new JButton("RESET");
        resetButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                (new Maze(theGrid)).restore();
            }
        });
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(solveButton);
        bottomPanel.add(resetButton);
        getContentPane().add(bottomPanel, BorderLayout.SOUTH);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public void solve() {
        Maze m = new Maze(theGrid);
        boolean found = false;


        boolean Problem1Test = false;
        boolean Problem2Test = true;
        boolean Problem3Test = false;
        
        if(Problem1Test)
            found = m.findMazePath();
        else if(Problem2Test){
        ArrayList <ArrayList<PairInt>> res = m.findAllMazePaths(0,0);
        if(res.size()>0)
                found = true;
            for(ArrayList<PairInt> x : res)
                System.out.println(x);
            }
        else if(Problem3Test){
            ArrayList<PairInt> min = m.findMazePathMin(0, 0);
            if(min.size() > 0)
                found = true;
            System.out.print(min);
            
        }
        if (found) {
            JOptionPane.showMessageDialog(null, "Success - reset maze and try again");
           
        } else {
            JOptionPane.showMessageDialog(null, "No path - reset maze and try again");
        }
    }
}
