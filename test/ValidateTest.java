import org.junit.*;

import Objetos.*;
import Otros.*;
import PanelCentral.*;

import static org.junit.Assert.*;

public class ValidateTest {
    
    public ValidateTest() {
    }
    public Vehicle car1;
    @BeforeClass
    public static void setUpClass() {
        Vehicle car1 = Vehicle.getInstance();
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        Status.highScore=0;
   }
    
    @After
    public void tearDown() {
        car1.vehiclePosition.x=100;
        car1.vehiclePosition.y=100;
    }

    @Test
    public void CorrectHighScoreTest(){
        assertEquals(Status.highScore, Score.readHighscore());
    }

    @Test(expected = InvalidPositionException.class)
    public void CorrectVehiclePositionXTest()throws Exception{
        car1.vehiclePosition.x=0;
        car1.CorrectVehiclePositionX(); 
    }
    @Test(expected = InvalidPositionException.class)
    public void CorrectVehiclePositionYTest()throws Exception{
        car1.vehiclePosition.y=0;
        car1.CorrectVehiclePositionY(); 
    }
    @Test
    public void MoveUpTest(){
        int i=0;
        
        while(i<10000){
            car1.MoveUp();
            i++;
        }
        assertEquals(20, car1.vehiclePosition.y, 0.01);
    }
    @Test
    public void MoveRightTest(){
        int i=0;
        
        while(i<10000){
            car1.MoveRight();
            i++;
        }
        assertEquals(980, car1.vehiclePosition.x, 0.01);
    }
    @Test
    public void MoveLeftTest(){
        int i=0;
        
        while(i<10000){
            car1.MoveLeft();
            i++;
        }
        assertEquals(20, car1.vehiclePosition.x, 0.01);
    }
    @Test
    public void MoveDownTest(){
        int i=0;
        
        while(i<10000){
            car1.MoveDown();
            i++;
        }
        assertEquals(580, car1.vehiclePosition.y, 0.01);
    }
    
}
