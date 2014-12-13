/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carpark;

import java.util.LinkedList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Padraig
 */
public class LargeCar extends Car {

    public LargeCar(String regNum) {
        super(regNum);
    }

    @Override
    public void occupySpace(LinkedList<ParkingSpace> list) {
        for (int i = 5; i <= 9; i++) {
            if (!list.get(i).isOccupied()) {
                list.get(i).addCar(this);
                break;
            }
            JOptionPane.showMessageDialog(new JFrame(), "No Suitable Space for Large Vehicles.");
        }
    }
}
