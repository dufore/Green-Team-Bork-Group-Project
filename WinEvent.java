package zeitz_borkv3;

/*This is a class of type Event. It is triggered when score exceeds winning score value or the main objective is completed
 *
 * @author Amos Dufore
 * @version BorkGroupV1
 */
class WinEvent extends Event {
    
    String won = "Congratulations, you have won Bork!";
    
    /*Constructor for WinEvent
     */
    WinEvent(String w) {
    }

    /*Called when the Win condition is reached
	 *Congratulation message printed
	 *asks if you wish to continue
	 *
	 * @return String returns continuation/gamefinished message
     */
    @Override
    String onTrigger() {
        return won;
    }
}
