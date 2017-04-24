package zeitz_borkv3;

/*This class is for creating an object to facilitate every fight that occurs during gameplay.
 *
 * @author Amos Dufore
 * @version GroupBorkV1
 */
class Fight {

    private Weapon weapon;
    private Shield shield;
    private Armor armor;
    private NonPlayerChar NPC;
    private boolean myTurn = true;

    //Uses a while loop to continue prompting during a fight and calls printFightMessage()
    Fight(Weapon w, Shield s, Armor a, NonPlayerChar n) {
        this.weapon = w;
        this.shield = s;
        this.armor = a;
        this.NPC = n;

        String equipped = GameState.instance().getEquipped();

        while (NPC.getHealth() != 0 || GameState.instance().getHealth() != 0) {
            if (myTurn) {
                System.out.println("It is your turn.");
                System.out.println("You currently have " + equipped + " equipped.");
                if (equipped.equals(weapon.getClass())) {
                    attack(weapon.getAttack());
                    printFightMessage();
                } else {
                    System.out.println("You are defending!");
                }
                printFightMessage();
                myTurn = false;
            } else {
                attack(NPC.getAttack());
                printFightMessage();
                myTurn = true;
            }
        }
    }

    //Uses player and NPC stats and equipment to print the options and health during a fight
    String printFightMessage() {
        if (myTurn) {
            return "Their health" + " is now" + NPC.getHealth();
        } else {
            return "Your health is now" + GameState.instance().getHealth();
        }
    }

    //called when an attack is launched
    //@param d damage value passed from weapon in fight
    String attack(int d) {
        int myAttack = weapon.getAttack();
        int NPCAttack = NPC.getAttack();
            
        if (myTurn) {
            NPC.woundHealth(myAttack);
            return "You have wounded " + NPC.getName() + "!";
        } else {
            GameState.instance().woundHealth(NPCAttack);
            return NPC.getName() + " has wounded you!";
        }
    }
}
