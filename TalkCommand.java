package zeitz_borkv3;

/**
 * TalkCommand class that will extend abstract class Command.
 * Will implement abstract method execute() to return
 * String to player 
 * @author James
 */
public class TalkCommand extends Command{
    private String talk;
    /**
     * Constructor that will construct the TalkCommand object.
     * Takes in a String parameter and sets it to field.
     * @param item
     */
    TalkCommand(String talk){
        
    }
    /**
     * Execute() method that overrides the abstract method execute() in the
     * Command class.  When called, will return a string to the player telling
     * them that they have spoken to the NPC.
     * @return speech
     */
    @Override
    String execute(){
        return null;
    }
}
