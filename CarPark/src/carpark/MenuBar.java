package carpark;

import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import org.pushingpixels.trident.Timeline;
import javax.swing.*;
import org.pushingpixels.trident.Timeline.RepeatBehavior;

/**
 * Manages add, remove and search functions
 *
 * @author Padraig
 */
public class MenuBar extends JPanel {

    /**
     * Class constructor
     * @param list list of parking spaces to be analyzed
     */
    public MenuBar(LinkedList<ParkingSpace> list) {
        this.setLayout(new GridLayout(0, 6));

        final JTextField regBox = new JTextField();
        final JButton addCarButton = new JButton("Add Car");

        final JButton searchCarButton = new JButton("Find Car");
        searchCarButton.setEnabled(false);

        final JButton removeCarButton = new JButton("Remove Car");
        removeCarButton.setEnabled(false);

        final JRadioButton carValue = new JRadioButton("High Value");
        final JRadioButton carSize = new JRadioButton("Large Car");

        /**
         * Searches list for a matching car and identifies it to the user if
         * found by flashing between blue and red
         */
        ActionListener FindCarListener = (ActionEvent e) -> {
            int i = getIndex(regBox.getText(), list);

            if (i != -1) {
                final Timeline timeline = new Timeline(list.get(i));
                timeline.addPropertyToInterpolate("background", list.get(i).getBackground(), Color.BLUE);
                timeline.setDuration(1000);
                timeline.playLoop(2, RepeatBehavior.REVERSE);
                removeCarButton.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(new JFrame(), "Car not found");
            }
        };

        /**
         * Analyzes list for a suitable parking space to place a new car and
         * inserts it if possible.
         */
        ActionListener InsertCarListener = (ActionEvent e) -> {
            Car newCar = new Car(regBox.getText());
            int i = 0;

            /**
             * If the car already exists, the user is alerted to this.
             */
            if (!isDuplicate(regBox.getText(), list)) {
                try {

                    if (carValue.isSelected()) {
                        newCar.isHighValue = true;
                    }

                    if (carSize.isSelected()) {
                        newCar.isLarge = true;
                    }

                    /**
                     * If a large car has been entered, it will be placed in one
                     * of bays 6-10 only. If none of these spaces are empty the
                     * car will be rejected.
                     *
                     * If the car is high value, it will first attempt to be
                     * placed in bays 1-5, then bays 11-15, and finally bays
                     * 6-10.
                     *
                     * Finally, any other car will be placed in bays 11-15, then
                     * bays 1-5 and finally 6-10;
                     */
                    if (newCar.isLarge) {
                        if (carCount(list) < 15) {
                            for (int listIndex = 5; listIndex <= 9; listIndex++) {
                                if (!list.get(listIndex).isOccupied()) {
                                    i = listIndex;
                                    break;
                                }
                            }
                        } else {
                            JOptionPane.showMessageDialog(new JFrame(), "No Suitable Spaces for Large Vehicles.");
                        }

                        //car is only added if a suitable bay is free
                        if (i != 0) {
                            list.get(i).addCar(newCar);
                        }
                    } else if (newCar.isHighValue) {
                        i = findNextEmpty(0, 5, list);

                        if (i == -1) {
                            i = findNextEmpty(10, 15, list);
                        }

                        if (i == -1) {
                            i = findNextEmpty(5, 10, list);
                        }
                        list.get(i).addCar(newCar);
                    } else {
                        i = findNextEmpty(10, 15, list);

                        if (i == -1) {
                            i = findNextEmpty(0, 5, list);
                        }

                        if (i == -1) {
                            i = findNextEmpty(5, 10, list);
                        }
                        list.get(i).addCar(newCar);
                    }

                    //Enable search button only when a car has been added
                    searchCarButton.setEnabled(true);

                } catch (IndexOutOfBoundsException oob) {
                    JOptionPane.showMessageDialog(new JFrame(), "Car Park Full.");
                }
            } else {
                JOptionPane.showMessageDialog(new JFrame(), "Car already entered.");
            }
        };

        /**
         * Searches list for a car matching the registration number in the text
         * field and removes it if found.
         */
        ActionListener RemoveCarListener = (ActionEvent e) -> {
            int i = getIndex(regBox.getText(), list);

            if (i != -1) {
                list.get(i).removeCar();
            }

            //Disables search and remove buttons if there are no cars remaining
            if (carCount(list) == 0) {
                searchCarButton.setEnabled(false);
                removeCarButton.setEnabled(false);
            }
        };

        //Add components to the jpanel
        addCarButton.addActionListener(InsertCarListener);
        searchCarButton.addActionListener(FindCarListener);
        removeCarButton.addActionListener(RemoveCarListener);

        this.add(regBox);
        this.add(addCarButton);
        this.add(searchCarButton);
        this.add(removeCarButton);
        this.add(carValue);
        this.add(carSize);
    }

    /**
     * Searches the list of parking spaces for a car matching the registration
     * number. If the car is found the index of the space is returned. Otherwise
     * this method will return -1;
     *
     * @param reg the registration number of the car being looked for
     * @param list the list of parking spaces being analyzed
     * @return index of the parking space in which the car has been found
     */
    private int getIndex(String reg, LinkedList<ParkingSpace> list) {
        int i = 0;
        boolean found = false;

        while (i < 15) {
            if (list.get(i).isOccupied()) {
                if (list.get(i).thisCar.registrationNumber.equalsIgnoreCase(reg)) {
                    found = true;
                    break;
                }
            }
            i++;
        }
        if (found == false) {
            i = -1;
        }
        return i;
    }

    /**
     *
     * @param list the list of parking spaces being analyzed
     * @return number of cars in the car park
     */
    private int carCount(LinkedList<ParkingSpace> list) {
        int count = 0;

        for (ParkingSpace space : list) {
            if (space.isOccupied()) {
                count++;
            }
        }
        return count;
    }

    /**
     * Returns true if a car matching the specified registration number is found
     * in the list of parking spaces.
     *
     * @param reg the registration number of the car being looked for
     * @param list the list of parking spaces being analyzed
     * @return whether or not the car already exists
     */
    private boolean isDuplicate(String reg, LinkedList<ParkingSpace> list) {
        boolean copy = false;
        if (carCount(list) > 0) {
            for (ParkingSpace space : list) {
                if (space.isOccupied()) {
                    copy = space.thisCar.registrationNumber.equalsIgnoreCase(reg);

                    if (copy == true) {
                        break;
                    }
                }
            }
        }
        return copy;
    }

    /**
     * Searches the list of parking spaces within a specified range to find the
     * first unoccupied parking space. If no space is found, returns -1;
     *
     * @param start the first space to be searched
     * @param end the final space to searched
     * @param list the list of parking spaces being analyzed
     * @return index of the first empty parking space within the specified range
     */
    private int findNextEmpty(int start, int end, LinkedList<ParkingSpace> list) {
        int i = start;
        if (!list.get(start).isOccupied()) {
            i = start;
        } else {
            while (i < end && list.get(i).isOccupied()) {
                i++;
            }
        }

        if (i == end) {
            return -1;
        } else {
            return i;
        }
    }
}
