/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cartoguration.a.tron;

import edu.uci.ics.jung.graph.DirectedGraph;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import java.util.ArrayList;

/**
 *
 * @author Luc
 */
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import java.io.IOException;
import static java.lang.Math.abs;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StorySystem {
    private ArrayList<StoryBlock> AvailableBlocks;
    private ArrayList<StoryBlock> UsedBlocks;
    public DirectedGraph<String, String> StoryMap = new DirectedSparseMultigraph<String, String>();
    private String DNA = "";

    public StorySystem(ArrayList<StoryBlock> AvailableBlocks, ArrayList<StoryBlock> UsedBlocks) {
        this.AvailableBlocks = AvailableBlocks;
        this.UsedBlocks = UsedBlocks;
    }
    public StorySystem(ArrayList<StoryBlock> AvailableBlocks) {
        this.AvailableBlocks = AvailableBlocks;
        this.UsedBlocks = new ArrayList<StoryBlock>();
    }
    public StorySystem() {
        this.AvailableBlocks = new ArrayList<StoryBlock>();
        this.UsedBlocks = new ArrayList<StoryBlock>();
    }
    public static StorySystem makeTestHero()
    {
        StorySystem newSS = new StorySystem();
        newSS.AvailableBlocks = HeroJourney.createTestJourney(12);
        return newSS;
    }
    public static StorySystem makeRandomSetBlocks(int numBlocks) throws IOException
    {
        StorySystem newSS = new StorySystem();
        Random rand = new Random();
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        
        for(int n = 0; n < numBlocks; n++)
        {
            String prereq = "";
            //rand.nextInt((max - min) + 1) + min;
            int r_pre = rand.nextInt((3 - 1) + 1) + 1;
            for(int i = 0; i < r_pre; i++)//make the pre_reqs
            {
                String r_Char = chars.charAt(rand.nextInt(chars.length())) + "";
                prereq += r_Char;
                if(i < numBlocks-1)
                     prereq += ",";
            }
            String post = "";
            //rand.nextInt((max - min) + 1) + min;
            int r_post = rand.nextInt((2 - 1) + 1) + 1;
            for(int i = 0; i < r_post; i++) //make the post_reqs
            {
                String r_Char = chars.charAt(rand.nextInt(chars.length())) + "";
                if(!post.contains(r_Char))
                {
                    post += r_Char;
                    if(i < numBlocks-1)
                         post += ",";
                }
                else if(i == 0)
                    i = -1;
                else
                    i--;
            }
            StoryBlock newBlock = new StoryBlock(prereq, null, post,"");
            newSS.AvailableBlocks.add(newBlock);
        }
        return newSS;
        
    }
    public String printAvailable()
    {
        String returnString = "";
        for(StoryBlock temp : AvailableBlocks)
        {
            returnString += temp.printFunctionMaps();
        }
        return returnString; 
    }
    public void buildTestGraph()
    {
        StoryBlock currentNode;
        Random rand = new Random(System.currentTimeMillis());
        int currentNodeAddress = 0; //abs(rand.nextInt()%(AvailableBlocks.size()+1));
        ArrayList<StoryBlock> possibles = grabNewPaths2(10);
        // this random simulates the user selecting one of the blocks
        currentNodeAddress = abs(rand.nextInt()) % (possibles.size());
        currentNode = AvailableBlocks.remove(currentNodeAddress);
        StoryMap.addVertex(currentNode.getBlockID()); 
        DNA += currentNode.printOutput();
        DNA += currentNode.printInput();
       
        
        while(true)
        {
            
            
            ArrayList<StoryBlock> possible = grabNewPaths(10);
            if(possible.isEmpty())
                break;
            // this random simulates the user selecting one of the blocks
            int nextNodeAdress = abs(rand.nextInt()) % (possible.size());
            StoryBlock nextNode = possible.remove(nextNodeAdress);
            AvailableBlocks.remove(nextNode);
            if(!DNA.contains(nextNode.OutputEvents.get(0)))
                {
                try {
                    System.out.println("Dialog: " + currentNode.getDialog());
                    System.out.println("Input: " + currentNode.InputEvents);
                    
                    System.out.println("Output: " + currentNode.OutputEvents + "\n\n");
                } catch (IOException ex) {
                    Logger.getLogger(StorySystem.class.getName()).log(Level.SEVERE, null, ex);
                }
                    StoryMap.addVertex(nextNode.getBlockID()); 
                    DNA += nextNode.printOutput();
                    StoryMap.addEdge(DNA, currentNode.getBlockID(), nextNode.getBlockID());
                    currentNode = nextNode;
                }
            //StoryMap.addEdge(DNA, currentNode.getBlockID(), nextNode.getBlockID());
           
            
            
            
        }
        if(!DNA.contains(currentNode.OutputEvents.get(0)))
        {
            StoryMap.addVertex(currentNode.getBlockID()); 
            DNA += currentNode.printOutput();
        }
        
        
        
    }
    public ArrayList<StoryBlock> grabNewPaths(int num_options)
    {
        ArrayList<StoryBlock> possible = new ArrayList<>();
        //Grab next node
        for(StoryBlock block : AvailableBlocks)
        {
            int index = DNA.lastIndexOf("[(]*[)]");
            if(index<0)
                index = 0;
            if(block.blockConnect(DNA.substring(index)))
                possible.add(block);
            if(possible.size() >= num_options-1)
                break;
        }
        return possible;
    }
    public ArrayList<StoryBlock> grabNewPaths2(int num_options)
    {
        ArrayList<StoryBlock> possible = new ArrayList<>();
        //Grab next node
        for(StoryBlock block : AvailableBlocks)
        {
            int index = DNA.lastIndexOf("[(]*[)]");
            if(index<0)
                index = 0;
            if(block.blockConnect(""))
                possible.add(block);
            if(possible.size() >= num_options-1)
                break;
        }
        return possible;
    }
    
}
