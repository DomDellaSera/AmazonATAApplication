
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
	
	


/**
Simple Rotations:
	TestDirections test caller permutes the different rotations to every direction and then checks
	for the correct state.

*/
    void TestDirections(){
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
	
	void testMoveInDirection(){
		KivaMoveTest group2 = new KivaMoveTest();
		group2.testForwardFromUp();

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

		
	}
	void testBounds(){
	    kivMoveTest group4 = KivaMoveTest();
	    testLowerBoundY();
	    testLowerBoundYException();
	    testLowerBoundX();

	   testLowerBoundXException();
	   testUpperBoundYException();
}
}






















