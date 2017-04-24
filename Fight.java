package zeitz_borkv3;

/*This class is for creating an object to facilitate every fight that occurs during gameplay.
 *
 * @author Amos Dufore
 * @version GroupBorkV1
 */
class Fight {

    private NonPlayerChar NPC;
    private String itemName;
    private boolean myTurn = true;

    //Uses a while loop to continue prompting during a fight and calls printFightMessage()
    Fight(String itemName, String NPCName) throws NonPlayerChar.NoNPCException,
            Item.NoItemException {

        try {
            this.NPC = GameState.instance().getNpcInVicinityNamed(NPCName); //gets the npc
            this.itemName = itemName; //gets the item name 
            Item myWeapon = GameState.instance().getItemFromInventoryNamed(itemName); //turns string into item
            int attackValue = 0; //attack value

            while (NPC.getHealth() != 0 && GameState.instance().getHealth() != 0) {
                if (myTurn) { // if myTurn then player attacks
                    System.out.println("It is your turn.");
                    System.out.println("You currently have " + itemName + " equipped.");
                    if (myWeapon instanceof Weapon) { //checks if item is a weapon
                        attackValue = ((Weapon) myWeapon).getAttack();
                        attack(attackValue); // attacks the npc
                    } else {
                        attackValue = 1;
                        attack(attackValue); // attacks npc
                    }
                    printFightMessage();
                    myTurn = false; // makes myTurn false so the npc attacks next
                    
                } else { //if myTurn is false then the npc attacks
                    attack(NPC.getAttack()); 
                    printFightMessage();
                    myTurn = true; // makes myTurn true so the Player attacks next
                }
            }
        } catch (NonPlayerChar.NoNPCException e) { // if the npc is not in the room
            System.out.println(NPC.getName() + "is not in this room.");
        } catch (Item.NoItemException e) { // if the player tries to attack with item they don't have
            System.out.println("You don't have that!");
        }
    }

    //Uses player and NPC stats and equipment to print the options and health during a fight
    String printFightMessage() {
        if (myTurn) {
            return "Their health" + " is now" + NPC.getHealth() + "\n";
        } else {
            return "Your health is now" + GameState.instance().getHealth() + "\n";
        }
    }

    //called when an attack is launched
    //@param d damage value passed from weapon in fight
    String attack(int d) throws Item.NoItemException {
        Item myWeapon = GameState.instance().getItemFromInventoryNamed(itemName);
        int myAttack = ((Weapon) myWeapon).getAttack();
        int NPCAttack = NPC.getAttack();
        
        if (myTurn) {
            NPC.woundHealth(myAttack);
            return "You have wounded " + NPC.getName() + "!\n";
        } else {
            GameState.instance().woundHealth(NPCAttack);
            return NPC.getName() + " has wounded you!\n";
        }
    }
}
