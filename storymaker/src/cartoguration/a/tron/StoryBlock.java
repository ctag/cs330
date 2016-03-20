package cartoguration.a.tron;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

/*
 *
 */

/**
 *
 * @author Luc
 */
public class StoryBlock {

    public StoryBlock(ArrayList<String> InputEvents, ArrayList<String> InputEventsModifiers, ArrayList<String> OutputEvents) {
        this.InputEvents = InputEvents;
        this.InputEventsModifiers = InputEventsModifiers;
        this.OutputEvents = OutputEvents;
        this.BlockID = makeID();
    }
    public StoryBlock(String InputEvents,String InputEventsModifiers, String OutputEvents) {
        this.InputEvents = new ArrayList<String>(Arrays.asList(InputEvents.split(",")));
        if(InputEventsModifiers != null)
            this.InputEventsModifiers = new ArrayList<String>(Arrays.asList(InputEventsModifiers.split(","))); 
        this.OutputEvents = new ArrayList<String>(Arrays.asList(OutputEvents.split(",")));
        this.BlockID = makeID();
    }
    public String printFunctionMaps()
    {
        return BlockID + ": " + InputEvents.toString() + " -> " + OutputEvents.toString() + "\n";
    }
    public String printInput()
    {
        String input=""; 
        for(String ele : InputEvents)
        {
            input += ele; 
        }
        
        return input;
        
    }
    public String printOutput()
    {
        String output="";
        for(String ele : OutputEvents)
        {
            output += ele; 
        }
        return output; 
        
    }
     public boolean blockConnect(String dna)
    {
        for(String check : InputEvents)
        {
            if(!dna.contains(check))
            {
                return false;
            }
        }
        return true;
    }
    public boolean equals(StoryBlock obj) {
        if (obj == null) return false;
       return (BlockID.equals(obj.getBlockID()));
    }
    
    
    

    
    /*
    List of elements that are required for character to gain entry into this block
        These will tend to be more concrete boolean variables. 
    List of desired story elements the the character has
        These will tend to be ranges, i.e. Fame from 0->1 
    List of elements that will affect the story state. 
    Story dialog for the console
    Language of describing dialog to the console. . 
    */
    public ArrayList<String> InputEvents;
    public ArrayList<String> InputEventsModifiers;
    public ArrayList<String> OutputEvents;
    private String BlockID;

    public ArrayList<String> getInputEvents() {
        return InputEvents;
    }

    public void setInputEvents(ArrayList<String> InputEvents) {
        this.InputEvents = InputEvents;
    }

    public ArrayList<String> getInputEventsModifiers() {
        return InputEventsModifiers;
    }

    public void setInputEventsModifiers(ArrayList<String> InputEventsModifiers) {
        this.InputEventsModifiers = InputEventsModifiers;
    }

    public ArrayList<String> getOutputEvents() {
        return OutputEvents;
    }

    public void setOutputEvents(ArrayList<String> OutputEvents) {
        this.OutputEvents = OutputEvents;
    }
    

    public void setBlockID(String BlockID) {
        this.BlockID = BlockID;
    }

    public String getBlockID() {
        return BlockID;
    }
    public String makeID()
    {
        return UUID.randomUUID().toString().split("-")[0];
    }
    
    
}
