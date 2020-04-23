package paint.components;

import data.Int4;
import helpers.PaintUtils;

import java.awt.*;

public class PaintTextBox extends PaintRectangle {
    private String text;
    private Color backgroundColor, textColor;

    public PaintTextBox(Int4 dimensions, boolean useBorder, boolean useFill, String text, Color backgroundColor, Color textColor) {
        super(dimensions, useBorder, useFill);
        this.text = text;
        this.backgroundColor = backgroundColor;
        this.textColor = textColor;
    }

    @Override
    public void drawComponent(Graphics2D g) {
        if (useFill && useBorder) {
            g.setColor(backgroundColor.darker());
            g.fill(borderBackgroundRectangle);
            g.setColor(backgroundColor);
            g.fill(backgroundRectangle);
            drawText(g);
        } else if (useFill) {
            g.setColor(backgroundColor);
            g.fill(backgroundRectangle);
            drawText(g);
        } else if (useBorder) {
            g.setColor(backgroundColor.darker());
            g.draw(borderBackgroundRectangle);
            g.setColor(backgroundColor);
            g.draw(backgroundRectangle);
            drawText(g);
        } else {
            g.setColor(backgroundColor);
            g.draw(backgroundRectangle);
            drawText(g);
        }
    }

    private void drawText(Graphics2D g) {
        g.setColor(textColor);
        PaintUtils.getInstance().drawCenteredString(g, backgroundRectangle, text, textColor);
    }
}
