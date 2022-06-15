package is.shapes.model;

import is.shapes.view.GraphicObjectView;
import is.shapes.view.GroupObjectView;

import java.awt.*;
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.util.*;
import java.util.List;

public class GroupObject extends AbstractGraphicObject {

    private Point2D position;
    private Set<GraphicObject> groupObjects;

    public GroupObject(Set<GraphicObject> groupObjects) {
        this.groupObjects = new HashSet<>(groupObjects);
        setPosition();
    }

    private void setPosition()
    {
        double mediaX = 0;
        double mediaY = 0;
        for(GraphicObject object: groupObjects)
        {
            Point2D punto = object.getPosition();
            mediaX += punto.getX();
            mediaY += punto.getY();
        }

        mediaX /=groupObjects.size();
        mediaY /=groupObjects.size();

        this.position = new Point2D.Double(mediaX,mediaY);
    }

    @Override
    public void moveTo(Point2D p) {
        double diffX = p.getX()-this.position.getX();
        double diffY = p.getY()-this.position.getY();

        for(GraphicObject object : groupObjects)
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
        for(GraphicObject object : groupObjects)
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
        for (GraphicObject object : groupObjects)
        {
            object.scale(factor);
        }

        notifyListeners(new GraphicEvent(this));
    }

    @Override
    public boolean contains(Point2D p) {
        for (GraphicObject object : groupObjects)
        {
            if(object.contains(p))
                return true;
        }

        return false;
    }

    @Override
    public double getArea() {
        double totalGroupArea = 0;
        for (GraphicObject object : groupObjects)
        {
            totalGroupArea += object.getArea();
        }

        return totalGroupArea;
    }

    @Override
    public double getPerimeter() {
        double totalGroupPerimeter = 0;
        for (GraphicObject object : groupObjects)
        {
            totalGroupPerimeter += object.getPerimeter();
        }

        return totalGroupPerimeter;
    }

    public Set<GraphicObject> getObjects()
    {
        return groupObjects;
    }

    @Override
    public String getType() {
        return "Group";
    }


    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(getType());
        sb.append("[");
        sb.append("position=("+position.getX()+","+position.getY()+")");
        sb.append("\n");
        sb.append("Children:\n");
        for(GraphicObject child : groupObjects)
            sb.append(" "+child+"\n");
        sb.append("]");

        return sb.toString();
    }


}
