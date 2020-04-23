package paint.core;

import data.StoredInformation;
import helpers.FormatUtils;
import paint.components.PaintTextBox;

import java.awt.*;

public class PaintTotalFishCaught extends PaintTextBox {
    private StoredInformation storedInformation;

    public PaintTotalFishCaught(StoredInformation storedInformation) {
        super(true, true, "Fish Caught: ", Color.darkGray.darker(), Color.cyan);
        this.storedInformation = storedInformation;
    }

    @Override
    public void onTextUpdate() {
        text = "Fish Caught: " + FormatUtils.formatValue(storedInformation.getPaintStoredInformation().getFishCaught());
    }
}
