package legacy.gui.components;

import data.StoredInformation;
import legacy.gui.enums.BorderLayoutPositions;
import legacy.gui.utils.SwingUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FishingModeSelector {
    private JPanel mainPanel;
    private JCheckBox bankingCB;
    private JCheckBox droppingCB;

    public FishingModeSelector(JComponent componentToAddTo, StoredInformation storedInformation) {
        JPanel spacer = new JPanel();
        SwingUtils.initializeComponent(spacer, BorderFactory.createEmptyBorder(10, 10, 10, 10),
                componentToAddTo, BorderLayoutPositions.NONE);

        mainPanel = new JPanel(new FlowLayout());
        SwingUtils.initializeComponent(mainPanel, BorderFactory.createEmptyBorder(),
                spacer, BorderLayoutPositions.NONE);

        bankingCB = new JCheckBox("Enable banking");
        bankingCB.setSelected(true);
        SwingUtils.initializeComponent(bankingCB, BorderFactory.createEmptyBorder(5, 5, 5, 5),
                mainPanel, BorderLayoutPositions.NONE);
        bankingCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bankingCB.setSelected(true);
                droppingCB.setSelected(!bankingCB.isSelected());
            }
        });

        droppingCB = new JCheckBox("Enable dropping");
        SwingUtils.initializeComponent(droppingCB, BorderFactory.createEmptyBorder(5, 5, 5, 5),
                mainPanel, BorderLayoutPositions.NONE);
        droppingCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                droppingCB.setSelected(true);
                bankingCB.setSelected(!droppingCB.isSelected());
            }
        });

        if (storedInformation != null) {
            if (storedInformation.getGeneralStoredInformation().isBankingEnabled()) {
                bankingCB.setSelected(true);
                droppingCB.setSelected(false);
            } else {
                droppingCB.setSelected(true);
                bankingCB.setSelected(false);
            }
        }

        mainPanel.setMaximumSize(mainPanel.getMinimumSize());
    }

    public JCheckBox getBankingCB() {
        return bankingCB;
    }

    public JCheckBox getDroppingCB() {
        return droppingCB;
    }
}
