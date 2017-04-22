package zeitz_borkv3;
/*
 * This class is an Event which is triggered when the player attempts to unequip an item
 *
 * @author Amos Dufore
 * @version GroupBorkV1
 */

class UnequipEvent extends Event{
    Item item;
	
	/*
	 * Constructor for UnequipEvent
	 *
	 * @param String item the name of the item to attempt to unequip
	 */
	UnequipEvent(Item item){
            this.item = item;
	}

	/*
	 * Called when the UnequipItem event is triggered
	 * Checks for item in equipped
	 * 	removes item from equipped
	 * 	updates stats
	 *
	 *@return String returns the string of success/failure
	 */
	String onTrigger(){
            String unequipString = GameState.instance().removeItemFromEquip(this.item);
            return unequipString;
	}

}
