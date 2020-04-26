package legacy_gui.components;

import data.StoredInformation;
import enums.FishTypes;
import legacy_gui.enums.BorderLayoutPositions;
import legacy_gui.utils.SwingUtils;

import javax.swing.*;
import java.awt.*;

public class FishSelector {
    private JPanel mainPanel;
    private JLabel label;
    private JComboBox<FishTypes> fishTypesJComboBox;

    public FishSelector(JComponent componentToAddTo, StoredInformation storedInformation) {
        JPanel spacer = new JPanel();
        SwingUtils.initializeComponent(spacer, BorderFactory.createEmptyBorder(10, 10, 10, 10),
                componentToAddTo, BorderLayoutPositions.NONE);

        mainPanel = new JPanel(new FlowLayout());
        SwingUtils.initializeComponent(mainPanel, BorderFactory.createEmptyBorder(),
                spacer, BorderLayoutPositions.NONE);

        label = new JLabel("Select type of fish: ");
        label.setFont(new Font("Sans Serif", Font.BOLD, 16));
        SwingUtils.initializeComponent(label, BorderFactory.createEmptyBorder(5, 5, 5, 5),
                mainPanel, BorderLayoutPositions.NONE);

        fishTypesJComboBox = new JComboBox<>(FishTypes.values());
        SwingUtils.initializeComponent(fishTypesJComboBox, BorderFactory.createEmptyBorder(5, 5, 5, 5),
                mainPanel, BorderLayoutPositions.NONE);
        if (storedInformation != null && storedInformation.getGeneralStoredInformation().getSelectedFishType() != null)
            fishTypesJComboBox.setSelectedItem(storedInformation.getGeneralStoredInformation().getSelectedFishType());

        mainPanel.setMaximumSize(mainPanel.getMinimumSize());
    }

    public JComboBox<FishTypes> getFishTypesJComboBox() {
        return fishTypesJComboBox;
    }
}
