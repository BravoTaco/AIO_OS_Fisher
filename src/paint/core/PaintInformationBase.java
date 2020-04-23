package paint.core;

import data.Int4;
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

    public PaintInformationBase(Int4 dimensions) {
        this.dimensions = dimensions;
        backgroundColor = Color.darkGray;
        backgroundRectangle = new Rectangle2D.Float(dimensions.getX(), dimensions.getY(), dimensions.getZ(), dimensions.getW());
        borderRectangle = new Rectangle2D.Float(dimensions.getX() - 3, dimensions.getY() - 3, dimensions.getZ() + 6, dimensions.getW() + 6);
        paintComponents = new ArrayList<>();
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

        for (PaintComponent paintComponent : paintComponents) {
            paintComponent.drawComponent(g);
        }
    }

}
