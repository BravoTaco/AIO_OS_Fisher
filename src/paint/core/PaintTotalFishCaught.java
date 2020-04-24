package paint.core;

import data.StoredInformation;
import helpers.FormatUtils;
import helpers.StringUtil;
import paint.components.PaintTextBox;

import java.awt.*;

public class PaintTotalFishCaught extends PaintTextBox {
    private StoredInformation storedInformation;

    public PaintTotalFishCaught(StoredInformation storedInformation) {
        super(true, true, "Fish Caught: ", Color.darkGray.darker(), Color.cyan);
        this.storedInformation = storedInformation;
    }

    @Override
    public void onBeforeDrawText(Graphics2D g) {
        g.setColor(Color.cyan);
        String fishName = StringUtil.convertCapitalStringToNormal(storedInformation.getGeneralStoredInformation().getSelectedFishType().name(), false);
        String amount = FormatUtils.formatValue(storedInformation.getPaintStoredInformation().getFishCaught());
        text = String.format("%s Caught: %s", fishName, amount);
    }
}
