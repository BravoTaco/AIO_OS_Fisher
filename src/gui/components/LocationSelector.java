package gui.components;

import enums.FishTypes;
import enums.Locations;
import gui.enums.BorderLayoutPositions;
import gui.utils.SwingUtils;

import javax.swing.*;
import java.awt.*;

public class LocationSelector {
    private JPanel mainPanel;
    private JComboBox<Locations> locationsJComboBox;
    private JLabel label;

    public LocationSelector(JComponent componentToAddTo, FishSelector fishSelector) {
        JPanel spacer = new JPanel();
        SwingUtils.initializeComponent(spacer, BorderFactory.createEmptyBorder(10, 10, 10, 10),
                componentToAddTo, BorderLayoutPositions.NONE);

        mainPanel = new JPanel(new FlowLayout());
        SwingUtils.initializeComponent(mainPanel, BorderFactory.createEmptyBorder(),
                spacer, BorderLayoutPositions.NONE);

        label = new JLabel("Select fishing location: ");
        SwingUtils.initializeComponent(label, BorderFactory.createEmptyBorder(5, 5, 5, 5),
                mainPanel, BorderLayoutPositions.NONE);

        locationsJComboBox = new JComboBox<>(((FishTypes) fishSelector.getFishTypesJComboBox().getSelectedItem()).getSupportedLocations());
        SwingUtils.initializeComponent(locationsJComboBox, BorderFactory.createEmptyBorder(5, 5, 5, 5),
                mainPanel, BorderLayoutPositions.NONE);
        fishSelector.getFishTypesJComboBox().addActionListener(e -> {
            locationsJComboBox.removeAllItems();
            for (Locations location : ((FishTypes) fishSelector.getFishTypesJComboBox().getSelectedItem()).getSupportedLocations()) {
                locationsJComboBox.addItem(location);
            }
        });
    }

    public JComboBox<Locations> getLocationsJComboBox() {
        return locationsJComboBox;
    }
}
