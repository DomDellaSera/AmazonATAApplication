import edu.duke.*;
import java.util.HashMap;
import java.util.ArrayList;
/**
 * Kiva simulates the movement of a robot by providing a small list of options for movement. 
 * 
 * Its main function is the move command which requires the input of a KivaCommand enum option. 
 * <h3>Implementation Details</h3>
 * <h4>Move Method</h4>
 * <p>The move command is mirrored by the KivaCommand with a similar switch statement for the five commands (FORWARD, TURN_LEFT,TURN_RIGHT,TAKE,DROP),</p>
 * <p>each has their own helper statement.</p>
 * <h5>The FORWARD Command</h5>
 * <p>The forward command is the main command that executes a planed movement. The turn command changes this plan and the TAKE and DROP commands coordinate with FORWARD's implementation in order function correctly.</p>
 * <p> Thus FORWARD alone applies it's methods in both space and time, whereas the other methods are applied locally. </p>
 * 
 * <h5>TURN</h5>
 * <p>The turn commands are implemented as two index lookups on arrays. The Left/Right turn commands access the index for their respective arrays, add one to determine the successor, computes mod 4. 
 *  The array lookup is equivalent   to choosing a complex number and its conjugate(e.g. x+yi, x-yi) and multiplying either by 
 * i for rotation in the respective direction. This construction can be extended to Quaternions easily for use in 3D rotations. Unfortunately, without direct VPN access I realized too late how the FacingDirection enum was supposed to be implemented.</p>
 * @see java.lang.Object.KivaCommand
 * 
 * @author Dominic Della Sera  
 * @version 0.1
 */
public class Kiva {
    private Point currentLocation;
    private Point nextLocation;
    /**
     * FloorMap map describes the area of activity, along with it's attributes.
     */
    FloorMap map;
    
    /**
     * Direction facing is the main controller of orientation. 
     * 
     *Its possible values are UP, LEFT, RIGHT, DOWN.
     */
    public FacingDirection directionFacing; 
    /**
     * carryingPod alerts the user of pod related alert statuses as well as successful delivery of pods.
     */
    boolean carryingPod = false;
    /**
     * successfullyDropped alerts the user upon successful input. Continuation of input will skip the corresponding message in RemoteControl.
     */
    boolean successfullyDropped = false;
    private HashMap<FacingDirection, Integer> directionIndex = new HashMap();
    //boolean throwNextForward;
    private Point upperBound;
    private Point lowerBound;
    public long MotorLifetime;
    /**
     * This is the array index for rotating left
     */
    final private ArrayList<FacingDirection> fdIndex= new ArrayList<FacingDirection>();
    /**
     * This is the array index for rotating right
     */
    final private ArrayList<FacingDirection> fdRightIndex = new ArrayList<FacingDirection>();
    


    
    private String errorMessage;
    /**
     *  constructGroupsMod4
     */
    private void constructGroupsMod4(){
        //We can construct a rotation primitive   by specifying 2 groups, mod 4
        // the operation o on (G,o) is a group if (G,o):
        //i) the operation o is associative on G
        //ii) THere is an identity element e in G for o.
        //iii) Every x in G gas an inverse in G
        
  
       /*
        |e a b c
       e|e,a,b,c
       a|a,b,c,e
       b|b,c,e,a
       c|c,e,a,b
       
       To find the inverse we take the column, we ask what returns our identity, e. a o c = e, Thus the inverse of a is c. 
        
       fdIndex.indexOf(directionFacing) % 4; Returns 1 +if we want to
       fdRightIndex.indexOf(directionFacing) +1 ) % 4;
       */
      
      /*
       * To invert this group we can flip it and rotate it.
       * 

     
           Origin: 
           
           0
          1 3
           2 / Flip
            / 3
             2 0
              1
             
  Rotate
           0
          3 1
           2 

           


         */
   
        fdIndex.add(FacingDirection.UP);
        fdIndex.add(FacingDirection.LEFT);
        fdIndex.add(FacingDirection.DOWN);
        fdIndex.add(FacingDirection.RIGHT);
        
        fdRightIndex.add(FacingDirection.UP);
        fdRightIndex.add(FacingDirection.RIGHT);
        fdRightIndex.add(FacingDirection.DOWN);
        fdRightIndex.add(FacingDirection.LEFT);
        //fdRightIndex.add(FacingDirection.UP);
    }
   //FlooObject terrain =  map.getObjectAtLocation(
    
    Kiva(FloorMap map){

     this.map = map;
     this.currentLocation = map.getInitialKivaLocation();
     this.directionFacing = FacingDirection.UP;
     this.MotorLifetime = 0;
     constructGroupsMod4();
     
    }
    public Point getPodLocation(){return map.getPodLocation();}
    Kiva(FloorMap map, Point currentLocation){

        this.map = map;
        this.currentLocation = currentLocation;
        this.directionFacing = FacingDirection.UP;
        this.MotorLifetime = 0;
        constructGroupsMod4();
        
        
    
    }

    private  void getMinNums(){
    System.out.println("GetMaxColNum "+map.getMaxColNum());
    System.out.println("GetMinColNum "+map.getMinColNum());
    System.out.println("GetMaxRowNum "+map.getMaxRowNum());
    System.out.println("GetMinRowNum "+map.getMinRowNum());
    }
    
    private int rotateLeftGroup(){
        FacingDirection dir = getDirectionFacing();
        return (fdIndex.indexOf(dir)+1)%4;
    }
    
    /**
     * setNextLocation is the mover forward helper location. While the next location is technically the same as the location as directionFacing it is difficult to reason about
     * the same physical point in terms of two points of time simultaneously. FacingDirection directionFacing imples a future point whereas setNextLocation treats the same point in terms 
     * of the present.
     * 
     */
    private void setNextLocation(){
        int x = currentLocation.getX();
        int y = currentLocation.getY();

        
            switch (directionFacing) {
            case UP:
            y-=1;
            checkLowerBounds(y,map.getMinRowNum());
            break;  
            case DOWN:
            y += 1;
            checkUpperBounds(y,map.getMaxRowNum());
            break;          
            case LEFT:
            x -=1;
            checkLowerBounds(x,map.getMinColNum());
            break;          
            case RIGHT:
            x+=1;
            checkUpperBounds(x,map.getMaxColNum());
            break;
                }
        nextLocation = new Point(x,y);
        FloorMapObject nextObj = map.getObjectAtLocation(getNextLocation());
        
   switch(nextObj){
        case OBSTACLE:
        throw new IllegalMoveException(String.format("Kiva at %s is trying to run into a(n) %s at %s", getCurrentLocation(),nextObj, nextLocation ));

        case POD:
        if(isCarryingPod() == true){
         throw new IllegalMoveException(String.format("Kiva is holding a POD at %s while trying to move onto a second POD at %s",getCurrentLocation(), nextLocation));
        }
         break;
    }
        
        

        
        
    }
    
    private void checkUpperBounds(int nextMove,int bound) {
        
    
        if(nextMove > bound){
          throw new IllegalMoveException(String.format("Attempted move %s to %s is beyond upper bound %s", getDirectionFacing(), nextMove, getCurrentLocation()));

    }
  
}
    
    private void checkLowerBounds(int nextMove, int bound){

    if(nextMove< bound){
       throw new IllegalMoveException(String.format("Attempted move %s to %s is beyond upper bound %s", getDirectionFacing(), nextMove, getCurrentLocation()));

    }

}





    /**
     * getNextLocation is the Point on the grid where FORWARD command will move to
     * @return a Point of the next locaiton
     */
    public  Point getNextLocation(){

        return nextLocation;
    }
/**
 *  Returns the direction Kiva is facing.
 *  @return A FacingDirecion enum. Can be UP, DOWN, LEFT, RIGHT.
 */
  public FacingDirection getDirectionFacing(){
      return directionFacing;
   }

    public void setDirectionFacing(FacingDirection newDir){
        directionFacing = newDir;
    }
    
    private boolean sameLocation(Point a, Point b) {
        return a.getX() == b.getX() && a.getY() == b.getY();
    }

      
     
    private void turnLeft(){
        /*Rotate in the direction of +1
           0
          1 3
           2 
           
           */
        int curDirIdx = (fdIndex.indexOf(directionFacing) + 1) % 4;
        setDirectionFacing(fdIndex.get(curDirIdx));    
    }
    private void turnRight(){
        /*              Rotate in the the direction of +1
              0
             3 1
              2
*/
     int curDirIdx = (fdRightIndex.indexOf(directionFacing) + 1 ) % 4;
        setDirectionFacing(fdRightIndex.get(curDirIdx));

    }
    private void takePod(){
        Point currentKivaLocation = getCurrentLocation();
        Point podLocation = map.getPodLocation();
        if(currentKivaLocation.getX() == podLocation.getX() && currentKivaLocation.getY() == podLocation.getY())
        {
            carryingPod = true;
        }
        else {
            throw new NoPodException(String.format("Cannot take pod from %s. Pod is at %s :)", getCurrentLocation(), getPodLocation()));
    }
        
    }

    private void dropPod(){
     
       if(isCarryingPod() == true){
           if(sameLocation(getCurrentLocation(),getDropZoneLocation()) ){
                carryingPod = false;
                successfullyDropped = true;
            }
           
            
            else if(!sameLocation(getCurrentLocation(),getDropZoneLocation())) {
            throw new IllegalDropZoneException(String.format("%s is not the correct drop zone. %s is.", getCurrentLocation(), getDropZoneLocation()));
        }
    }
           else if (!isCarryingPod() && sameLocation(getCurrentLocation(),getDropZoneLocation())){
            throw new IllegalMoveException(String.format("Correct location drop zone %s, but the pod was left behind at %s", getCurrentLocation(), getPodCurrentLocation()));
        }
        else if(!isCarryingPod() && !sameLocation(getCurrentLocation(),getDropZoneLocation())){
            throw new NoPodException("Kiva is not currently carrying the pod");
        }
    }
        
    
    

    

    /**
     * move {@code move} is the main command which creates Robot action
     * 
     * @param KivaCommand {@link KivaCommand} enum datatype allows only a fixed set of constants from user input.
     * @exception IllegalMoveException is thrown Keva attempts to
     * <ul>
     * <li>move off the map</li>
     * <li>DROP at the drop zone with no POD</li>
     * <li>FORWARD into an obstacle</li>
     * <li>move onto a POD space while already holding a pod</li>
     * </ul>
 
     * @throws NoPodException is thrown under two conditions:
     * <ul>
     * <li>DROP: Kiva attempts drop in empty space without holding a POD</li>
     * <li>TAKE: Kiva attempts TAKE while not standing over a POD</li>
     * </ul>
     * 
     * @see KivaCommand
     */
    public void move(KivaCommand command){
        successfullyDropped = false;
        switch (command) {
            case FORWARD:

            moveForward();
            incrementMotorLifetime();
            break;
            case TURN_LEFT:
            turnLeft();
            incrementMotorLifetime();
         
            break;
            case TURN_RIGHT:
            turnRight();
            incrementMotorLifetime();
            break;
            case TAKE:
            takePod();
            incrementMotorLifetime();
            break;
            case DROP:
            dropPod();
            break;
               
               
            }
 }   
    private void moveForward(){
        setNextLocation(); //THIS DOESN'T MOVE THE ROBOT BECAUSE WE HAVE NOT CHANGE CURRENT LOCATION
        
    currentLocation = nextLocation;



    }
    

    
    public boolean isCarryingPod(){
    return carryingPod;
}
public Point getCurrentLocation(){
        
    return currentLocation;
    }
    public boolean isSuccessfullyDropped(){
        return successfullyDropped;
    }  
    
      
    public Point getDropZoneLocation(){return map.getDropZoneLocation();}
    public Point getPodCurrentLocation(){return map.getPodLocation();}

    public void printMap(){
    System.out.println(map.toString());}
    
    public long getMotorLifetime(){return MotorLifetime;}
    private void incrementMotorLifetime(){MotorLifetime+=1000;}
}
