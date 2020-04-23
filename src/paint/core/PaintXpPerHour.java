package paint.core;

import data.StoredInformation;
import helpers.FormatUtils;
import paint.components.PaintTextBox;

import java.awt.*;

public class PaintXpPerHour extends PaintTextBox {
    private StoredInformation storedInformation;

    public PaintXpPerHour(StoredInformation storedInformation) {
        super(true, true, "", Color.darkGray.darker(), Color.cyan);
        this.storedInformation = storedInformation;
    }

    @Override
    public void onTextUpdate() {
        text = "Xp/Hr: " + FormatUtils.formatValue(storedInformation.getPaintStoredInformation().getXpPerHour());
    }
}
