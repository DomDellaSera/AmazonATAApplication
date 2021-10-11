
/**
 * Write a description of KivaCommand here.
 * 
 * @author (your name) 
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

