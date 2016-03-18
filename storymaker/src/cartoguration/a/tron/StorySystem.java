/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cartoguration.a.tron;

import java.util.ArrayList;

/**
 *
 * @author Luc
 */
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import java.util.Random;

public class StorySystem {
    private ArrayList<StoryBlock> AvailableBlocks;
    private ArrayList<StoryBlock> UsedBlocks;
    public Graph<Integer, String> StoryMap = new SparseMultigraph<Integer, String>();

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
    public static StorySystem makeRandomSetBlocks(int numBlocks)
    {
        StorySystem newSS = new StorySystem();
        Random rand = new Random();
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        
        for(int n = 0; n < numBlocks; n++)
        {
            String prereq = "";
            //rand.nextInt((max - min) + 1) + min;
            int r_pre = rand.nextInt((3 - 1) + 1) + 1;
            for(int i = 0; i < r_pre; i++)
            {
                String r_Char = chars.charAt(rand.nextInt(chars.length())) + "";
                prereq += r_Char;
                if(i < numBlocks-1)
                     prereq += ",";
            }
            String post = "";
            //rand.nextInt((max - min) + 1) + min;
            int r_post = rand.nextInt((2 - 1) + 1) + 1;
            for(int i = 0; i < r_post; i++)
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
            StoryBlock newBlock = new StoryBlock(prereq, null, post);
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
    
}
