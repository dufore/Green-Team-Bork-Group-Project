
package zeitz_borkv3;

import java.util.List;
import java.util.Arrays;

public class CommandFactory {

    private static CommandFactory theInstance;
    public static List<String> MOVEMENT_COMMANDS = 
        Arrays.asList("n","w","e","s","u","d" );

    public static synchronized CommandFactory instance() {
        if (theInstance == null) {
            theInstance = new CommandFactory();
        }
        return theInstance;
    }

    private CommandFactory() {
    }

    public Command parse(String command) {
	command = command.toLowerCase();    
        String parts[] = command.split(" ");
        String verb = parts[0];
        String noun = parts.length >= 2 ? parts[1] : "";
        if (verb.equals("save")) {
            return new SaveCommand(noun);
        }
        if (verb.equals("take")) {
            return new TakeCommand(noun);
        }
        if (verb.equals("drop")) {
            return new DropCommand(noun);
        }
        if (verb.equals("i") || verb.equals("inventory")) {
            return new InventoryCommand();
        }
        if (MOVEMENT_COMMANDS.contains(verb)) {
            return new MovementCommand(verb);
        }
	if (verb.equals("score")){
            return new ScoreCommand();
	}
	if (verb.equals("health")){
            return new HealthCommand();
	}
        if(verb.equals("talk")){
            return new TalkCommand(GameState.instance().getAdventurersCurrentRoom().getNPCS().get(0));
        }
        if(verb.equals("fight")) {
            return new FightCommand(GameState.instance().getEquipped(), GameState.instance().getAdventurersCurrentRoom().getNPCS().get(0).getName());
        }
        if (parts.length == 2) {
            return new ItemSpecificCommand(verb, noun);
        }
        return new UnknownCommand(command);
    }
}
