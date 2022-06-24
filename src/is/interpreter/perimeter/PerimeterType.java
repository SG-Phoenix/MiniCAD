package is.interpreter.perimeter;

import is.interpreter.ExecutionResult;
import is.manager.ObjectManager;
import is.shapes.model.GraphicObject;
import is.shapes.model.GroupObject;


public class PerimeterType implements Perimeter {

    private String type;

    public PerimeterType(String type) {
        this.type = type;
    }

    @Override
    public ExecutionResult execute(ObjectManager context) {
        double totalPerimeter = 0;
        for(GraphicObject object : context.getManagedObjects().values())
        {
            if(object.getType().equalsIgnoreCase(type))
                totalPerimeter += object.getPerimeter();
            else if(object.getType().equals("Group"))
            {
                for(GraphicObject child : ((GroupObject) object).getObjects())
                {
                    if(child.getType().equalsIgnoreCase(type))
                    {
                        totalPerimeter += child.getPerimeter();
                    }
                }
            }
        }
        return new ExecutionResult(true, String.valueOf(totalPerimeter));
    }

    @Override
    public String toString()
    {
        return "PerimeterType["+type.toString()+"]";
    }

}
