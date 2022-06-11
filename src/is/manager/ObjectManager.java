package is.manager;

import is.shapes.model.*;

import java.awt.geom.Point2D;
import java.util.*;

public class ObjectManager implements GraphicObjectListener{

    private Map<String, GraphicObject> managedObjects;
    private List<GraphicObjectListener> listenersList;
    private int nextID;

    public ObjectManager()
    {
        managedObjects = new HashMap<String, GraphicObject>();
        listenersList = new LinkedList<GraphicObjectListener>();
        nextID = 0;
    }

    public void addGraphicObjectListener(GraphicObjectListener l)
    {
        if (listenersList.contains(l))
            return;
        listenersList.add(l);
    }

    public void removeGraphicObjectListener(GraphicObjectListener l)
    {

        listenersList.remove(l);
    }

    private void notifyListeners(GraphicEvent e) {

        for (GraphicObjectListener gol : listenersList)
            gol.graphicChanged(e);

    }

    public String addObject(GraphicObject object)
    {
        String objID = getID();
        managedObjects.put(objID,object);
        object.addGraphicObjectListener(this);
        notifyListeners(new GraphicEvent(object));
        return objID;
    }

    public String setObject(String key, GraphicObject object)
    {
        managedObjects.put(key, object);
        notifyListeners(new GraphicEvent(object));
        return key;
    }

    public GraphicObject removeObject(String objID)
    {
        GraphicObject object = managedObjects.get(objID);
        if( object != null )
        {
            object.removeGraphicObjectListener(this);
            notifyListeners(new GraphicEvent(object));
            return managedObjects.remove(objID);
        }
        return null;

    }

    public String groupObject(Set<String> ids)
    {
        Map<String,GraphicObject> objList = new HashMap();
        for(String key : ids)
        {
            GraphicObject object = managedObjects.get(key);
            if( object == null )
                return "Object " + key + " not found";

            objList.put(key,object);
        }

        System.out.println("Prima:");
        for(String key : managedObjects.keySet())
        {
            System.out.println(key);
        }
        managedObjects.keySet().removeAll(ids);

        System.out.println("Dopo:");
        for(String key : managedObjects.keySet())
        {
            System.out.println(key);
        }
        String id = getID();
        GroupObject newGroup = new GroupObject(objList);
        managedObjects.put(id,newGroup);
        notifyListeners(new GraphicEvent(newGroup));
        return id;
    }

    public boolean ungroupObject(String objID)
    {
        GraphicObject object = managedObjects.get(objID);
        if(object != null && object.getType().equals("Group"))
        {
            managedObjects.remove(objID);
            for(Map.Entry<String,GraphicObject> childObj : ((GroupObject) object).getObjects().entrySet())
            {
                managedObjects.put(childObj.getKey(),childObj.getValue());
            }
            notifyListeners(new GraphicEvent(object));
            return true;
        }
        return false;
    }

    public GraphicObject getObject(String objID)
    {
        GraphicObject object = managedObjects.get(objID);

        if( object != null)
        {
            return object;
        }
        return null;
    }

    public Map<String, GraphicObject> getManagedObjects()
    {
        return Collections.unmodifiableMap(managedObjects);
    }



    private String getID()
    {
        return "id"+nextID++;
    }




    @Override
    public void graphicChanged(GraphicEvent e) {
        notifyListeners(e);
    }
}
