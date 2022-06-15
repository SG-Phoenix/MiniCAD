package is.interpreter.area;

import is.interpreter.ExecutionResult;
import is.manager.ObjectManager;
import is.shapes.model.GraphicObject;

public class AreaAll extends Area {

    @Override
    public ExecutionResult execute(ObjectManager context) {
        double totArea = 0;
        for(GraphicObject object : context.getManagedObjects().values())
        {
            totArea += object.getArea();
        }
        return new ExecutionResult(true, String.valueOf(totArea));
    }



    @Override
    public String toString()
    {
        return "AreaAll";
    }
}
