/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carpark;

import java.util.LinkedList;

/**
 *
 * @author Padraig
 */
public class ValuableCar extends Car {

    public ValuableCar(String regNum) {
        super(regNum);
    }

    @Override
    public void occupySpace(LinkedList<ParkingSpace> list) {
        int i = findNextEmpty(0, 5, list);

        if (i == -1) {
            i = findNextEmpty(10, 15, list);
        }

        if (i == -1) {
            i = findNextEmpty(5, 10, list);
        }
        list.get(i).addCar(this);
    }
}
