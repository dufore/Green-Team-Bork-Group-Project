package zeitz_borkv3;

/*This class is for creating an object to facilitate every fight that occurs during gameplay.
 *
 * @author Amos Dufore
 * @version GroupBorkV1
 */
class Fight{
	//Uses a while loop to continue prompting during a fight and calls printFightMessage()
	Fight(){
            String equipped = GameState.instance().getEquipped();
            
            int i = 0;
            while(i<1){
                System.out.println("You currently have " + equipped + " equipped.");
                System.out.println();
            }
	}

	//Uses player and NPC stats and equipment to print the options and health during a fight
	 String printFightMessage(){
             
	}

	//called when an attack is launched
	//@param d damage value passed from weapon in fight
	String attack(int d){
	}
}
