package carpark;

import java.awt.*;
import java.util.*;
import javax.swing.*;

/**
 *
 * @author B00639511
 */
public class CarParkGUI extends JFrame {

    public static LinkedList<ParkingSpace> carList = new LinkedList<>();

    final Dimension LARGE = new Dimension(60, 150);
    final Dimension SMALL = new Dimension(60, 75);

    ArrayList<LinkedHashMap<Integer, String>> rowProperties = new ArrayList<>();

    LinkedHashMap<Integer, String> topRowProperties = new LinkedHashMap<>();
    LinkedHashMap<Integer, String> middleRowProperties = new LinkedHashMap<>();
    LinkedHashMap<Integer, String> bottomRowProperties = new LinkedHashMap<>();

    JPanel topRowPane = new JPanel();
    JPanel middleRowPane = new JPanel();
    JPanel bottomRowPane = new JPanel();

    public static void main(String[] args) {
        CarParkGUI g = new CarParkGUI();
    }

    public CarParkGUI() {
        this.setLayout(new BorderLayout());
        initialiseCarPark();
        startGui();

        //PROPERTIES
        this.setResizable(false);
        this.setLocationByPlatform(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Car Park Management System");
        this.pack();
        this.setVisible(rootPaneCheckingEnabled);
    }

    /**
     * Fill linked list with new instances of ParkingSpace and set properties of
     * each ParkingSpace in LinkedHashMaps
     */
    private void initialiseCarPark() {
        for (int i = 0; i < 15; i++) {
            carList.add(new ParkingSpace());
        }

        topRowProperties.put(8, "large");
        topRowProperties.put(7, "large");
        topRowProperties.put(6, "large");
        topRowProperties.put(2, "small");
        topRowProperties.put(1, "small");

        middleRowProperties.put(13, "small");
        middleRowProperties.put(12, "small");
        middleRowProperties.put(11, "small");
        middleRowProperties.put(3, "small");

        bottomRowProperties.put(15, "small");
        bottomRowProperties.put(14, "small");
        bottomRowProperties.put(10, "large");
        bottomRowProperties.put(9, "large");
        bottomRowProperties.put(5, "small");
        bottomRowProperties.put(4, "small");

        rowProperties.add(topRowProperties);
        rowProperties.add(middleRowProperties);
        rowProperties.add(bottomRowProperties);

    }

    private void startGui() {
        //Layout properties
        FlowLayout flow = new FlowLayout(FlowLayout.LEFT);
        flow.setHgap(50);
        flow.setVgap(20);

        this.add(new MenuBar(carList), BorderLayout.NORTH);

        JPanel carParkPane = new JPanel();
        carParkPane.setLayout(new BorderLayout());
        carParkPane.add(topRowPane, BorderLayout.NORTH);
        carParkPane.add(middleRowPane, BorderLayout.CENTER);
        carParkPane.add(bottomRowPane, BorderLayout.SOUTH);
        this.add(carParkPane);

        JPanel stationPane = new JPanel();
        stationPane.add(new JLabel("Attendant's Station"));
        stationPane.setBackground(Color.white);

        topRowPane.setBackground(Color.ORANGE);
        topRowPane.setLayout(flow);

        middleRowPane.setBackground(Color.ORANGE);
        middleRowPane.setLayout(flow);

        bottomRowPane.setBackground(Color.ORANGE);
        bottomRowPane.setLayout(flow);

        /*
         Retrieves space number and size properties from rowProperties
         */
        for (int i = 0; i < 3; i++) {
            for (Map.Entry<Integer, String> entry : rowProperties.get(i).entrySet()) {
                int index = entry.getKey() - 1;

                JLabel label = new JLabel("" + entry.getKey());
                label.setForeground(Color.WHITE);

                carList.get(index).setBackground(Color.GREEN);
                carList.get(index).add(label);

                if (entry.getValue().equals("small")) {
                    carList.get(index).setPreferredSize(SMALL);
                } else {
                    carList.get(index).setPreferredSize(LARGE);
                }

                switch (i) {
                    case 0:
                        topRowPane.add(carList.get(index));
                        break;
                    case 1:
                        middleRowPane.add(carList.get(index));
                        break;
                    case 2:
                        bottomRowPane.add(carList.get(index));
                        break;
                }
            }
        }

        middleRowPane.add(stationPane);
        carParkPane.add(topRowPane, BorderLayout.NORTH);
        carParkPane.add(middleRowPane, BorderLayout.CENTER);
        carParkPane.add(bottomRowPane, BorderLayout.SOUTH);
    }
}
