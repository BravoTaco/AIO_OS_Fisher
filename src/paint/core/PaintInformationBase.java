package paint.core;

import data.Int4;
import data.StoredInformation;
import org.osbot.rs07.Bot;
import org.osbot.rs07.script.MethodProvider;
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

        for (PaintComponent paintComponent : paintComponents) {
            paintComponent.drawComponent(g);
        }
    }

}
