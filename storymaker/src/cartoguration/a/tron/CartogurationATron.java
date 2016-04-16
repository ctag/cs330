/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cartoguration.a.tron;

import edu.uci.ics.jung.algorithms.layout.BalloonLayout;
import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.DAGLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.algorithms.layout.SpringLayout2;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import java.awt.Dimension;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;

/**
 *
 * @author Luc
 */
public class CartogurationATron {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        //This makes the DB
        //StateDB.createDB();
        //StateDB.instert_Element("AA", "I am a test", "I-am-not-real");
        //StateDB.instert_Modifier("Fame", ".5", "I-am-not-real", "This is a Desc");
        List<StoryBlock> list_of_blocks = new ArrayList<StoryBlock>();
        StoryBlock g = new StoryBlock("[1]");
        
        StorySystem game = StorySystem.makeTestHero();//.makeRandomSetBlocks(50);
        game.buildTestGraph();
        
        System.out.print(game.printAvailable());
        
        System.out.print(game.StoryMap.toString());
        
        
        
        Layout<Integer, String> layout = new DAGLayout(game.StoryMap);
        layout.setSize(new Dimension(800,800)); // sets the initial size of the space
        // The BasicVisualizationServer<V,E> is parameterized by the edge types
        BasicVisualizationServer<Integer,String> vv = new BasicVisualizationServer<Integer,String>(layout);
        vv.setPreferredSize(new Dimension(1500,1000)); //Sets the viewing area size
        
        
        JFrame frame = new JFrame("Simple Graph View");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(vv);
        frame.pack();
        frame.setVisible(true); 
        // TODO code application logic here
        
    }
    
}
