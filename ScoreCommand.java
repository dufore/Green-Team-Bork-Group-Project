package zeitz_borkv3;

/**
 * ScoreCommand class that extends abstract class Command.
 * Will use abstract method execute() to return a string
 * to the player.
 * @author James
 */
public class ScoreCommand extends Command{
    int playerScore;
    String playerRank;
     /**
     * Constructor for ScoreCommand class.  Takes in String parameter
     * and will set input value to field.
     * @param score 
     */
    ScoreCommand(){
	    playerScore = GameState.instance().getScore();
	    if(playerScore >= 0 && playerScore < 15){
		    playerRank = "F Class Adventurer";
	    }else if(playerScore >= 15 && playerScore < 30){
		    playerRank = "D Class Adventurer";
	    }else if(playerScore >= 30 && playerScore < 45){
		    playerRank = "C Class Adventurer"
	    }else if(playerScore >= 45 && playerScore < 60){
		    playerRank = "B Class Adventurer";
	    }else if(playerScore >= 60 && playerScore < 90){
		    playerRank = "A Class Adventurer";
	    }else if(playerScore >= 90 && playerScore < 120){
		    playerRank = "S Class Adventurer";
	    }else if(playerScore >= 120 && playerScore < 200){
		    playerRank = "SS Class Adventurer";
	    }else if(playerScore >= 200){
		    playerRank = "SSS Class Adventurer";
	    }

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
	    
        String returnString = "You currently have a score of " + playerScore ". Rank: "+ playerRank;
        
        return returnString;
    }
    
}
