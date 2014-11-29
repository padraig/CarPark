/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carpark;

/**
 *
 * @author Padraig
 */
class Car {
    String registrationNumber = "";
    boolean isLarge = false;
    boolean isHighValue = false;
    
    public Car(String registrationNumber){
        this.registrationNumber = registrationNumber;
    }
}
