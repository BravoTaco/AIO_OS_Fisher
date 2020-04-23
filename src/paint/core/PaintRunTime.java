package paint.core;

import data.Int4;
import data.StoredInformation;
import helpers.FormatUtils;
import paint.components.PaintTextBox;

import java.awt.*;

public class PaintRunTime extends PaintTextBox {
    private StoredInformation storedInformation;

    public PaintRunTime(StoredInformation storedInformation) {
        super(new Int4(25, 90, 155, 20), true, true, "Runtime: ", Color.darkGray.darker(), Color.cyan);
        this.storedInformation = storedInformation;
    }

    @Override
    public void onDraw() {
        text = "Runtime: " + FormatUtils.formatTime(storedInformation.getPaintStoredInformation().getRunTime());
    }
}
