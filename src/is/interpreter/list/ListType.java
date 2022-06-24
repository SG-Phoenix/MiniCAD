package is.interpreter.list;

import is.interpreter.ExecutionResult;
import is.manager.ObjectManager;
import is.shapes.model.GraphicObject;
import is.shapes.model.GroupObject;

import java.util.Map;

public class ListType implements List
{

    private String type;

    public ListType(String type) {
        this.type = type;
    }


    @Override
    public ExecutionResult execute(ObjectManager context) {
        StringBuilder sb = new StringBuilder();
        sb.append(type + " objects:");
        sb.append("\n");
        for(Map.Entry<String, GraphicObject> object : context.getManagedObjects().entrySet())
        {
            if(object.getValue().getType().equalsIgnoreCase(type))
            {
                sb.append("Object " + object.getKey());
                sb.append("\n");
                sb.append(object.getValue());
                sb.append("\n");
            }
            else if(object.getValue().getType().equals("Group"))
            {

                GroupObject group = (GroupObject) context.getObject(object.getKey());
                for(GraphicObject child : group.getObjects())
                {
                    if(child.getType().equalsIgnoreCase(type))
                    {
                        sb.append("Object " + object.getKey() + " child");
                        sb.append("\n");
                        sb.append(child);
                        sb.append("\n");
                    }
                }

            }
        }
        return new ExecutionResult(true, sb.toString());
    }


    @Override
    public String toString()
    {
        return "ListType["+type.toString()+"]";
    }
}
