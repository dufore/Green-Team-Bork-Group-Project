package zeitz_borkv3;

/**
 * HealthCommand class that will extend abstract class Command.
 * Will implement abstract method execute() to return
 * String to player 
 * @author James
 */
public class HealthCommand extends Command{
    /**
     * Constructor that will construct the HealthCommand object.
     * Takes in a String parameter and sets it to field.
     * @param h
     */
    HealthCommand(){
        
    }
    /**
     * Execute() method that overrides the abstract method execute() in the
     * Command class.  When called, will return a string to the player telling
     * them what their current health is.  GameState will be called,
     * but no changes will be made to it.
     * @return health
     */
    @Override
    String execute(){
        int playerHealth = GameState.instance().getHealth();
        String health = "You currently have " + playerHealth + " health.";
        
        return health;
    }
}
