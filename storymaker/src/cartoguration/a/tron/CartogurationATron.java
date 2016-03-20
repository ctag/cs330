/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cartoguration.a.tron;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author Luc
 */
public class CartogurationATron {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        StorySystem game = StorySystem.makeRandomSetBlocks(50);
        game.buildTestGraph();
        
        System.out.print(game.printAvailable());
        
        System.out.print(game.StoryMap.toString());
        
        
        
        Layout<Integer, String> layout = new CircleLayout(game.StoryMap);
        layout.setSize(new Dimension(300,300)); // sets the initial size of the space
        // The BasicVisualizationServer<V,E> is parameterized by the edge types
        BasicVisualizationServer<Integer,String> vv =
        new BasicVisualizationServer<Integer,String>(layout);
        vv.setPreferredSize(new Dimension(350,350)); //Sets the viewing area size

        JFrame frame = new JFrame("Simple Graph View");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(vv);
        frame.pack();
        frame.setVisible(true); 
        // TODO code application logic here
                
    }
    
}
