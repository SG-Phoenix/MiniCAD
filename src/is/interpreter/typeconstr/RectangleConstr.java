package is.interpreter.typeconstr;


import is.shapes.model.GraphicObject;
import is.shapes.model.RectangleObject;

import java.awt.geom.Point2D;

public class RectangleConstr implements TypeConstr {

    private Point2D dim;

    public RectangleConstr(Point2D position) {
        this.dim = position;
    }


    @Override
    public String toString()
    {
        return "RectangleConstr["+ dim +"]";
    }

    @Override
    public GraphicObject create(Point2D point) {
        return new RectangleObject(point, dim.getX(), dim.getY());
    }
}
