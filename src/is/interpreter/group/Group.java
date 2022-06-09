package is.interpreter.group;

import is.interpreter.CommandIF;
import is.manager.ObjectManager;
import is.shapes.model.AbstractGraphicObject;
import is.shapes.model.GroupObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Group implements CommandIF {

    private java.util.List<String> keyList;

    public Group(java.util.List keyList) {
        this.keyList = keyList;
    }

    @Override
    public String interpreta(ObjectManager manager) {

        HashMap<String, AbstractGraphicObject> objMap = manager.getManagedObjects();
        List<AbstractGraphicObject> objList = new ArrayList<>();
        for(String key : keyList)
        {
            AbstractGraphicObject object = objMap.get(key);
            if( object == null )
                return "Object " + key + " not found";

            objList.add(object);
        }

        GroupObject group = new GroupObject(objList);
        String key = manager.addObject(group);
        return "Group " + key + " created";
    }

    @Override
    public String toString()
    {
        return "Group["+keyList.toString()+"]";
    }


}
