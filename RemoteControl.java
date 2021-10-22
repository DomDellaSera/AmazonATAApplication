import edu.duke.FileResource;
import java.util.Arrays;
/**
 * This is the class that controls Kiva's actions. Implement the <code>run()</code>
 * method to deliver the pod and avoid the obstacles.
 *
 * This is starter code that may or may not work. You will need to update the code to
 * complete the project.
 */
public class RemoteControl {
    KeyboardResource keyboardResource;

    /**
     * Build a new RemoteControl.
     */
    public RemoteControl() {
        keyboardResource = new KeyboardResource();
    }

    /**
     * The controller that directs Kiva's activity. Prompts the user for the floor map
     * to load, displays the map, and asks the user for the commands for Kiva to execute.
     *
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
        System.out.println(Arrays.toString(KivaCommand.values()));
        System.out.println("Directions that you typed in: " +Arrays.toString(dirArray)+ dirArray.length);
        //String[] kivarray = Arrays.toString(KivaCommand.values());
        for(int i = 0; i< dirArray.length; i++){
            KivaCommand k = null;
        kiva.move(k.valueOf(dirArray[i]));
        }
    }
    
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
