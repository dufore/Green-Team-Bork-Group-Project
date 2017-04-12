package zeitz_borkv3;
/*This is an Event class which is triggered when damage is taken/given during combat
 *
 * @author Amos Dufore
 * @version GroupBorkV1
 */
class WoundEvent extends Event{
	private int damage;
	private NonPlayerChar target;
	/*Constructor for WoundEvent
	 *
	 *@param t target for damage (NPC or null for the player)
	 *@param d damage value for wound
	 */
	WoundEvent(NonPlayerChar t,int d){
	}

	/*Called when the event is triggered
	 *Subtracts damage value from target
	 *
	 *@return String returns a String damage occured message "'targetName' takes 'damage' damage"
	 */
	String onTrigger(){
	}
}
