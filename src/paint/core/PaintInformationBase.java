package paint.core;

import data.Int4;
import data.StoredInformation;
import paint.components.PaintTextBox;
import paint.interfaces.PaintComponent;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class PaintInformationBase implements PaintComponent {
    private Rectangle2D backgroundRectangle;
    private Rectangle2D borderRectangle;
    private ArrayList<PaintComponent> paintComponents;
    private Color backgroundColor;

    private PaintRunTime paintRunTime;
    private PaintXpPerHour paintXpPerHour;
    private PaintTotalFishCaught paintTotalFishCaught;

    private int padding = 15;
    private int borderOffset = 3;
    private int componentHeights = 20;

    private Font defaultFont = new Font("Sans Serif", Font.BOLD, 12);

    public PaintInformationBase(Int4 dimensions, StoredInformation storedInformation) {
        backgroundColor = Color.darkGray;
        backgroundRectangle = new Rectangle2D.Float(dimensions.getX(), dimensions.getY(), dimensions.getZ(), dimensions.getW());
        borderRectangle = new Rectangle2D.Float(dimensions.getX() - 3, dimensions.getY() - 3, dimensions.getZ() + 6, dimensions.getW() + 6);
        paintComponents = new ArrayList<>();
        paintRunTime = new PaintRunTime(storedInformation);
        paintComponents.add(paintRunTime);
        paintXpPerHour = new PaintXpPerHour(storedInformation);
        paintComponents.add(paintXpPerHour);
        paintTotalFishCaught = new PaintTotalFishCaught(storedInformation);
        paintComponents.add(paintTotalFishCaught);
    }

    public void addPaintComponent(PaintComponent paintComponent) {
        paintComponents.add(paintComponent);
    }

    @Override
    public void drawComponent(Graphics2D g) {
        g.setFont(defaultFont);
        g.setColor(backgroundColor.darker());
        g.fill(borderRectangle);
        g.setColor(backgroundColor);
        g.fill(backgroundRectangle);

        int y = 15;
        for (PaintComponent paintComponent : paintComponents) {
            if (paintComponent instanceof PaintTextBox) {
                ((PaintTextBox) paintComponent).backgroundRectangle.setRect(
                        backgroundRectangle.getX() + padding, backgroundRectangle.getY() + y,
                        backgroundRectangle.getWidth() - padding * 2, componentHeights);
                ((PaintTextBox) paintComponent).borderBackgroundRectangle.setRect(
                        backgroundRectangle.getX() + padding - borderOffset, backgroundRectangle.getY() + y - borderOffset,
                        backgroundRectangle.getWidth() - padding * 2 + borderOffset * 2, componentHeights + borderOffset * 2);
                y += componentHeights + padding;
            }
            g.setFont(defaultFont);
            paintComponent.drawComponent(g);
        }
    }

}
