import edu.duke.*;
/**
 * Write a description of Kiva here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Kiva {
    Point currentLocation;//DO THISE NEED TO BE INITIALIZED TO SOME NUMBER/VARIABLE???
    FacingDirection directionFacing;
    FloorMap map;
    
    boolean carryingPod;
    boolean successfullyDropped;
    
    Kiva(FloorMap map){
       this.carryingPod = false;
     this.map = map;
     this.currentLocation = map.getInitialKivaLocation();
    }
    Kiva(FloorMap map, Point currentLocation){
        this.carryingPod = false;
        this.map = map;
        //this.currentLocation = currentLocation;
        this.currentLocation = map.getInitialKivaLocation();
        
    
    }
    
    public static void move(KivaCommand command){
    
    }
    
    public boolean isCarryingPod(){
    return carryingPod;
}
public boolean isSuccessfullyDropped(){
    return successfullyDropped;
}

public Point getCurrentLocation(){
return currentLocation;
}
    
}
