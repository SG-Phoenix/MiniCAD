package is.manager;

import is.shapes.model.*;

import java.awt.geom.Point2D;
import java.util.*;

public class ObjectManager implements GraphicObjectListener{

    private Map<String, GraphicObject> managedObjects;
    private Set<GraphicObjectListener> listenersList;
    private int nextID;

    public ObjectManager()
    {
        managedObjects = new HashMap<String, GraphicObject>();
        listenersList = new HashSet<GraphicObjectListener>();
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

    public GraphicObject removeObject(String objID) throws ObjectNotFoundException {
        GraphicObject object = getObject(objID);
        object.removeGraphicObjectListener(this);
        managedObjects.remove(objID);
        notifyListeners(new GraphicEvent(object));
        return object;

    }

    public String groupObject(Set<String> ids) throws ObjectNotFoundException, IllegalArgumentException {

        if(ids == null || ids.size()<1)
            throw new IllegalArgumentException("Group cannot be null");

        Set<GraphicObject> groupObj = new HashSet<>();
        for(String key : ids)
        {
            GraphicObject object = getObject(key);

            groupObj.add(object);
        }

        managedObjects.keySet().removeAll(ids);
        GroupObject newGroup = new GroupObject(groupObj);
        String grpID = addObject(newGroup);
        return grpID;
    }

    public void ungroupObject(String objID) throws ObjectNotFoundException, NotAGroupException {
        GraphicObject object = getObject(objID);
        if(object.getType().equals("Group"))
        {
            managedObjects.remove(objID);

            for(GraphicObject child : ((GroupObject) object).getObjects())
            {
                String id = getID();
                managedObjects.put(id,child);
            }

            notifyListeners(new GraphicEvent(object));
        }
        else
            throw new NotAGroupException(objID);
    }

    public GraphicObject getObject(String objID) throws ObjectNotFoundException {
        GraphicObject object = managedObjects.get(objID);

        if( object != null)
        {
            return object;
        }
        else throw new ObjectNotFoundException(objID);
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
