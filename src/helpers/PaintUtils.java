package helpers;

import org.osbot.rs07.Bot;
import org.osbot.rs07.script.MethodProvider;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public final class PaintUtils extends MethodProvider {
    private static PaintUtils instance;

    private PaintUtils() {
    }

    public static PaintUtils getInstance() {
        return instance;
    }

    public static void initializeInstance(Bot bot) {
        if (instance == null) {
            instance = new PaintUtils();
            instance.exchangeContext(bot);
        }
    }

    public Point getMousePoint() {
        int x = MouseInfo.getPointerInfo().getLocation().x - getBot().getCanvas().getLocationOnScreen().x;
        int y = MouseInfo.getPointerInfo().getLocation().y - getBot().getCanvas().getLocationOnScreen().y;
        return new Point(x, y);
    }

    public void drawCenteredString(Graphics2D g, Rectangle2D rectangle2D, String text, Color textColor) {
        g.setColor(textColor);
        FontMetrics metrics = g.getFontMetrics();
        int x = (int) (rectangle2D.getX() + (rectangle2D.getWidth() - metrics.stringWidth(text)) / 2);
        int y = (int) (rectangle2D.getY() + (rectangle2D.getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
        g.drawString(text, x, y);
    }
}
