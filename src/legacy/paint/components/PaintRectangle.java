package legacy.paint.components;

import legacy.paint.interfaces.PaintComponent;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public abstract class PaintRectangle implements PaintComponent {
    public Rectangle2D backgroundRectangle;
    public Rectangle2D borderBackgroundRectangle;
    protected boolean useBorder, useFill;

    public PaintRectangle(boolean useBorder, boolean useFill) {
        this.useBorder = useBorder;
        this.useFill = useFill;

        backgroundRectangle = new Rectangle2D.Float(0,0,0,0);
        borderBackgroundRectangle = new Rectangle2D.Float(0,0,0,0);
    }

    public abstract void drawComponent(Graphics2D g);

}
