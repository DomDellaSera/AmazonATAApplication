
/**
 * Write a description of TestDeployer here.
 *
 * @author (Dominic Della Sera)
 * @version (a version number or a date)
 */

/**
 *
 * The intention here is to separate the testing interface from its implementation.
 *
 */
public class TestDeployer {



        String Layout1 =  "--------------\n"
                           + "         P   *\n"
                           + "    **       *\n"
                           + "    **       *\n"
                           + "   K       D *\n"
                           + "  * * * * * **\n"
                           + "-------------\n";




/**
Simple Rotations:
    TestDirections test caller permutes the different rotations to every direction and then checks
    for the correct state.

*/
void testDirections(){

        KivaMoveTest group1 = new KivaMoveTest();
        group1.testTurnLeftFromUP();
        group1.testTurnLeftFromLEFT();
        group1.testTurnLeftFromDOWN();
        group1.testTurnLeftFromRIGHT();
        group1.testTurnRightFromUP();
        group1.testTurnRightFromRight();
        group1.testTurnRightFromDown();
        group1.testTurnRightFromLeft();
    }

    /**
    Basic Movement Tests

    This set of tests assesses basic robot functionality. Each test turns a direction
    followed by moving foward.


    */
    //KivaMoveTest(){
    void testMoveInDirection(){
        KivaMoveTest group2 = new KivaMoveTest();
       // group2.setFloorMap(Layout1);

        group2.testForwardWhileFacingLeft();
        group2.testForwardWhileFacingRight();
        group2.testForwardWhileFacingDown();
        group2. testForwardFromUp();

    }

    void randomExtremeTests(){

    KivaMoveTest group3 = new KivaMoveTest();
    group3.testTurnRightThenLeft();
    }

    void testRobotUtilities(){

        KivaMoveTest group4 = new KivaMoveTest();
        group4.testTakeOnPod();
        group4.testDropOnDropZone();


    //}

    //kiva k = new k
}
    void testBounds(){
         KivaMoveTest group4 = new KivaMoveTest();
       //group4.setFloorMap(layout1);
        //group4.setFloorMap();

        group4.testLowerBoundY();
        group4.testLowerBoundYException();
        group4.testLowerBoundX();

       group4.testLowerBoundXException();
       group4.testUpperBoundYException();
}
}
