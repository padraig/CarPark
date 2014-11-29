package carpark;

import java.awt.Color;
import javax.swing.JPanel;

/**
 *
 * @author Padraig
 */
@SuppressWarnings("serial")
public class ParkingSpace extends JPanel{
    String value;
    Car thisCar;
    
    public ParkingSpace(){
        this.setBackground(Color.GREEN);
    }
    
    public boolean isOccupied(){
        return (thisCar != null);
    }
    
    public void addCar(Car newCar){
        thisCar = newCar;
        this.setBackground(Color.RED);
    }
    
    public void removeCar(){
        thisCar = null;
        this.setBackground(Color.GREEN);
    }
}
