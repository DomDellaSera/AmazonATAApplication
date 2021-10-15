import edu.duke.*;
import java.util.HashMap;
import java.util.ArrayList;
/**
 * Write a description of Kiva here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Kiva {
    private Point currentLocation;//DO THISE NEED TO BE INITIALIZED TO SOME NUMBER/VARIABLE???
    private Point nextLocation;
    FloorMap map;
    
    FacingDirection directionFacing; //= FacingDirection.UP;
    boolean carryingPod = false;
    boolean successfullyDropped = false;
    private HashMap<FacingDirection, Integer> directionIndex = new HashMap();
    
    private Point upperBound;
    private Point lowerBound;
    ArrayList<FacingDirection> fdIndex= new ArrayList<FacingDirection>();
    ArrayList<FacingDirection> fdRightIndex = new ArrayList<FacingDirection>();
    
    boolean throwNextForward;
    boolean db = false;
    
    private String errorMessage;
    
    private void constructGroupsMod4(){
        //We can construct a rotation premitive by secifying 2 groups, mod 4
        // the operation o on (G,o) is a group if (G,o):
        //i) the operation o is associative on G
        //ii) THere is an identity element e in G for o.
        //iii) Every x in G gas an inverse in G
        
        // The logarithm function is an example of a function that doesn't form a group. It doesn't have an inverse in many sets of numbers,
        //and this may be why the discrete logarithm is difficult.
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
    Kiva(FloorMap map){


     this.map = map;
     this.currentLocation = map.getInitialKivaLocation();
     System.out.println(currentLocation);
     directionFacing = FacingDirection.UP;

     setNextLocation();
     constructGroupsMod4();
     
    }
    public void getPodLocation(){
    System.out.println("Pod Location: "+ map.getPodLocation());}
    Kiva(FloorMap map, Point currentLocation){
        //this.carryingPod = false;
        this.map = map;
        //this.currentLocation = currentLocation;    
        this.currentLocation = map.getInitialKivaLocation();
        setNextLocation();
        constructGroupsMod4();
        
    
    }
    //private void setBounds(){
    //upperBound = new Point(map.getMaxRowNum()+1,map.getMaxColNum()+1);
    //lowerBound = new Point(-1,-1);
    //}
    public void getMinNums(){
    System.out.println("GetMaxColNum "+map.getMaxColNum());
    System.out.println("GetMinColNum "+map.getMinColNum());
    System.out.println("GetMaxRowNum "+map.getMaxRowNum());
    System.out.println("GetMinRowNum "+map.getMinRowNum());
    }
    
    int rotateLeftGroup(){
        FacingDirection dir = getDirectionFacing();
        return (fdIndex.indexOf(dir)+1)%4;
    }
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
        System.out.println("Facing Direction " + getDirectionFacing());
        System.out.println("Next Location " + getNextLocation());
        
    }
    private void checkUpperBounds(int nextMove,int bound) {
        if(db == true){
        System.out.println("Next move "+ nextMove + " upper Bound: " +bound );
    }
        if(nextMove > bound){
        setThrowNextMove(true);
        setErrorMessage("Exceeded Upper Bound: moved to " + nextMove + " outside of "+ bound );
    }
    else{setThrowNextMove(false);
    }
}
    
    private void checkLowerBounds(int nextMove, int bound){
    if(db == true){
        System.out.println("Next move "+ nextMove + "lower Bound: " +bound );}
    if(nextMove< bound){
    setThrowNextMove(true);
    setErrorMessage("Exceeded Lower Bound: moved to " + nextMove + " outside of "+ bound );
    }
    else{
        setThrowNextMove(false);
    }
}

public void checkFloorClearance(){
    Point nextSquare = getNextLocation();
    FloorMapObject nextObj = map.getObjectAtLocation(nextSquare);
    System.out.print(nextObj.toChar());
}
    
    private void setThrowNextMove(boolean throwNextForward){
        this.throwNextForward = throwNextForward;
        
    }
    public boolean doesMoveForwardEqualThrow(){
    return throwNextForward;
}

private void setErrorMessage(String message){
 errorMessage = message;
}
public String getErrorMessage(){
return errorMessage;
}
    public Point getNextLocation(){
    //public String getNextLocation(){
        //return nextLocation.toString();
        return nextLocation;
    }
    public FacingDirection getDirectionFacing(){
        return directionFacing;
    }

    public void setDirectionFacing(FacingDirection newDir){
        directionFacing = newDir;
    }
    private boolean sameLocation(Point a, Point b) {
        return a.getX() == b.getX() && a.getY() == b.getY();
    }
    //These turn turnLeft and turn right operations are laid out in this way because I'm really trying to make two separate cayley tabels
    //so that don't have to use negative numbers. Or more technically I want 2 groups over the set of Natural Numbers(N) instead of 1 over Integers(Z). 
    
    /*
     * 
     * +|e 1 2 3
      {1{2,3,4,e},
       2{3,4,1,2},
       3{4,1,2,3},
       4{1,2,3,4}}
       
       -|1 2 3 4
      {1{4,3,2,1},
       2{1,4,3,2},
       3{2,1,4,3},
       4{3,2,1,4}}
       
       It is intersting to note that this pattern also holds with the complex number i (square root of negative 1) multiplied by itself
        * |i^1 i^2 i^3 i^4
      {i^1{-1,-i,1,i},
       i^2{-i,1,i,-1},
       i^3{1,i,-1,-i},
       i^4{-i,-1,i,1}}
       */
      
     
    private void turnLeft(){
        /*Rotate in the direction of +1
           0
          1 3
           2 
           
           */
        int curDirIdx = (fdIndex.indexOf(directionFacing) + 1) % 4;
        ///curDirIdx= (curDirIdx +1)%4;
        //System.out.println("Turnning l, new direction index: "+ curDirIdx);
        setDirectionFacing(fdIndex.get(curDirIdx));
        setNextLocation();
        
    }
    private void turnRight(){
        /*              Rotate in the the direction of +1
              0
             3 1
              2
*/
    int curDirIdx = (fdRightIndex.indexOf(directionFacing) + 1 ) % 4;
    
   // System.out.println("Turnningr Right, next direction index: "+ curDirIdx);
        ///curDirIdx= (curDirIdx +1)%4;
        setDirectionFacing(fdRightIndex.get(curDirIdx));
        setNextLocation();
    }
    private void takePod(){
        Point currentKivaLocation = getCurrentLocation();
        Point podLocation = map.getPodLocation();
        if(currentKivaLocation.getX() == podLocation.getX() && currentKivaLocation.getY() == podLocation.getY())
        {
            carryingPod = true;
        }
        else throw new NoPodException("Not standing over pod");
        
    }

    private void dropPod(){
        

        
       if(isCarryingPod() == true){
           if(sameLocation(getCurrentLocation(),getDropZoneLocation())){
                carryingPod = false;
                successfullyDropped = true;
            }
            else {
            throw new IllegalDropZoneException("Incorrect drop zone");
        
        }
    }
        
    
    }

    

    public void move(KivaCommand command){
        switch (command) {
            case FORWARD:
           // if(nextLocation > Upperbound || nextLocation > LowerBound){
           // }
           
           if (throwNextForward==true){
            throw  new IllegalMoveException(getErrorMessage()); }
            moveForward();
            break;
            case TURN_LEFT:
            turnLeft();
           // setDirectionFacing;
            break;
            case TURN_RIGHT:
            turnRight();
            break;
            case TAKE:
            takePod();
            break;
            case DROP:
            dropPod();
            break;
               /**
               FORWARD ('F'),
TURN_LEFT ('L'),
TURN_RIGHT ('R'),
TAKE ('T'),
DROP
*/
                 
        

    }
}
    
    private void moveForward(){
    
        
       // if(nextLocatio
        
    currentLocation = nextLocation;
    
    setNextLocation();
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
    public void printKivaLocation(){
        System.out.println("The current kiva location is: "+ getCurrentLocation());
    }
        
    
    public Point getDropZoneLocation(){
        return map.getDropZoneLocation();
    }
    public void printDropZoneLocation(){
         System.out.println("The current Drop Zone location is: "+ getDropZoneLocation());
    }
    public void printMap(){
    System.out.println(map.toString());}
    
    
}
