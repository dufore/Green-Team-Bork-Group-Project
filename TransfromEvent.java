package zeitz_borkv3;
/*Event class which is triggered to remove an item and replace it with another
 *
 * @author Amos Dufore
 * @version GroupBorkV1
 */
class TransformEvent extends Event{
	private Item transform;
	private Item remove;
	/*Constructor for TransformEvent
	 *
	 * @param t item which replaces
	 * @param r item which is removed
	 */
	TransformEvent(Item t, Item r){
	}

	/*Called when an action is performed which transforms one item into another
	 * remove 'remove' from inventory
	 * add 'transform' to inventory
	 *
	 * @return String message explaining transformation
	 */
	String onTrigger(){
	}
}
