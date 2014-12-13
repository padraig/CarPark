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
public class Car {

    String registrationNumber = "";

    public Car(String regNum) {
        this.registrationNumber = regNum;
    }

    public void occupySpace(LinkedList<ParkingSpace> list) {

        int i = findNextEmpty(10, 15, list);

        if (i == -1) {
            i = findNextEmpty(0, 5, list);
        }

        if (i == -1) {
            i = findNextEmpty(5, 10, list);
        }

        list.get(i).addCar(this);
    }

    protected int findNextEmpty(int start, int end, LinkedList<ParkingSpace> list) {
        int i;

        for (i = start; i < end; i++) {
            if (!list.get(start).isOccupied()) {
                i = start;
                break;
            } else {
                i++;
                while (list.get(i).isOccupied()) {
                    i++;
                }
                break;
            }
        }

        if (i == end) {
            return -1;
        } else {
            return i;
        }
    }
}
