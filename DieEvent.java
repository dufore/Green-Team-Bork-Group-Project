package zeitz_borkv3;

/*This is an Event class which is triggered when an NPC or Player's health <= 0
 *
 * @author Amos Dufore
 * @version BorkGroupV1
 */
class DieEvent extends Event {

    // private NonPlayerChar target;
    String diedString;

    /*Constructor for DieEvent
	 *
	 * @param t target (null for player)
     */
    DieEvent() {
        if (diedString.startsWith("You")) {
            System.exit(0);
        }
    }

    // DieEvent(NonPlayerChar t) {
    //   String died = onTrigger();
    // if (died.equals("You have died! Game Over!")) {
    //  System.exit(0);
    // } else {
    //   GameState.instance().getAdventurersCurrentRoom().getNPCS().remove(t);
    //  GameState.instance().getDungeon().getNPCS().remove(t);
    //}
    //}

    /*Called when the event is triggered
	 * Remove NPC from room
	 * 
	 * @return String appropriate death message (player/NPC)
     */
    @Override
    String onTrigger() {
        if (GameState.instance().getHealth() <= 0) {
            diedString = "You have died! Game Over!";
        } else {
            diedString = ("The NPC has died!");
        }
        return diedString;
    }
}
