package paint;

import data.Int4;
import helpers.PaintUtils;
import org.osbot.rs07.Bot;
import org.osbot.rs07.input.mouse.BotMouseListener;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

public abstract class PaintButton {
    private Int4 dimensions;
    private String text;
    private Rectangle2D rectangle2D;
    private BotMouseListener mouseListener;
    private Color textColor, backgroundButtonColor;

    private final Font textFont = new Font("Sans Serif", Font.PLAIN, 14);

    public PaintButton(Int4 dimensions, String text, Bot bot) {
        initialize(text, dimensions, bot);
    }

    public void drawButton(Graphics2D g) {
        g.setFont(textFont);

        fillButton(g);

        if (rectangle2D.getBounds().contains(PaintUtils.getInstance().getMousePoint()))
            onHover();
        else
            onNotHover();

        PaintUtils.getInstance().drawCenteredString(g, rectangle2D, text, textColor);
    }

    public void setText(String text) {
        this.text = text;
    }

    public void click() {
        backgroundButtonColor = Color.darkGray.brighter().brighter();
        textColor = Color.green.brighter();
        onClick();
    }

    public abstract void onClick();

    private void onHover() {
        textColor = Color.green;
        backgroundButtonColor = Color.darkGray.brighter();
    }

    private void onNotHover() {
        textColor = Color.cyan;
        backgroundButtonColor = Color.darkGray;
    }

    private void fillButton(Graphics2D g) {
        g.setColor(Color.darkGray.darker());
        g.fill(new Rectangle2D.Float(
                (float) rectangle2D.getX() - 3,
                (float) rectangle2D.getY() - 3,
                (float) rectangle2D.getWidth() + 6,
                (float) rectangle2D.getHeight() + 6));
        g.setColor(backgroundButtonColor);
        g.fill(rectangle2D);
    }

    private void initialize(String text, Int4 dimensions, Bot bot) {
        this.text = text;
        this.dimensions = dimensions;
        textColor = Color.cyan;
        backgroundButtonColor = Color.darkGray;
        rectangle2D = new Rectangle2D.Float(
                dimensions.getX(),
                dimensions.getY(),
                dimensions.getZ(),
                dimensions.getW());
        mouseListener = new BotMouseListener() {
            @Override
            public void checkMouseEvent(MouseEvent mouseEvent) {
                if (rectangle2D.getBounds().contains(PaintUtils.getInstance().getMousePoint()) && mouseEvent.getID() == MouseEvent.MOUSE_CLICKED) {
                    mouseEvent.consume();
                    click();
                }
            }
        };
        bot.addMouseListener(mouseListener);
    }
}
