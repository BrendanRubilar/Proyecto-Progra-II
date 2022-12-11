
package Objetos;

import static Objetos.Vehicle.Velocity;
import static Objetos.Vehicle.giro;
import PanelCentral.*;
import java.awt.Graphics;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Otros.InvalidPositionException;

import static org.junit.Assert.*;

public class VehicleTest {
    public Vehicle car1;
    public VehicleTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        car1 = Vehicle.getInstance(); 
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getInstance method, of class Vehicle.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        boolean expResult = true ;
        boolean result=false;
        Vehicle instance = Vehicle.getInstance();
        if(instance!=null){
            result = true;
        }
        assertEquals(expResult, result);
        
    }

    /**
     * Test of MoveUp method, of class Vehicle.
     */
    @Test
    public void testMoveUp() {
        System.out.println("MoveUp");
        for(int i=0;i<1000;i++){
            car1.MoveUp();
        }
        assertEquals(20,car1.vehiclePosition.y);
    }

    /**
     * Test of MoveRight method, of class Vehicle.
     */
    @Test
    public void testMoveRight() {
        System.out.println("MoveRight");
        for(int i=0;i<1000;i++){
            car1.MoveRight();
        }
        assertEquals(980,car1.vehiclePosition.x);
       
    }

    /**
     * Test of MoveLeft method, of class Vehicle.
     */
    @Test
    public void testMoveLeft() {
        System.out.println("MoveLeft");
        for(int i=0;i<1000;i++){
            car1.MoveLeft();
        }
        assertEquals(20,car1.vehiclePosition.x);
    }

    /**
     * Test of MoveDown method, of class Vehicle.
     */
    @Test
    public void testMoveDown() {
        System.out.println("MoveDown");
        for(int i=0;i<1000;i++){
            car1.MoveDown();
        }
        assertEquals(580,car1.vehiclePosition.y);
    }

    /**
     * Test of setSize method, of class Vehicle.
     */
    @Test
    public void testSetSize() {
        System.out.println("setSize");
        int H = 200;
        int W = 100;
        Vehicle instance = Vehicle.getInstance();
        instance.setSize(H, W);
        assertEquals(200, instance.getSizeH());
        assertEquals(100, instance.getSizeW());
    }

    /**
     * Test of CorrectVehiclePositionX method, of class Vehicle.
     */
    @Test(expected = InvalidPositionException.class)
    public void testCorrectVehiclePositionX() throws Exception {
        System.out.println("CorrectVehiclePositionX");
        car1.vehiclePosition.x=0;
        car1.CorrectVehiclePositionX();
        
    }

    /**
     * Test of CorrectVehiclePositionY method, of class Vehicle.
     */
    @Test(expected = InvalidPositionException.class)
    public void testCorrectVehiclePositionY() throws Exception {
        System.out.println("CorrectVehiclePositionY");
        car1.vehiclePosition.y=0;
        car1.CorrectVehiclePositionY();
    }
    
}
