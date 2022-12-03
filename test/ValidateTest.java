import Otros.*;
import PanelCentral.*;
import org.junit.*;
import static org.junit.Assert.*;

public class ValidateTest {
    
    public ValidateTest() {
    }
    private Vehicle car1,car2;
    @BeforeClass
    public static void setUpClass() {
        Vehicle car1 = Vehicle.getInstance();
        Vehicle car2 = Vehicle.getInstance();
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        Status.highScore=0;
        car1.vehiclePosition.x=0;
        car2.vehiclePosition.y=0;
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void CorrectHighScoreTest(){
        assertEquals(Status.highScore, Score.readHighscore());
    }

    @Test(expected = InvalidPositionException.class)
    public void CorrectVehiclePositionXTest()throws Exception{
        Vehicle.CorrectVehiclePositionX(); 
    }
    @Test(expected = InvalidPositionException.class)
    public void CorrectVehiclePositionYTest()throws Exception{
        Vehicle.CorrectVehiclePositionY(); 
    }
    
}
