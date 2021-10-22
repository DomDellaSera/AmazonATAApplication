import edu.duke.FileResource;
import java.util.Arrays;
/**
 * This is the class that controls Kiva's actions. It askes for a FloorMap file followed by a sequence of directions for Kiva to advance. It utilizes enums to constrain potential malicious user input
 * 
 *
*
 */
public class RemoteControl {
    KeyboardResource keyboardResource;

    /**
     * Build a new RemoteControl.
     */
    public RemoteControl() {
        /**
         * Keyboard resource prompts the user for an input file
         */
        keyboardResource = new KeyboardResource();
    }

    /**
     * The controller that directs Kiva's activity. Prompts the user for the floor map
     * to load, displays the map, and asks the user for the commands for Kiva to execute.
     *
     *@param inputMap the input map text file that will serve as the Wherehouse map.
     *@throws IllegalArgumentException if user doesn't enter the correct instructions.
     * [Here's the method you'll execute from within BlueJ. It may or may not run successfully
     * as-is, but you'll definitely need to add more to complete the project.]
     */
    public void run() {
        System.out.println("Please select a map file.");
        FileResource fileResource = new FileResource();
        String inputMap = fileResource.asString();
        FloorMap floorMap = new FloorMap(inputMap);
        System.out.println(floorMap);
        System.out.println("Max row num "+floorMap.getMaxRowNum());
	System.out.println("Max col num "+floorMap.getMaxColNum());
	Kiva kiva = new Kiva(floorMap);
	System.out.println("Kiva is located at "+kiva.getCurrentLocation());
	System.out.println("Kiva is facing "+ kiva.getDirectionFacing());

        System.out.println("Please enter the directions for the Kiva Robot to take.");
        String directions = keyboardResource.getLine();
        String[] dirArray = convertToKivaCommands(directions);
        //System.out.println(Arrays.toString(KivaCommand.values()));
        System.out.println("Directions that you typed in: " +Arrays.toString(dirArray)+ dirArray.length);
        //String[] kivarray = Arrays.toString(KivaCommand.values());
        for(int i = 0; i< dirArray.length; i++){
            KivaCommand k = null;
        kiva.move(k.valueOf(dirArray[i]));
        }
        
        if(kiva.successfullyDropped){
        System.out.println("Successfully picked up the pod and dropped it off. Thank you!");
    }else{
        System.out.println("I'm sorry. The Kiva Robot did not pick up the pod then drop it off in the right place.");
    }

    
}
/**
 * convertToKivaCommands is a user input handler that assures user input is correctly formatted and handles string conversion. 
 * @parama keyboard is a raw String of commands 
 * @return String[] keys is a formatted array processed for entry into a KivaCommand
 */
    private String[] convertToKivaCommands(String keyboard){
     String[] keys = new String[keyboard.length()];
     KivaCommand[] commands = KivaCommand.values();
     
     for(int i = 0; i< keys.length; i++){
         keys[i] = keyboard.substring(i,i+1);
         if (!"FLRTD".contains(keys[i])){
            
            throw new java.lang.IllegalArgumentException(keys[i]+" does not correspond to a command!");
        }
         //System.out.println(keys[i]);
         for(int j = 0; j < commands.length; j++){
           
      //  System.out.print(commands[j].getDirectionKey()+" ");
        String cmd =""+ commands[j].getDirectionKey();
       
        //System.out.println("Equal: "+ cmd +" "+keys[i].equals(cmd));
        if(keys[i].equals(cmd)){
        keys[i] = ""+commands[j];
        }
    }
        //keys[i] = keyboard.substring(i,i+1);
    }
     return keys;
     //return Arrays.toString(keys);
    }
}
