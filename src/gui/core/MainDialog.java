package gui.core;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import gui.components.FishSelector;
import gui.components.FishingModeSelector;
import gui.components.LocationSelector;
import gui.components.ToolSelector;
import gui.enums.BorderLayoutPositions;
import gui.utils.SwingUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainDialog {
    private static MainDialog instance;

    private JDialog dialog;
    private JPanel mainPanel;
    private JPanel topPanel;
    private JPanel bottomPanel;
    private JPanel centerPanel;
    private JPanel buttonsPanel;
    private JButton confirmButton;
    private JButton cancelButton;

    private FishSelector fishSelector;
    private FishingModeSelector fishingModeSelector;
    private ToolSelector toolSelector;
    private LocationSelector locationSelector;

    private boolean wasConfirmClicked;

    public static MainDialog getInstance() {
        if (instance == null)
            instance = new MainDialog("AIO OS Fisher");
        return instance;
    }

    private MainDialog(String title) {
        dialog = new JDialog();
        SwingUtils.setDefaultColors(dialog);
        dialog.setTitle(title);
        dialog.setModal(true);
        dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);

        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEtchedBorder());
        SwingUtils.setDefaultColors(mainPanel);
        dialog.add(mainPanel);

        topPanel = new JPanel(new BorderLayout());
        SwingUtils.initializeComponent(topPanel, BorderFactory.createEmptyBorder(10, 0, 10, 0),
                mainPanel, BorderLayoutPositions.NORTH);

        JPanel centerPanelPadding = new JPanel();
        SwingUtils.initializeComponent(centerPanelPadding, BorderFactory.createEmptyBorder(50, 50, 50, 50),
                mainPanel, BorderLayoutPositions.CENTER);

        centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.PAGE_AXIS));
        SwingUtils.initializeComponent(centerPanel, BorderFactory.createEtchedBorder(),
                centerPanelPadding, BorderLayoutPositions.CENTER);

        bottomPanel = new JPanel(new BorderLayout());
        SwingUtils.initializeComponent(bottomPanel, BorderFactory.createEtchedBorder(),
                mainPanel, BorderLayoutPositions.SOUTH);

        buttonsPanel = new JPanel(new FlowLayout());
        SwingUtils.initializeComponent(buttonsPanel,
                BorderFactory.createLineBorder(Color.darkGray.brighter()),
                bottomPanel, BorderLayoutPositions.EAST);

        confirmButton = new JButton("Confirm");
        confirmButton.setFont(new Font("", Font.BOLD, 22));
        SwingUtils.initializeComponent(confirmButton,
                BorderFactory.createEmptyBorder(5, 5, 5, 5),
                buttonsPanel, BorderLayoutPositions.NONE);
        addDefaultMouseListener(confirmButton);
        confirmButton.addActionListener(e -> onConfirm());

        cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("", Font.BOLD, 22));
        SwingUtils.initializeComponent(cancelButton,
                BorderFactory.createEmptyBorder(5, 5, 5, 5),
                buttonsPanel, BorderLayoutPositions.NONE);
        addDefaultMouseListener(cancelButton);
        cancelButton.addActionListener(e -> onCancel());

        fishSelector = new FishSelector(centerPanel);
        fishingModeSelector = new FishingModeSelector(centerPanel);
        toolSelector = new ToolSelector(centerPanel, fishSelector);
        locationSelector = new LocationSelector(centerPanel, fishSelector);

    }

    private void addDefaultMouseListener(JButton button) {
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setForeground(Color.green);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setForeground(Color.cyan);
            }
        });
    }

    public void addComponentToMainPanel(Component component, String borderLayoutPosition) {
        mainPanel.add(component, borderLayoutPosition);
    }

    public void addComponentToBottomPanel(Component component, String borderLayoutPosition) {
        bottomPanel.add(component, borderLayoutPosition);
    }

    public void show() {
        dialog.pack();
        dialog.setMinimumSize(dialog.getSize());
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    public boolean wasConfirmClicked() {
        return wasConfirmClicked;
    }

    public FishingModeSelector getFishingModeSelector() {
        return fishingModeSelector;
    }

    public FishSelector getFishSelector() {
        return fishSelector;
    }

    public ToolSelector getToolSelector() {
        return toolSelector;
    }

    public LocationSelector getLocationSelector() {
        return locationSelector;
    }

    protected void onConfirm() {
        wasConfirmClicked = true;
        dialog.setVisible(false);
        dialog.dispose();
    }

    protected void onCancel() {
        dialog.setVisible(false);
        dialog.dispose();
    }
}
