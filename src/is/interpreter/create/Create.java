package is.interpreter.create;

import is.interpreter.ExecutionResult;
import is.interpreter.IntrCommand;
import is.interpreter.typeconstr.TypeConstr;
import is.manager.ObjectManager;
import is.shapes.model.GraphicObject;

import java.awt.geom.Point2D;

public class Create implements IntrCommand {

    private TypeConstr element;
    private Point2D pos;


    public Create(TypeConstr element, Point2D pos) {
        this.element = element;
        this.pos = pos;
    }

    @Override
    public ExecutionResult execute(ObjectManager manager) {
        GraphicObject object = element.create(pos);
        return new ExecutionResult(true, manager.addObject(object));
    }




    @Override
    public String toString()
    {
        return "Create["+element+" , "+pos+"]";
    }
}
