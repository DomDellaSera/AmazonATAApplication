import edu.duke.Point; 

public class KivaConstructorTest { 
    String defaultLayout = "" 
                            + "-------------\n" 
                            + "        P   *\n"
                            + "   **       *\n"
                            + "   **       *\n"
                            + "  K       D *\n"
                            + " * * * * * **\n"
                            + "-------------\n";

    FloorMap defaultMap = new FloorMap(defaultLayout);

    public void testSingleArgumentConstructor() { 
        // GIVEN 
        // The default map we defined earlier 

        // WHEN 
        // We create a Kiva with the single-argument constructor         
        Kiva kiva = new Kiva(defaultMap); 

        // THEN 
        // The initial Kiva location is (2, 4) 
        Point initialLocation = kiva.getCurrentLocation();
        System.out.println("Initial Location"+initialLocation.toString());
        System.out.println("Next location "+ kiva.getNextLocation());
        Point expectedLocation = new Point(2, 4);
        if (sameLocation(initialLocation, expectedLocation)) { 
            System.out.println("testSingleArgumentConstructor SUCCESS"); 
        } else { 
            System.out.println(String.format( "testSingleArgumentConstructor FAIL: %s != (2,4)!", initialLocation)); 
        } 
    } 

    private boolean sameLocation(Point a, Point b) { 
        return a.getX() == b.getX() && a.getY() == b.getY(); 
    }
    
    

    // For you: create a test for the constructor taking two arguments. 
    
    public void testTwoArgumentConstructor(){
    //GIVEN
    // The defauly map and a parameter point
    Point testPoint = new Point(5,6);
    Kiva kiva = new Kiva(defaultMap, testPoint);
    
    Point initialLocation = kiva.getCurrentLocation();
    Point expectedLocation = new Point(5,6);
    if (sameLocation(initialLocation, expectedLocation)) { 
            System.out.println("testDoubleArgumentConstructor SUCCESS"); 
        } else { 
            System.out.println(String.format( "testDoubleArgumentConstructor FAIL: %s != (2,4)!", initialLocation)); 
        } 
    }
}