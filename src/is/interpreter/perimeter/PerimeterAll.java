package is.interpreter.perimeter;

import is.interpreter.ExecutionResult;
import is.manager.ObjectManager;
import is.shapes.model.GraphicObject;

public class PerimeterAll implements Perimeter {

    @Override
    public ExecutionResult execute(ObjectManager context) {
        double totalPerimeter = 0;
        for(GraphicObject object : context.getManagedObjects().values())
        {
            totalPerimeter += object.getPerimeter();
        }

        return new ExecutionResult(true, String.valueOf(totalPerimeter));
    }


    @Override
    public String toString()
    {
        return "PerimeterAll";
    }


}
