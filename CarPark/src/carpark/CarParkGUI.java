package carpark;

import java.awt.*;
import java.util.*;
import javax.swing.*;

/**
 * Manages GUI components.
 * @author B00639511
 */
public class CarParkGUI extends JFrame {

    //Holds parking spaces
    public static LinkedList<ParkingSpace> spaceList = new LinkedList<>();

    //Dimensions for large and small parking spaces
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

    /**
     * Sets up properties for the class and initiates the GUI
     */
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
     * initializes the spaceList and sets up properties for each parking space
     */
    private void initialiseCarPark() {
        for (int i = 0; i < 15; i++) {
            spaceList.add(new ParkingSpace());
        }

        //Top Row
        topRowProperties.put(8, "large");
        topRowProperties.put(7, "large");
        topRowProperties.put(6, "large");
        topRowProperties.put(2, "small");
        topRowProperties.put(1, "small");

        //Middle Row
        middleRowProperties.put(13, "small");
        middleRowProperties.put(12, "small");
        middleRowProperties.put(11, "small");
        middleRowProperties.put(3, "small");

        //Bottom Row
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

    /**
     * Establishes layout for GUI components and add them to the frame
     */
    private void startGui() {
        //Layout properties
        FlowLayout flow = new FlowLayout(FlowLayout.LEFT);
        flow.setHgap(50);
        flow.setVgap(20);

        //add the menu to the top of the frame
        this.add(new MenuBar(spaceList), BorderLayout.NORTH);

        //manages the layout for parking spaces
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

                spaceList.get(index).add(label);

                //set size according to value retrieved
                if (entry.getValue().equals("small")) {
                    spaceList.get(index).setPreferredSize(SMALL);
                } else {
                    spaceList.get(index).setPreferredSize(LARGE);
                }

                //space added to the specified row
                switch (i) {
                    case 0:
                        topRowPane.add(spaceList.get(index));
                        break;
                    case 1:
                        middleRowPane.add(spaceList.get(index));
                        break;
                    case 2:
                        bottomRowPane.add(spaceList.get(index));
                        break;
                }
            }
        }

        //add rows to carpark
        middleRowPane.add(stationPane);
        carParkPane.add(topRowPane, BorderLayout.NORTH);
        carParkPane.add(middleRowPane, BorderLayout.CENTER);
        carParkPane.add(bottomRowPane, BorderLayout.SOUTH);
    }
}
