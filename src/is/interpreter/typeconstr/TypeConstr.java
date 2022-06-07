package is.interpreter.typeconstr;

import is.interpreter.GrammarElement;
import is.shapes.model.AbstractGraphicObject;

import java.awt.geom.Point2D;

public abstract class TypeConstr {

    public abstract AbstractGraphicObject create(Point2D point);
}
