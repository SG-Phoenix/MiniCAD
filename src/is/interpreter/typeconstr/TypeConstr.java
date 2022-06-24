package is.interpreter.typeconstr;

import is.shapes.model.AbstractGraphicObject;
import is.shapes.model.GraphicObject;

import java.awt.geom.Point2D;

public interface TypeConstr {

    public abstract GraphicObject create(Point2D point);
}
