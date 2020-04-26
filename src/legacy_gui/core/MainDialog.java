package legacy_gui.core;

import data.StoredInformation;
import legacy_gui.components.FishSelector;
import legacy_gui.components.FishingModeSelector;
import legacy_gui.components.LocationSelector;
import legacy_gui.components.ToolSelector;
import legacy_gui.enums.BorderLayoutPositions;
import legacy_gui.utils.SwingUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainDialog {
    private static MainDialog instance;

    private final JDialog dialog;
    private final JPanel mainPanel;
    private final JPanel topPanel;
    private final JPanel bottomPanel;
    private final JPanel centerPanel;
    private final JPanel buttonsPanel;
    private final JButton confirmButton;
    private final JButton cancelButton;

    private final FishSelector fishSelector;
    private final FishingModeSelector fishingModeSelector;
    private final ToolSelector toolSelector;
    private final LocationSelector locationSelector;

    private boolean wasConfirmClicked;

    public static MainDialog getInstance(StoredInformation storedInformation) {
        if (instance == null)
            instance = new MainDialog("AIO OS Fisher", storedInformation);
        return instance;
    }

    private MainDialog(String title, StoredInformation storedInformation) {
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
                BorderFactory.createEmptyBorder(10, 10, 10, 10),
                bottomPanel, BorderLayoutPositions.EAST);

        JPanel confirmButtonPanel = new JPanel();
        SwingUtils.initializeComponent(confirmButtonPanel,
                BorderFactory.createEtchedBorder(),
                buttonsPanel, BorderLayoutPositions.NONE);

        confirmButton = new JButton("Confirm");
        confirmButton.setFont(new Font("", Font.BOLD, 22));
        SwingUtils.initializeComponent(confirmButton,
                BorderFactory.createEmptyBorder(3, 3, 3, 3),
                confirmButtonPanel, BorderLayoutPositions.NONE);
        addDefaultMouseListener(confirmButton);
        confirmButton.addActionListener(e -> onConfirm());

        JPanel cancelButtonPanel = new JPanel();
        SwingUtils.initializeComponent(cancelButtonPanel,
                BorderFactory.createEtchedBorder(),
                buttonsPanel, BorderLayoutPositions.NONE);

        cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("", Font.BOLD, 22));
        SwingUtils.initializeComponent(cancelButton,
                BorderFactory.createEmptyBorder(3, 3, 3, 3),
                cancelButtonPanel, BorderLayoutPositions.NONE);
        addDefaultMouseListener(cancelButton);
        cancelButton.addActionListener(e -> onCancel());

        fishSelector = new FishSelector(centerPanel, storedInformation);
        fishingModeSelector = new FishingModeSelector(centerPanel, storedInformation);
        toolSelector = new ToolSelector(centerPanel, fishSelector, storedInformation);
        locationSelector = new LocationSelector(centerPanel, fishSelector, storedInformation);
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
