package paint.core;

import data.StoredInformation;
import helpers.FormatUtils;
import paint.components.PaintTextBox;

import java.awt.*;

public class PaintRunTime extends PaintTextBox {
    private StoredInformation storedInformation;

    public PaintRunTime(StoredInformation storedInformation) {
        super(true, true, "Runtime: ", Color.darkGray.darker(), Color.cyan);
        this.storedInformation = storedInformation;
    }

    @Override
    public void onTextUpdate() {
        text = "Runtime: " + FormatUtils.formatTime(storedInformation.getPaintStoredInformation().getRunTime());
    }
}
