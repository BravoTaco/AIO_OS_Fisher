package gui.components;

import enums.FishTypes;
import enums.ToolTypes;
import gui.enums.BorderLayoutPositions;
import gui.utils.SwingUtils;

import javax.swing.*;
import java.awt.*;

public class ToolSelector {
    private JPanel mainPanel;
    private JComboBox<ToolTypes> toolTypesJComboBox;
    private JLabel label;

    public ToolSelector(JComponent componentToAddTo, FishSelector fishSelector) {
        JPanel spacer = new JPanel();
        SwingUtils.initializeComponent(spacer, BorderFactory.createEmptyBorder(10, 10, 10, 10),
                componentToAddTo, BorderLayoutPositions.NONE);

        mainPanel = new JPanel(new FlowLayout());
        SwingUtils.initializeComponent(mainPanel, BorderFactory.createRaisedBevelBorder(),
                spacer, BorderLayoutPositions.NONE);

        label = new JLabel("Select fishing tool");
        SwingUtils.initializeComponent(label, BorderFactory.createEmptyBorder(5, 5, 5, 5),
                mainPanel, BorderLayoutPositions.NONE);

        toolTypesJComboBox = new JComboBox<>(((FishTypes)
                fishSelector.getFishTypesJComboBox().getSelectedItem()).getToolTypes());
        SwingUtils.initializeComponent(toolTypesJComboBox, BorderFactory.createEmptyBorder(5, 5, 5, 5),
                mainPanel, BorderLayoutPositions.NONE);
        fishSelector.getFishTypesJComboBox().addActionListener(e -> {
            toolTypesJComboBox.removeAllItems();
            for (ToolTypes tool : ((FishTypes) fishSelector.getFishTypesJComboBox().getSelectedItem()).getToolTypes()) {
                toolTypesJComboBox.addItem(tool);
            }
            toolTypesJComboBox.setSelectedIndex(0);
        });

        mainPanel.setMaximumSize(mainPanel.getMinimumSize());
    }

    public JComboBox<ToolTypes> getToolTypesJComboBox() {
        return toolTypesJComboBox;
    }
}
