package is.shapes.model;

import java.awt.*;
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.util.List;

public class GroupObject extends AbstractGraphicObject{

    private Point2D position;
    private List<AbstractGraphicObject> groupObjects;

    public GroupObject(List<AbstractGraphicObject> groupObjects) {
        double mediaX = 0;
        double mediaY = 0;
        for(AbstractGraphicObject object: groupObjects)
        {
            Point2D punto = object.getPosition();
            mediaX += punto.getX();
            mediaY += punto.getY();
        }

        mediaX /=groupObjects.size();
        mediaY /=groupObjects.size();

        this.position = new Point2D.Double(mediaX,mediaY);
        this.groupObjects = groupObjects;
    }

    @Override
    public void moveTo(Point2D p) {
        double diffX = this.position.getX()-p.getX();
        double diffY = this.position.getY()-p.getY();

        for(AbstractGraphicObject object : groupObjects)
        {
            Point2D punto = object.getPosition();
            double newX = punto.getX() + diffX;
            double newY = punto.getY() + diffY;
            object.moveTo(new Point2D.Double(newX,newY));
        }

        this.position = p;
        notifyListeners(new GraphicEvent(this));
    }

    @Override
    public Point2D getPosition() {
        return this.position;
    }

    @Override
    public Dimension2D getDimension() {
        Dimension totalDimension = new Dimension(0,0);
        for(AbstractGraphicObject object : groupObjects)
        {
            Dimension2D objectDimension = object.getDimension();
            totalDimension.setSize(
                    totalDimension.getWidth()+objectDimension.getWidth(),
                    totalDimension.getHeight()+objectDimension.getHeight());
        }

        return totalDimension;
    }

    @Override
    public void scale(double factor) {
        for (AbstractGraphicObject object : groupObjects)
        {
            object.scale(factor);
        }

        notifyListeners(new GraphicEvent(this));
    }

    @Override
    public boolean contains(Point2D p) {
        for (AbstractGraphicObject object : groupObjects)
        {
            if(object.contains(p))
                return true;
        }

        return false;
    }

    @Override
    public String getType() {
        return "Group";
    }
}
