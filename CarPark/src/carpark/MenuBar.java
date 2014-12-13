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

    private JTextField regBox = new JTextField();
    private JButton addCarButton = new JButton("Add Car");
    private JButton searchCarButton = new JButton("Find Car");
    private JButton removeCarButton = new JButton("Remove Car");
    private JRadioButton carValue = new JRadioButton("High Value");
    private JRadioButton carSize = new JRadioButton("Large Car");
    private final JPanel countPane = new JPanel();

    public MenuBar(LinkedList<ParkingSpace> list) {
        this.setLayout(new GridLayout(0, 6));

        searchCarButton.setEnabled(false);
        removeCarButton.setEnabled(false);

        JLabel count = new JLabel("Car Count: " + carCount(list));

        ActionListener FindCarListener = (ActionEvent e) -> {
            int i = getIndex(regBox.getText(), list);

            if (i != -1) {
                final Timeline timeline = new Timeline(list.get(i));
                timeline.addPropertyToInterpolate("background", list.get(i).getBackground(), Color.BLUE);
                timeline.setDuration(1000);
                timeline.playLoop(5, RepeatBehavior.REVERSE);
                removeCarButton.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(new JFrame(), "Car not found");
            }
        };

        ActionListener InsertCarListener = (ActionEvent e) -> {
            Car newCar;

            if (carCount(list) != 15) {
                if (!regBox.getText().isEmpty()) {
                    if (!isDuplicate(regBox.getText(), list)) {

                        //Create car
                        if (carValue.isSelected()) {
                            newCar = new ValuableCar(regBox.getText());
                        } else if (carSize.isSelected()) {
                            newCar = new LargeCar(regBox.getText());
                        } else {
                            newCar = new Car(regBox.getText());
                        }

                        //Assign car to space
                        newCar.occupySpace(list);

                        //Enable search button when one car has been added
                        searchCarButton.setEnabled(true);
                        count.setText("Car Count: " + carCount(list));
                    } else {
                        JOptionPane.showMessageDialog(new JFrame(), "Car already entered.");
                    }
                }else{
                    JOptionPane.showMessageDialog(new JFrame(),"No registration number entered.");
                }
            } else {
                JOptionPane.showMessageDialog(new JFrame(), "Car Park Full.");
            }
        };

        ActionListener RemoveCarListener = (ActionEvent e) -> {
            int i = getIndex(regBox.getText(), list);
            if (i != -1) {
                list.get(i).removeCar();
            }

            if (carCount(list) == 0) {
                searchCarButton.setEnabled(false);
            }

            removeCarButton.setEnabled(false);
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
        this.add(count);
    }

    private int getIndex(String reg, LinkedList<ParkingSpace> list) {
        int i = 0;
        boolean found = false;

        while (i < 15) {
            if (list.get(i).isOccupied()) {
                if (list.get(i).thisCar.registrationNumber.equals(reg)) {
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
                    copy = space.thisCar.registrationNumber.equals(reg);

                    if (copy == true) {
                        break;
                    }
                }
            }
        }
        return copy;
    }
}
