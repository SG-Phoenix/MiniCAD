package is.interpreter.create;

import is.interpreter.CommandIF;
import is.interpreter.Position;
import is.interpreter.typeconstr.TypeConstr;
import is.manager.ObjectManager;
import is.shapes.model.AbstractGraphicObject;
import is.shapes.model.GraphicObject;
import is.shapes.view.GraphicObjectPanel;

import java.awt.geom.Point2D;

public class Create implements CommandIF {

    private TypeConstr element;
    private Point2D pos;

    public Create(TypeConstr element, Point2D pos) {
        this.element = element;
        this.pos = pos;
    }

    @Override
    public String interpreta(ObjectManager context) {
        GraphicObject newObj = element.create(pos);
        return context.addObject(newObj);
    }

    @Override
    public String toString()
    {
        return "Create["+element+" , "+pos+"]";
    }


    @Override
    public boolean doIt() {
        return false;
    }

    @Override
    public boolean undoIt() {
        return false;
    }
}
