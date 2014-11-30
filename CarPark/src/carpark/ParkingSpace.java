package carpark;

import java.awt.Color;
import javax.swing.JPanel;

/**
 * Manages an individual parking space.
 * @author Padraig
 */
@SuppressWarnings("serial")
public class ParkingSpace extends JPanel{
    String value;
    Car thisCar;
    
    /**
     * ParkingSpace constructor with default background of Green
     */
    public ParkingSpace(){
        this.setBackground(Color.GREEN);
    }
    
    /**
     * Decides if the space is occupied or not.
     * @return is this space occupied?
     */
    public boolean isOccupied(){
        return (thisCar != null);
    }
    
    /**
     * Adds a new car to the parking space
     * @param newCar the car to be added to the space
     */
    public void addCar(Car newCar){
        thisCar = newCar;
        this.setBackground(Color.RED);
    }
    
    /**
     * Removes a car from the space by making <b>thisCar</b> null.
     */
    public void removeCar(){
        thisCar = null;
        this.setBackground(Color.GREEN);
    }
}
