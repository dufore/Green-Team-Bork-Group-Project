package zeitz_borkv3;

/*Event type class called when an item is removed from the game
 *
 * @author Amos Dufore
 * @version BorkGroupV1
 */
class DisappearEvent extends Event {

    private String target;

    /*Constructor for DisappearEvent
	 *
	 * @param t is the target item which is disappearing
     */
    DisappearEvent(String name) {
        this.target = name;
    }

    /*Called when an action is perfromed which requires an item to be removed from gameplay
	 * finds item 
	 * removes item
	 *
	 * returns String informing of item removal
     */
    String onTrigger() {
        Item itemToRemove;
        String disappearString = "";

        try {
            itemToRemove = GameState.instance().getItemFromInventoryNamed(target);
            GameState.instance().removeFromInventory(itemToRemove);
            GameState.instance().getAdventurersCurrentRoom().remove(itemToRemove);
            disappearString = "The " + target + " has disappeared!";
        } catch (Item.NoItemException e) {

        }
        return disappearString;
    }
}
