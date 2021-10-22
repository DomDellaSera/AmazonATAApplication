import java.util.Arrays;
/**
 * KivaCommand isses commands to Kiva instances. 
 * 
 * KivaCommand provides an interface between the user input and robot command, only allowing for a finite set of options.
 * 
 * @author Amazon 
 * @version (a version number or a date)
 */
public enum KivaCommand {
FORWARD ('F'),
TURN_LEFT ('L'),
TURN_RIGHT ('R'),
TAKE ('T'),
DROP ('D');

    private char directionKey;
    
    KivaCommand(char DirectionKey){
        this.directionKey = DirectionKey;

    }
    
    public char getDirectionKey(){
    return directionKey;
}
}

