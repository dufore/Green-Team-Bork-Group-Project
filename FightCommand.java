package zeitz_borkv3;

/**
 * FightCommand class that will extend abstract class Command. Will implement
 * abstract method execute() to return String to player
 *
 * @author James
 */
public class FightCommand extends Command {

    private String myWeaponName;
    private String npcName;
    private boolean myTurn = true;

    /**
     * Constructor that will construct the FightCommand object. Takes in a
     * String parameter and sets it to field.
     *
     * @param f
     */
    FightCommand(String item, String npc) {
        this.myWeaponName = item;
        this.npcName = npc;
    }

    /**
     * Execute() method that overrides the abstract method execute() in the
     * Command class. When called, will return a string to the player telling
     * them that a fight will begin.
     *
     * @return fight
     */
    @Override
    String execute() {
        try {
            int attackValue = 0;
            Item myWeapon = GameState.instance().getItemFromInventoryNamed(myWeaponName);
            NonPlayerChar npc = GameState.instance().getNpcInVicinityNamed(npcName);
            int npcAttackValue = npc.getAttack();

            while (npc.getHealth() > 0 && GameState.instance().getHealth() > 0) {
                if (myTurn) { // if myTurn then player attacks
                    System.out.println("It is your turn.");
                    System.out.println("You currently have " + myWeapon + " equipped.");

                    if (myWeapon.getEvent().equals("attack")) { //checks if item has an attack event
                        attackValue = myWeapon.getEventNum(); // makes the attack value the eventNum
                        // attacks the npc
                    } else {
                        attackValue = 1;
                    }
                    npc.woundHealth(attackValue);
                    System.out.println("You dealt " + attackValue + " damage!");
                    myTurn = false; // makes myTurn false so the npc attacks next

                } else { //if myTurn is false then the npc attacks
                    GameState.instance().woundHealth(npcAttackValue);
                    System.out.println("You took " + npcAttackValue + " damage!");
                    myTurn = true; // makes myTurn true so the Player attacks next
                }
            }

            if (GameState.instance().getHealth() <= 0) {
                GameState.instance().die();
            }
            
            if (npc.getHealth() <= 0) {
                System.out.println("You have killed " + npcName + "!");
                GameState.instance().removeNPC(npc);
            }

        } catch (NonPlayerChar.NoNPCException ex) {
            return npcName + " is not here!";
        } catch (Item.NoItemException ex) {
        }
        return null;
    }

}
