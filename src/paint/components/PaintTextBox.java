package paint.components;

import helpers.PaintUtils;

import java.awt.*;

public abstract class PaintTextBox extends PaintRectangle {
    protected String text;
    protected Color backgroundColor, textColor;

    public PaintTextBox(boolean useBorder, boolean useFill, String text, Color backgroundColor, Color textColor) {
        super(useBorder, useFill);
        this.text = text;
        this.backgroundColor = backgroundColor;
        this.textColor = textColor;
    }

    @Override
    public void drawComponent(Graphics2D g) {
        onTextUpdate();
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

    public abstract void onTextUpdate();

    private void drawText(Graphics2D g) {
        g.setColor(textColor);
        PaintUtils.getInstance().drawCenteredString(g, backgroundRectangle, text, textColor);
    }
}
