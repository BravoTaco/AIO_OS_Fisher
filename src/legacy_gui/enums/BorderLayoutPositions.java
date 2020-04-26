package legacy_gui.enums;

import java.awt.*;

public enum BorderLayoutPositions {
    NORTH(BorderLayout.NORTH),
    SOUTH(BorderLayout.SOUTH),
    EAST(BorderLayout.EAST),
    WEST(BorderLayout.WEST),
    CENTER(BorderLayout.CENTER),
    NONE("NONE");

    String identifier;

    BorderLayoutPositions(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }
}
