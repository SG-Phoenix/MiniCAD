package is.interpreter.create;

import is.interpreter.CommandIF;
import is.interpreter.Position;
import is.interpreter.typeconstr.TypeConstr;
import is.manager.ObjectManager;
import is.shapes.model.AbstractGraphicObject;

import java.awt.geom.Point2D;

public class Create implements CommandIF {

    private TypeConstr element;
    private Point2D pos;

    public Create(TypeConstr element, Point2D pos) {
        this.element = element;
        this.pos = pos;
    }

    @Override
    public String interpreta(ObjectManager manager) {
        AbstractGraphicObject newObj = element.create(pos);
        return manager.addObject(newObj);
    }

    @Override
    public String toString()
    {
        return "Create["+element+" , "+pos+"]";
    }


}
