package is.manager;

import is.shapes.model.AbstractGraphicObject;

import java.awt.geom.Point2D;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

public class ObjectManager {

    private HashMap<String, AbstractGraphicObject> managedObjects;
    private int nextID;

    public ObjectManager()
    {
        managedObjects = new HashMap<>();
        nextID = 0;
    }

    public String addObject(AbstractGraphicObject object)
    {
        String objID = getID();
        managedObjects.put(objID,object);
        return objID;
    }

    public AbstractGraphicObject removeObject(String objID)
    {
        return managedObjects.remove(objID);
    }

    public void moveObject(String objID, Point2D newPosition)
    {
        managedObjects.get(objID).moveTo(newPosition);

    }

    private String getID()
    {
        return "id"+nextID++;
    }

    public HashMap<String, AbstractGraphicObject> getManagedObjects() {
        return managedObjects;
    }
}
