/*
 * This event is triggered when a player attempts to equip an item
 *
 * @author Amos Dufore
 * @version GroupBorkV1
 */

class EquipEvent extends Event {

	String item;

	/*Constructor for EquipEvent
	 *
	 * @param String item	takes the String name of the item to attempt to equip
	 */
	EquipEvent(String item){
	}

	/*
	 * The onTrigger method for EquipEvent
	 * checks for item in inventory
	 * checks if item is equippable
	 * 	adds item to equipped field in GameState
	 * 	updates stat values
	 *
	 * @return String returns a string stating success or failure
	 */
	String onTrigger(){
	}

}

