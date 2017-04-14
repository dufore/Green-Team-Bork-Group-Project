package zeitz_borkv3;
/*Event class which is triggered to remove an item and replace it with another
 *
 * @author Amos Dufore
 * @version GroupBorkV1
 */
class TransformEvent extends Event{
	private  Item transform;
	private String remove;
	/*Constructor for TransformEvent
	 *
	 * @param t item which replaces
	 * @param r item which is removed
	 */
	TransformEvent(Item t, String r){
            this.transform = t;
            this.remove = r;
	}

	/*Called when an action is performed which transforms one item into another
	 * remove 'remove' from inventory
	 * add 'transform' to inventory
	 *
	 * @return String message explaining transformation
	 */
	String onTrigger(){
            Item toRemove;
            String transformString = "";
            
            try {
                toRemove = GameState.instance().getItemFromInventoryNamed(this.remove);
                GameState.instance().removeFromInventory(toRemove);
                GameState.instance().getAdventurersCurrentRoom().remove(toRemove);
                GameState.instance().addToInventory(this.transform);
                
                transformString = this.remove + " has transformed into " + this.transform.getPrimaryName();
            } catch (Item.NoItemException e){
                
            }
            return transformString;
	}
}
