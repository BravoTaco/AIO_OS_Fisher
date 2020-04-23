package paint.components;

import data.Int4;
import paint.interfaces.PaintComponent;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public abstract class PaintRectangle implements PaintComponent {
    protected Int4 dimensions;
    protected Rectangle2D backgroundRectangle;
    protected Rectangle2D borderBackgroundRectangle;
    protected boolean useBorder, useFill;

    public PaintRectangle(Int4 dimensions, boolean useBorder, boolean useFill) {
        this.dimensions = dimensions;
        this.useBorder = useBorder;
        this.useFill = useFill;

        backgroundRectangle = new Rectangle2D.Float(dimensions.getX(), dimensions.getY(), dimensions.getZ(), dimensions.getW());
        borderBackgroundRectangle = new Rectangle2D.Float(dimensions.getX() - 3, dimensions.getY() - 3, dimensions.getZ() + 6, dimensions.getW() + 6);
    }

    public abstract void drawComponent(Graphics2D g);

}
