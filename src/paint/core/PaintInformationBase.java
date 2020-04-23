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
    private Int4 dimensions;
    private ArrayList<PaintComponent> paintComponents;
    private Color backgroundColor;

    private PaintRunTime paintRunTime;
    private PaintXpPerHour paintXpPerHour;
    private PaintTotalFishCaught paintTotalFishCaught;

    public PaintInformationBase(Int4 dimensions, StoredInformation storedInformation) {
        this.dimensions = dimensions;
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
        g.setColor(backgroundColor.darker());
        g.fill(borderRectangle);
        g.setColor(backgroundColor);
        g.fill(backgroundRectangle);

        int y = 15;
        for (PaintComponent paintComponent : paintComponents) {
            if (paintComponent instanceof PaintTextBox) {
                ((PaintTextBox) paintComponent).backgroundRectangle.setRect(
                        backgroundRectangle.getX() + 15, backgroundRectangle.getY() + y, backgroundRectangle.getWidth() - 30, 20);
                ((PaintTextBox) paintComponent).borderBackgroundRectangle.setRect(
                        backgroundRectangle.getX() + 15 - 3, backgroundRectangle.getY() + y - 3, backgroundRectangle.getWidth() - 30 + 6, 20 + 6);
                y += 30;
            }
            paintComponent.drawComponent(g);
        }
    }

}
