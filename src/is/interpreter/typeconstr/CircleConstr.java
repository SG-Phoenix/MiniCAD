package is.interpreter.typeconstr;

import is.shapes.model.AbstractGraphicObject;
import is.shapes.model.CircleObject;

import java.awt.geom.Point2D;

public class CircleConstr extends TypeConstr {

    private double pos;

    public CircleConstr(double pos) {
        this.pos = pos;
    }


    @Override
    public String toString()
    {
        return "CircleConstr["+pos+"]";
    }

    @Override
    public AbstractGraphicObject create(Point2D point) {
        return new CircleObject(point, pos);
    }
}
