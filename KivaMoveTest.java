
/**
 * Write a description of KivaMoveTest here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.Point;

public class KivaMoveTest {
    // Define the FloorMap we'll use for all the tests
    String defaultLayout = ""
                           + "-------------\n"
                           + "        P   *\n"
                           + "   **       *\n"
                           + "   **       *\n"
                           + "  K       D *\n"
                           + " * * * * * **\n"
                           + "-------------\n";
                           

                 String podLayout = ""
                           + "-------------\n"
                           + "        P   *\n"
                           + "   **    P  *\n"
                           + "   **       *\n"
                           + "  K       D *\n"
                           + " * * * * * **\n"
                           + "-------------\n";
                           
    FloorMap defaultMap = new FloorMap(defaultLayout);
    //FloorMap podMap = new FloorMap(podLayout);

    public void testForwardFromUp() {
        // GIVEN
        // A Kiva built with the default map we defined earlier
        Kiva kiva = new Kiva(defaultMap);

        // WHEN
        // We move one space forward
        kiva.move(KivaCommand.FORWARD);
        
        // THEN
        // The Kiva has moved one space up
        verifyKivaState("testForwardFromUp", 
            kiva, new Point(2, 3), FacingDirection.UP, false, false);
    }
    
    // For you: create all the other tests and call verifyKivaState() for each

    private boolean sameLocation(Point a, Point b) {
        return a.getX() == b.getX() && a.getY() == b.getY();
    }

    public void testTurnLeftFromUP(){
            Kiva kiva = new Kiva(defaultMap);
            kiva.move(KivaCommand.TURN_LEFT);
            verifyKivaState("testLeftTurnFromUp", 
            kiva, new Point(2, 4), FacingDirection.LEFT, false, false);
    }
    public void testTurnLeftFromLEFT(){
            Kiva kiva = new Kiva(defaultMap);
            kiva.move(KivaCommand.TURN_LEFT);
            kiva.move(KivaCommand.TURN_LEFT);
            verifyKivaState("testLeftTurnFromLeft", 
            kiva, new Point(2, 4), FacingDirection.DOWN, false, false);
        }
            public void testTurnLeftFromDOWN(){
            Kiva kiva = new Kiva(defaultMap);
            kiva.move(KivaCommand.TURN_LEFT);
            kiva.move(KivaCommand.TURN_LEFT);
            kiva.move(KivaCommand.TURN_LEFT);
            verifyKivaState("testLeftTurnFromDown", 
            kiva, new Point(2, 4), FacingDirection.RIGHT, false, false);
        }
         public void testTurnLeftFromRIGHT(){
            Kiva kiva = new Kiva(defaultMap);
            kiva.move(KivaCommand.TURN_LEFT);
            kiva.move(KivaCommand.TURN_LEFT);
            kiva.move(KivaCommand.TURN_LEFT);
            kiva.move(KivaCommand.TURN_LEFT);
            verifyKivaState("testLeftTurnFromrIGHT", 
            kiva, new Point(2, 4), FacingDirection.UP, false, false);
        }
        public void testTurnRightFromUP(){
            Kiva kiva = new Kiva(defaultMap);
            kiva.move(KivaCommand.TURN_RIGHT);
            verifyKivaState("testRightTurnFromUp", 
            kiva, new Point(2, 4), FacingDirection.RIGHT, false, false);
        }
                public void testTurnRightFromRight(){
            Kiva kiva = new Kiva(defaultMap);
            kiva.move(KivaCommand.TURN_RIGHT);
                        kiva.move(KivaCommand.TURN_RIGHT);
            verifyKivaState("testRightTurnFromRight", 
            kiva, new Point(2, 4), FacingDirection.DOWN, false, false);
        }
        public void testTurnRightFromDown(){
            Kiva kiva = new Kiva(defaultMap);
            kiva.move(KivaCommand.TURN_LEFT);
            kiva.move(KivaCommand.TURN_LEFT);
            kiva.move(KivaCommand.TURN_RIGHT);
            verifyKivaState("testRightTurnFromDown", 
            kiva, new Point(2, 4), FacingDirection.LEFT, false, false);
        }
                public void testTurnRightFromLeft(){
            Kiva kiva = new Kiva(defaultMap);
            kiva.move(KivaCommand.TURN_LEFT);
            kiva.move(KivaCommand.TURN_RIGHT);
            System.out.println("ONLY TWO DIRS HERE");
                                                   
                                                                                              
            verifyKivaState("testRightTurnFromLeft", 
            kiva, new Point(2, 4), FacingDirection.UP, false, false);
        }
    public void testForwardWhileFacingLeft(){
        Kiva kiva = new Kiva(defaultMap);
        kiva.move(KivaCommand.TURN_LEFT);
        kiva.move(KivaCommand.FORWARD);
        verifyKivaState("testForwardMoveFromRightDirection", kiva, new Point(1,4), FacingDirection.LEFT, false,false);
    }
    
    public void testForwardWhileFacingRight(){
        Kiva kiva = new Kiva(defaultMap);
        kiva.move(KivaCommand.TURN_RIGHT);
        kiva.move(KivaCommand.FORWARD);
        verifyKivaState("testForwardMoveFromRightDirection", kiva, new Point(3,4), FacingDirection.RIGHT, false,false);
    }
    public void testForwardWhileFacingDown(){
        Kiva kiva = new Kiva(defaultMap);
        kiva.move(KivaCommand.TURN_RIGHT);
        kiva.move(KivaCommand.TURN_RIGHT);
        kiva.move(KivaCommand.FORWARD);
        verifyKivaState("testForwardWhileFacingDowwn", kiva, new Point(2,5), FacingDirection.DOWN, false, false);
    }
    public void testTurnRightThenLeft(){
    
     Kiva kiva = new Kiva(defaultMap);
        kiva.move(KivaCommand.TURN_RIGHT);
        kiva.move(KivaCommand.TURN_RIGHT);
        kiva.move(KivaCommand.TURN_LEFT);

        verifyKivaState("testTurnRightThenLeft", kiva, new Point(2,4), FacingDirection.RIGHT, false, false);
    }
     public void testManyRightOneLeft(){
    
     Kiva kiva = new Kiva(defaultMap);
        kiva.move(KivaCommand.TURN_RIGHT);
        kiva.move(KivaCommand.TURN_RIGHT);
        
        kiva.move(KivaCommand.TURN_RIGHT);
        kiva.move(KivaCommand.TURN_RIGHT);
        kiva.move(KivaCommand.TURN_RIGHT);
        kiva.move(KivaCommand.TURN_LEFT);

        verifyKivaState("testManyRightThenLeft", kiva, new Point(2,4), FacingDirection.UP, false, false);
    }
    public void testTakeOnPod(){
        //Input: Create a Kiva object using the default map, and call move() to go up three times, turn right, move right six times, and take the pod. Verify the state​ of the Kiva
        Kiva kiva = new Kiva(defaultMap);
        kiva.move(KivaCommand.FORWARD);
        kiva.move(KivaCommand.FORWARD);
        kiva.move(KivaCommand.FORWARD);
        kiva.move(KivaCommand.TURN_RIGHT);
        kiva.move(KivaCommand.FORWARD);
        kiva.move(KivaCommand.FORWARD);
        kiva.move(KivaCommand.FORWARD);
        kiva.move(KivaCommand.FORWARD);
        kiva.move(KivaCommand.FORWARD);
        kiva.move(KivaCommand.FORWARD);
        kiva.getPodLocation();
        //kiva.printKivaLocation();
        verifyKivaState("testTakeOnPod; Not carrying", kiva, new Point(8,1), FacingDirection.RIGHT, false, false);
        kiva.move(KivaCommand.TAKE);
        verifyKivaState("testTakeOnPodPost; Carrying", kiva, new Point(8,1), FacingDirection.RIGHT, true, false);
        

    }
    public void getMetadata(){
    Kiva kiva = new Kiva(defaultMap);
    //kiva.getMinNums();
}
    public void testDropOnDropZone(){
     //Create a Kiva object using the default map, and call move() to go up three times, turn right, move right six times, 
     //take the pod, 
     //move to the drop zone, and drop the pod. 
     //Verify the state​ of the Kiva object. Ensure that it is not carrying the pod, and the drop was successful.
     Kiva kiva = new Kiva(defaultMap);
     //kiva.printKivaLocation();
     kiva.move(KivaCommand.FORWARD);

     kiva.move(KivaCommand.FORWARD);
     kiva.move(KivaCommand.FORWARD);

     kiva.move(KivaCommand.TURN_RIGHT);

     kiva.move(KivaCommand.FORWARD);
     kiva.move(KivaCommand.FORWARD);

     kiva.move(KivaCommand.FORWARD);
     kiva.move(KivaCommand.FORWARD);

     kiva.move(KivaCommand.FORWARD);
     kiva.move(KivaCommand.FORWARD);

    // kiva.printKivaLocation();
     kiva.move(KivaCommand.TAKE);

        
     kiva.move(KivaCommand.TURN_RIGHT);
     kiva.move(KivaCommand.FORWARD);

     kiva.move(KivaCommand.FORWARD);
     kiva.move(KivaCommand.FORWARD);
  
     kiva.move(KivaCommand.TURN_LEFT);
     kiva.move(KivaCommand.FORWARD);
     kiva.move(KivaCommand.FORWARD);
     
     //kiva.printKivaLocation();
     //kiva.printDropZoneLocation();
     kiva.move(KivaCommand.DROP);
     verifyKivaState("testDroppedZoneException", kiva, new Point(10, 4), FacingDirection.RIGHT, false, true);
     }
     
     
    
    //}
    public void testDropOnDropZoneException(){
        Kiva kiva = new Kiva(defaultMap);
        kiva.move(KivaCommand.FORWARD);
        kiva.move(KivaCommand.TAKE);
        
        // THEN
        // The Kiva has moved one space up


     kiva.move(KivaCommand.FORWARD);
     kiva.move(KivaCommand.FORWARD);
     kiva.move(KivaCommand.FORWARD);
     kiva.move(KivaCommand.TURN_RIGHT);
     kiva.move(KivaCommand.FORWARD);
     kiva.move(KivaCommand.FORWARD);
     kiva.move(KivaCommand.FORWARD);
     kiva.move(KivaCommand.FORWARD);
     kiva.move(KivaCommand.FORWARD);
     //kiva.move(KivaCommand.FORWARD);
     try{
         //kiva.printKivaLocation();
        }
catch(NoPodException noPod) {
System.out.println("Successful Test");
}
}
public void testIllegalDropZoneException(){
Kiva kiva = new Kiva(defaultMap);

    // kiva.printKivaLocation();
     kiva.move(KivaCommand.FORWARD);
     kiva.move(KivaCommand.FORWARD);
     kiva.move(KivaCommand.FORWARD);
     kiva.move(KivaCommand.TURN_RIGHT);
     kiva.move(KivaCommand.FORWARD);
     kiva.move(KivaCommand.FORWARD);
     kiva.move(KivaCommand.FORWARD);
     kiva.move(KivaCommand.FORWARD);
     kiva.move(KivaCommand.FORWARD);
     kiva.move(KivaCommand.FORWARD);
     //kiva.printKivaLocation();
     kiva.move(KivaCommand.TAKE);
        
     kiva.move(KivaCommand.TURN_RIGHT);
     kiva.move(KivaCommand.FORWARD);
     kiva.move(KivaCommand.FORWARD);
     kiva.move(KivaCommand.FORWARD);
     kiva.move(KivaCommand.TURN_LEFT);
     kiva.move(KivaCommand.FORWARD);
     //kiva.move(KivaCommand.FORWARD);


     kiva.move(KivaCommand.DROP);


    }

//testBoundsTests(){
public void testLowerBoundY(){
      Kiva kiva = new Kiva(defaultMap);

      kiva.move(KivaCommand.FORWARD);
      kiva.move(KivaCommand.FORWARD);
      kiva.move(KivaCommand.FORWARD);
      kiva.move(KivaCommand.FORWARD);
      verifyKivaState("testLowerBoundY", kiva, new Point(2,0), FacingDirection.UP,false,false);
      //verifyNextThrow("testLowerBoundY", kiva, true);
}
//TEst lower bout exceptions are old
public void testLowerBoundYException(){
      Kiva kiva = new Kiva(defaultMap);

      kiva.move(KivaCommand.FORWARD);
      kiva.move(KivaCommand.FORWARD);
      kiva.move(KivaCommand.FORWARD);
      kiva.move(KivaCommand.FORWARD);
      try{
      kiva.move(KivaCommand.FORWARD);
    }
    catch (IllegalMoveException e){
    System.out.println("Error thrown: " + e);
}
}

public void testLowerBoundX(){

    String testName = "LowerBoundX";
      Kiva kiva = new Kiva(defaultMap);

      kiva.move(KivaCommand.TURN_LEFT);
      kiva.move(KivaCommand.FORWARD);
      kiva.move(KivaCommand.FORWARD);

      verifyKivaState("test"+testName, kiva, new Point(0,4), FacingDirection.LEFT,false,false);
      //verifyNextThrow("test"+testName, kiva, true);
}   
public void testLowerBoundXException(){

    String testName = "LowerBoundXException";
      Kiva kiva = new Kiva(defaultMap);

      kiva.move(KivaCommand.TURN_LEFT);
      kiva.move(KivaCommand.FORWARD);
      kiva.move(KivaCommand.FORWARD);
      kiva.move(KivaCommand.FORWARD);

// try{
  //    kiva.move(KivaCommand.FORWARD);
 //   }
//    catch (InvalidFloorMapLocationException e){
//    System.out.println("Error thrown: " + e);
//}
}
public void testMoveOutOfBounds() {
        Kiva kiva = new Kiva(defaultMap);
        kiva.move(KivaCommand.FORWARD);
        kiva.move(KivaCommand.TURN_LEFT);
        kiva.move(KivaCommand.FORWARD);
        kiva.move(KivaCommand.FORWARD);
        System.out.println("testMoveOutOfBounds: (expect an IllegalMoveException)");
        kiva.move(KivaCommand.FORWARD);
        
        // This only runs if no exception was thrown
        System.out.println("testMoveOutOfBounds FAIL!");
        System.out.println("Moved outside the FloorMap!");
    }




void upperBoundTestRand(){

    String testName = "UpperBoundY";
      Kiva kiva = new Kiva(defaultMap);
      //kiva.db = true;
      kiva.move(KivaCommand.TURN_LEFT);
      kiva.move(KivaCommand.TURN_LEFT);
      kiva.move(KivaCommand.FORWARD);
      kiva.move(KivaCommand.FORWARD);

            verifyKivaState("test"+testName, kiva, new Point(2,6), FacingDirection.DOWN,false,false);
      //verifyNextThrow("test"+testName, kiva, true);
    }
public void testUpperBoundYException(){

    String testName = "UpperBoundYException";
      Kiva kiva = new Kiva(defaultMap);

      kiva.move(KivaCommand.TURN_LEFT);
      kiva.move(KivaCommand.TURN_LEFT);
      kiva.move(KivaCommand.FORWARD);
      kiva.move(KivaCommand.FORWARD);


 try{
      kiva.move(KivaCommand.FORWARD);
    }
    catch (IllegalMoveException e){
    System.out.println("Error thrown: " + e);
}
}
public void testUpperBoundX(){

    String testName = "UpperBoundX";
      Kiva kiva = new Kiva(defaultMap);

      kiva.move(KivaCommand.TURN_RIGHT);
      kiva.move(KivaCommand.FORWARD);      
      kiva.move(KivaCommand.FORWARD);
      kiva.move(KivaCommand.FORWARD);
      kiva.move(KivaCommand.FORWARD);
      kiva.move(KivaCommand.FORWARD);
      kiva.move(KivaCommand.FORWARD);
      kiva.move(KivaCommand.FORWARD);
      kiva.move(KivaCommand.FORWARD);
      kiva.move(KivaCommand.FORWARD);
      kiva.move(KivaCommand.FORWARD);
            

            verifyKivaState("test"+testName, kiva, new Point(12,6), FacingDirection.DOWN,false,false);
      //verifyNextThrow("test"+testName, kiva, true);
    }
    
public void testUpperBoundXException(){
    String testName = "UpperBoundXException";
      Kiva kiva = new Kiva(defaultMap);

      kiva.move(KivaCommand.TURN_RIGHT);
      kiva.move(KivaCommand.FORWARD);      
      kiva.move(KivaCommand.FORWARD);
      kiva.move(KivaCommand.FORWARD);
      kiva.move(KivaCommand.FORWARD);
      kiva.move(KivaCommand.FORWARD);
      kiva.move(KivaCommand.FORWARD);
      kiva.move(KivaCommand.FORWARD);
      kiva.move(KivaCommand.FORWARD);
      kiva.move(KivaCommand.FORWARD);
      kiva.move(KivaCommand.FORWARD);
            

        //    verifyKivaState("test"+testName, kiva, new Point(2,12), FacingDirection.DOWN,false,false);
      //verifyNextThrow("test"+testName, kiva, true);

 try{
      kiva.move(KivaCommand.FORWARD);
    }
    catch (IllegalMoveException e){
    System.out.println("Error thrown: " + e);
}
}  
//}
/*public void kivaTestClearance(){
    String testName = "UpperBoundXException";
      Kiva kiva = new Kiva(defaultMap);
      kiva.db = true;
      kiva.move(KivaCommand.FORWARD);
      kiva.move(KivaCommand.TURN_RIGHT);
      kiva.checkFloorClearance();
      kiva.move(KivaCommand.TURN_LEFT);
      kiva.move(KivaCommand.FORWARD);
      kiva.move(KivaCommand.TURN_RIGHT);
      kiva.checkFloorClearance();

}
  
*/
 public void testObstacleThrow(){
            Kiva kiva = new Kiva(defaultMap);
             kiva.move(KivaCommand.FORWARD);
             kiva.move(KivaCommand.TURN_RIGHT);
              kiva.move(KivaCommand.FORWARD);
              System.out.println("testObstacleThrow: (expect an IllegalMoveException)");
        kiva.move(KivaCommand.FORWARD);
        System.out.println("testMoveOutOfBounds FAIL!");
        System.out.println("Moved crashed into an obstacle");
            
        
        }
public void testPodCollision(){
        Kiva kiva = new Kiva(defaultMap);
        kiva.move(KivaCommand.FORWARD);
        kiva.move(KivaCommand.FORWARD);
        kiva.move(KivaCommand.FORWARD);
        kiva.move(KivaCommand.TURN_RIGHT);
        kiva.move(KivaCommand.FORWARD);
        kiva.move(KivaCommand.FORWARD);
        kiva.move(KivaCommand.FORWARD);
        kiva.move(KivaCommand.FORWARD);
        kiva.move(KivaCommand.FORWARD);
        kiva.move(KivaCommand.FORWARD);
        kiva.move(KivaCommand.TURN_RIGHT);
        kiva.move(KivaCommand.FORWARD);
        }
    /*private void verifyNextThrow(
            String testName,
            Kiva actual,
            boolean expectThrow){
            
          //  boolean throwNextStatus = actual.doesMoveForwardEqualThrow();
            if(throwNextStatus == expectThrow){
                 System.out.println(
                    String.format("%s: exception throw onForward SUCCESS", testName));
            }
            else{
              System.out.println(
                    String.format("%s: Exception throw onForward FAIL!", testName));}
            }*/

            /**
             * Verifies the current keva state
             */
    private void verifyKivaState(
            String testName,
            Kiva actual,
            Point expectLocation,
            FacingDirection expectDirection,
            boolean expectCarry,
            boolean expectDropped) {

        Point actualLocation = actual.getCurrentLocation();
        if (sameLocation(actualLocation, expectLocation)) {
            System.out.println(
                    String.format("%s: current location SUCCESS", testName));
        }
        else {
            System.out.println(
                    String.format("%s: current location FAIL!", testName));
            System.out.println(
                    String.format("Expected %s, got %s",
                            expectLocation, actualLocation));
        }

        FacingDirection actualDirection = actual.getDirectionFacing();
        if (actualDirection == expectDirection) {
            System.out.println(
                    String.format("%s: facing direction SUCCESS", testName));
        }
        else {
            System.out.println(
                    String.format("%s: facing direction FAIL!", testName));
            System.out.println(
                    String.format("Expected %s, got %s",
                            expectDirection, actualDirection));
        }

        boolean actualCarry = actual.isCarryingPod();
        if (actualCarry == expectCarry) {
            System.out.println(
                    String.format("%s: carrying pod SUCCESS", testName));
        }
        else {
            System.out.println(
                    String.format("%s: carrying pod FAIL!", testName));
            System.out.println(
                    String.format("Expected %s, got %s",
                            expectCarry, actualCarry));
        }

        boolean actualDropped = actual.isSuccessfullyDropped();
        if (actualDropped == expectDropped) {
            System.out.println(
                    String.format("%s: successfully dropped SUCCESS", testName));
        }
        else {
            System.out.println(
                    String.format("%s: successfully dropped FAIL!", testName));
            System.out.println(
                    String.format("Expected %s, got %s",
                            expectDropped, actualDropped));
        }
    }
}