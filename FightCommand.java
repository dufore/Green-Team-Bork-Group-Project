package zeitz_borkv3;

/**
 * FightCommand class that will extend abstract class Command.
 * Will implement abstract method execute() to return
 * String to player 
 * @author James
 */
public class FightCommand extends Command{
    private String fight;
    /**
     * Constructor that will construct the FightCommand object.
     * Takes in a String parameter and sets it to field.
     * @param f 
     */
    FightCommand(String f){
        
    }
    /**
     * Execute() method that overrides the abstract method execute() in the
     * Command class.  When called, will return a string to the player telling
     * them that a fight will begin.
     * @return fight
     */
    @Override
    String execute(){
        return null;
    }
    
}
