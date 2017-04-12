package zeitz_borkv3;
/*
 * This is an Event class which is triggered when a score is achieved
 *
 * @author Amos Dufore
 * @version BorkGroupV1
 */

class ScoreEvent extends Event{
	private int score;


	/*
	 * Constructor for ScoreEvent
	 *
	 * @param int score takes the amount to be added to the total score
	 */
	ScoreEvent(){
	}

	/* Called when the event is triggered
	 * adds amount to total score
	 *
	 * @return String returns a string containing a score message "Your score increased by 5"
	 */
	String onTrigger(){
	}
}

