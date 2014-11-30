package carpark;

/**
 * Stores details about an individual car.
 * @author B00639511
 */
class Car {
    String registrationNumber = "";
    boolean isLarge = false;
    boolean isHighValue = false;
    /**
     * Constructor for new car, specifying the car registration number.
     * @param registrationNumber the registration number of the new car 
     */
    public Car(String registrationNumber){
        this.registrationNumber = registrationNumber;
    }
}
