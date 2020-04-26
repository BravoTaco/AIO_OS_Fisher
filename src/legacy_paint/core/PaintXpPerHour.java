package legacy_paint.core;

import data.StoredInformation;
import helpers.FormatUtils;
import legacy_paint.components.PaintTextBox;

import java.awt.*;

public class PaintXpPerHour extends PaintTextBox {
    private StoredInformation storedInformation;

    public PaintXpPerHour(StoredInformation storedInformation) {
        super(true, true, "", Color.darkGray.darker(), Color.cyan);
        this.storedInformation = storedInformation;
    }

    @Override
    public void onBeforeDrawText(Graphics2D g) {
        g.setColor(Color.cyan);
        text = "Xp/Hr: " + FormatUtils.formatValue(storedInformation.getPaintStoredInformation().getXpPerHour());
    }
}
