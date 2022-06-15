package is.interpreter.list;

import is.interpreter.ExecutionResult;
import is.manager.ObjectManager;
import is.shapes.model.GraphicObject;

import javax.swing.*;
import java.util.Map;

public class ListAll extends List
{
    @Override
    public String toString()
    {
        return "ListAll";
    }


    @Override
    public ExecutionResult execute(ObjectManager context) {
        StringBuilder sb = new StringBuilder();
        sb.append("All objects: ");
        sb.append("\n");
        for(Map.Entry<String, GraphicObject> object : context.getManagedObjects().entrySet())
        {
            sb.append("Object "+object.getKey());
            sb.append("\n");
            sb.append(object.getValue());
            sb.append("\n");
        }
        return new ExecutionResult(true, sb.toString());
    }

}
