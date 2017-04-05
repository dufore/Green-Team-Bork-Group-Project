/*This is an Event class which is triggered when an NPC or Player's health <= 0
 *
 * @author Amos Dufore
 * @version BorkGroupV1
 */
class DieEvent extends Event{
	private NPC target;
	/*Constructor for DieEvent
	 *
	 * @param t target (null for player)
	 */
	DieEvent(NPC t){
	}

	/*Called when the event is triggered
	 * Remove NPC from room
	 * 
	 * @return String appropriate death message (player/NPC)
	 */
	String onTrigger(){
	}
}
