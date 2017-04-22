package zeitz_borkv3;

/**
 * GiveCommand class that will extend abstract class Command.
 * Will implement abstract method execute() to return
 * String to player 
 * @author James
 */
public class GiveCommand extends Command{
    private Item item;
    /**
     * Constructor that will construct the GiveCommand object.
     * Takes in a String parameter and sets it to field.
     * @param item
     */
    GiveCommand(Item item){
        this.item = item;
    }
    /**
     * Execute() method that overrides the abstract method execute() in the
     * Command class.  When called, will return a string to the player telling
     * them that what item was given to NPC or that they do not have that item
     * in their inventory.  GameState called and may be updated depending on
     * if the player has item in inventory or not.
     * @return givenItem
     */
    @Override
    String execute(){
        String giveString;
        if(GameState.instance().getInventoryNames().contains(this.item.getPrimaryName())){
            GameState.instance().removeFromInventory(this.item);
            giveString = "You have given " + this.item.getPrimaryName();
        } else {
            giveString = "You do not have " + this.item.getPrimaryName() + " to give";
        }
        return giveString;
    }
}
