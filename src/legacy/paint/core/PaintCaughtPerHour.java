package legacy.paint.core;

import data.StoredInformation;
import helpers.FormatUtils;
import legacy.paint.components.PaintTextBox;

import java.awt.*;

public class PaintCaughtPerHour extends PaintTextBox {
    private StoredInformation storedInformation;

    public PaintCaughtPerHour(StoredInformation storedInformation) {
        super(true, true, "Caught/Hr: ", Color.darkGray.darker(), Color.cyan);
        this.storedInformation = storedInformation;
    }

    @Override
    public void onBeforeDrawText(Graphics2D g) {
        text = "Caught/Hr: " + FormatUtils.formatValue(FormatUtils.calculateItemsPerHour(
                storedInformation.getPaintStoredInformation().getRunTime(),
                storedInformation.getPaintStoredInformation().getFishCaught()));
    }
}
