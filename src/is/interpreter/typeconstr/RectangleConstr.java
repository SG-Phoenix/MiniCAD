package is.interpreter.typeconstr;


import is.interpreter.Position;
import is.shapes.model.AbstractGraphicObject;
import is.shapes.model.RectangleObject;

import java.awt.*;
import java.awt.geom.Point2D;

public class RectangleConstr extends TypeConstr {

    private Point2D position;

    public RectangleConstr(Point2D position) {
        this.position = position;
    }


    @Override
    public String toString()
    {
        return "RectangleConstr["+position+"]";
    }

    @Override
    public AbstractGraphicObject create(Point2D point) {
        return new RectangleObject(point, position.getX(),position.getY());
    }
}
