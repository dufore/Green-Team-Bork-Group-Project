package zeitz_borkv3;
/*Event class triggered to teleport the player to another room
 *
 * @author Amos Dufore
 * @version GroupBorkV1
 */
class TeleportEvent extends Event{
	private Room target;

	/*Constructor for TeleportEvent
	 *
	 * @param t Room to teleport the player to
	 */
	TeleportEvent(Room t){
            this.target = t;
	}

	/*Called when the action is performed to cause a teleportation to happen
	 *
	 * @return String returns a message about your teleportation
	 */
	String onTrigger(){
            GameState.instance().setAdventurersCurrentRoom(this.target);
            String newRoomString = "You have been teleported to "
                    + this.target.getTitle() + "."
                    + "/n" + this.target.describe() + "\n";
            return newRoomString;
	}
}
