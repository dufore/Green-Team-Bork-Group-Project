package zeitz_borkv3;
/*Event type class called when an item is removed from the game
 *
 * @author Amos Dufore
 * @version BorkGroupV1
 */
class DisappearEvent extends Event{
	private Item target;
	/*Constructor for DisappearEvent
	 *
	 * @param t is the target item which is disappearing
	 */
	DisappearEvent(){
	}

	/*Called when an action is perfromed which requires an item to be removed from gameplay
	 * finds item 
	 * removes item
	 *
	 * returns String informing of item removal
	 */
	String onTrigger(){
	}
}
