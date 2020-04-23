package paint.core;

import data.Int4;
import data.StoredInformation;
import helpers.FormatUtils;
import paint.components.PaintTextBox;

import java.awt.*;

public class PaintXpPerHour extends PaintTextBox {
    private StoredInformation storedInformation;

    public PaintXpPerHour(StoredInformation storedInformation) {
        super(new Int4(25, 120, 155, 20), true, true, "", Color.darkGray.darker(), Color.cyan);
        this.storedInformation = storedInformation;
    }

    @Override
    public void onDraw() {
        text = "Xp/Hr: " + FormatUtils.formatValue(storedInformation.getPaintStoredInformation().getXpPerHour());
    }
}
