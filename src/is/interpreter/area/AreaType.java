package is.interpreter.area;

import is.interpreter.ExecutionResult;
import is.manager.ObjectManager;
import is.shapes.model.GraphicObject;
import is.shapes.model.GroupObject;

public class AreaType extends Area {

    private String type;

    public AreaType(String type) {
        this.type = type;
    }

    @Override
    public String toString()
    {
        return "AreaType["+type.toString()+"]";
    }


    @Override
    public ExecutionResult execute(ObjectManager context) {
        double totArea = 0;
        for(GraphicObject object : context.getManagedObjects().values())
        {
            if(object.getType().equalsIgnoreCase(type))
                totArea += object.getArea();
            else if(object.getType().equals("Group"))
            {
                for(GraphicObject child : ((GroupObject) object).getObjects())
                {
                    if(child.getType().equalsIgnoreCase(type))
                    {
                        totArea += child.getArea();
                    }
                }
            }
        }

        return new ExecutionResult(true, String.valueOf(totArea));
    }

}
