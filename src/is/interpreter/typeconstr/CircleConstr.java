package is.interpreter.typeconstr;

import is.shapes.model.AbstractGraphicObject;
import is.shapes.model.CircleObject;
import is.shapes.model.GraphicObject;

import java.awt.geom.Point2D;

public class CircleConstr implements TypeConstr {

    private double rad;

    public CircleConstr(double pos) {
        this.rad = pos;
    }


    @Override
    public String toString()
    {
        return "CircleConstr["+ rad +"]";
    }

    @Override
    public GraphicObject create(Point2D point) {
        return new CircleObject(point, rad);
    }
}
