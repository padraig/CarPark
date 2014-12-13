package carpark;

import java.util.LinkedList;

/**
 * Stores details about an individual car.
 * @author B00639511
 */
public class Car {

    String registrationNumber = "";
<<<<<<< HEAD

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
=======
    boolean isLarge = false;
    boolean isHighValue = false;
    /**
     * Constructor for new car, specifying the car registration number.
     * @param registrationNumber the registration number of the new car 
     */
    public Car(String registrationNumber){
        this.registrationNumber = registrationNumber;
>>>>>>> f7627d62893fe04a9b128bb90477c1db6781b96f
    }
}
