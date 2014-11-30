package carpark;

import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import org.pushingpixels.trident.Timeline;
import javax.swing.*;
import org.pushingpixels.trident.Timeline.RepeatBehavior;

/**
 *
 * @author Padraig
 */
public class MenuBar extends JPanel {

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

        ActionListener FindCarListener = (ActionEvent e) -> {
            int i = getIndex(regBox.getText(), list);

            if (i != -1) {
                final Timeline timeline = new Timeline(list.get(i));
                timeline.addPropertyToInterpolate("background", list.get(i).getBackground(), Color.BLUE);
                timeline.setDuration(1000);
                timeline.playLoop(4, RepeatBehavior.REVERSE);
                removeCarButton.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(new JFrame(), "Car not found");
            }
        };

        ActionListener InsertCarListener = (ActionEvent e) -> {
            Car newCar = new Car(regBox.getText());
            int i = 0;

            if (!isDuplicate(regBox.getText(), list)) {
                try {

                    if (carValue.isSelected()) {
                        newCar.isHighValue = true;
                    }

                    if (carSize.isSelected()) {
                        newCar.isLarge = true;
                    }

                    if (newCar.isLarge) {
                        if (!list.get(5).isOccupied()) {
                            i = 5;
                        } else if (!list.get(6).isOccupied()) {
                            i = 6;
                        } else if (!list.get(7).isOccupied()) {
                            i = 7;
                        } else if (!list.get(8).isOccupied()) {
                            i = 8;
                        } else if (!list.get(9).isOccupied()) {
                            i = 9;
                        } else {
                            JOptionPane.showMessageDialog(new JFrame(), "No Suitable Space for Large Vehicles.");
                        }
                        list.get(i).addCar(newCar);
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
                        i = findNextEmpty(0, 14, list);
                        list.get(i).addCar(newCar);
                    }

                    //Enable search and remove buttons when one car has been added
                    searchCarButton.setEnabled(true);

                } catch (IndexOutOfBoundsException oob) {
                    JOptionPane.showMessageDialog(new JFrame(), "Car Park Full.");
                }
            } else {
                JOptionPane.showMessageDialog(new JFrame(), "Car already entered.");
            }
        };

        ActionListener RemoveCarListener = (ActionEvent e) -> {
            int i = getIndex(regBox.getText(), list);

            if (i != -1) {
                list.get(i).removeCar();
            }

            if (carCount(list) == 0) {
                searchCarButton.setEnabled(false);
                removeCarButton.setEnabled(false);
            }
        };

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

    private int carCount(LinkedList<ParkingSpace> list) {
        int count = 0;

        for (ParkingSpace space : list) {
            if (space.isOccupied()) {
                count++;
            }
        }
        return count;
    }

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

    private int findNextEmpty(int start, int end, LinkedList<ParkingSpace> list) {
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
