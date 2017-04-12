package zeitz_borkv3;
/*The Event class is an abstract class that represents any event that causes a change in the GameState
 *
 *@author Amos Dufore
 *@version GroupBorkV1
 */

abstract class Event{
	
	/*
	 *Executes when the Event is triggered
	 *
         *@return String message related to the event
	 */
	abstract String onTrigger();

}
