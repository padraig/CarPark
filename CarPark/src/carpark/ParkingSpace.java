package carpark;

import java.awt.Color;
import javax.swing.JPanel;

/**
 * Manages an individual parking space.
 * @author B00639511
 */

public class ParkingSpace extends JPanel {
    Car thisCar;
<<<<<<< HEAD

    public ParkingSpace() {
        this.setBackground(Color.GREEN);
    }

    public boolean isOccupied() {
        return (thisCar != null);
    }

    public void addCar(Car newCar) {
        thisCar = newCar;
        this.setBackground(Color.RED);
    }

    public void removeCar() {
=======
    
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
>>>>>>> f7627d62893fe04a9b128bb90477c1db6781b96f
        thisCar = null;
        this.setBackground(Color.GREEN);
    }
}
