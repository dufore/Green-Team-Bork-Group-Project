package zeitz_borkv3;

/**
 * ScoreCommand class that extends abstract class Command.
 * Will use abstract method execute() to return a string
 * to the player.
 * @author James
 */
public class ScoreCommand extends Command{
    /**
     * Constructor for ScoreCommand class.  Takes in String parameter
     * and will set input value to field.
     * @param score 
     */
    ScoreCommand(){
    }
    /**
     * Execute() method that extends abstract method Execute().
     * Will return string to player describing their score.
     * When called, will call gamestate to get the score then
     * return the score to the player.  No changes will be made
     * to gamestate.
     * @return score
     */
    @Override
    public String execute(){
        int playerScore = GameState.instance().getScore();
        String returnString = "You currently have a score of " + playerScore;
        
        return returnString;
    }
    
}
