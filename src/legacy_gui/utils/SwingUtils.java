package legacy_gui.utils;

import legacy_gui.enums.BorderLayoutPositions;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public final class SwingUtils {
    private SwingUtils() {
    }

    public static void setDefaultColors(Component component) {
        component.setBackground(Color.darkGray);
        component.setForeground(Color.cyan);
    }

    public static void initializeComponent(JComponent componentToInitialize, Border border, JComponent componentToAddTo,
                                           BorderLayoutPositions borderLayoutPosition) {
        setDefaultColors(componentToInitialize);
        componentToInitialize.setBorder(border);
        if (!borderLayoutPosition.getIdentifier().equalsIgnoreCase("none")) {
            componentToAddTo.add(componentToInitialize, borderLayoutPosition.getIdentifier());
        } else {
            componentToAddTo.add(componentToInitialize);
        }
    }
}