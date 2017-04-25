package zeitz_borkv3;

/**
 * HealthCommand class that will extend abstract class Command.
 * Will implement abstract method execute() to return
 * String to player 
 * @author James
 */
public class HealthCommand extends Command{
    int playerHealth;
    String fuzzyString;
	/**
     * Constructor that will construct the HealthCommand object.
     * Takes in a String parameter and sets it to field.
     * @param h
     */
    HealthCommand(){
        playerHealth = GameState.instance().getHealth();
	if(playerHealth == 100){
		fuzzyString = "You are the embodiment of health \n";
	} else if (playerHealth >= 75 && playerHealth < 100){
		fuzzyString = "You've taken a small amount of damage \n";
	} else if (playerHealth >= 50 && playerHealth < 75){
		fuzzyString = "You're feeling a bit worse for the wear \n";
	} else if (playerHealth >= 25 && playerHealth < 50){
		fuzzyString = "You can no longer walk straight, every movement is painful \n";
	} else if (playerHealth >= 1 && playerHealth < 25){
		fuzzyString = "Standing is nearly impossible. Your vision is going red with the pain \n";
	} else if (playerHealth == 0){
		fuzzyString = "You are dead \n";
	}

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
        return fuzzyString;
    }
}
