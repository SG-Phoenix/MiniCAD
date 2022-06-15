package is.manager;

import is.shapes.model.CircleObject;
import is.shapes.model.GraphicObject;
import is.shapes.model.GroupObject;
import is.shapes.model.RectangleObject;
import org.junit.Before;
import org.junit.Test;

import java.awt.geom.Point2D;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

public class TestObjectManager {

    private ObjectManager manager;
    private String[] ids;

    @Before
    public void init()
    {
        manager = new ObjectManager();
        GraphicObject circle = new CircleObject(new Point2D.Double(10,30),10);
        GraphicObject rectangle = new RectangleObject(new Point2D.Double(30,40),10,30);
        GraphicObject circle2 = new CircleObject(new Point2D.Double(100,20),15);
        GraphicObject rectangle2 = new RectangleObject(new Point2D.Double(220,50),30,75);

        GraphicObject grpRect = new RectangleObject(new Point2D.Double(100,20),200,50);
        GraphicObject grpCirc = new CircleObject(new Point2D.Double(300,50),35);
        GraphicObject grpRect2 = new RectangleObject(new Point2D.Double(440,100),87,35);

        Set<GraphicObject> grpSet = new HashSet<>();
        grpSet.add(grpRect);
        grpSet.add(grpCirc);
        grpSet.add(grpRect2);

        GraphicObject grp = new GroupObject(grpSet);
        ids = new String[5];
        ids[0] = manager.addObject(circle);
        ids[1] = manager.addObject(rectangle);
        ids[2] = manager.addObject(circle2);
        ids[3] = manager.addObject(rectangle2);
        ids[4] = manager.addObject(grp);
    }

    @Test
    public void testAddObject()
    {
        int id = ids.length;
        GraphicObject circle = new CircleObject(new Point2D.Double(10,30),10);
        GraphicObject rectangle = new RectangleObject(new Point2D.Double(30,40),10,30);

        String objID;
        objID = manager.addObject(circle);
        assertEquals("id"+id,objID);
        id++;

        objID = manager.addObject(rectangle);
        assertEquals("id"+id,objID);
    }
    @Test
    public void testRemoveObject()
    {

        GraphicObject object = manager.getObject(ids[0]);
        GraphicObject removedObject = manager.removeObject(ids[0]);

        assertEquals(object, removedObject);

        assertThrows(ObjectNotFoundException.class, () ->
        {
            manager.removeObject(ids[0]);
        });

    }

    @Test
    public void testGraphicChangedFires()
    {
        TestEventListener eventListener = new TestEventListener();

        manager.addObject(new CircleObject(new Point2D.Double(30,20),10));

        assertEquals(0,eventListener.getEventCounter());
        manager.addGraphicObjectListener(eventListener);

        manager.addObject(new RectangleObject(new Point2D.Double(40,130),100,40));

        assertEquals(1,eventListener.getEventCounter());

        manager.removeGraphicObjectListener(eventListener);

        manager.addObject(new RectangleObject(new Point2D.Double(230,1500),50,200));

        assertEquals(1,eventListener.getEventCounter());
    }

    @Test
    public void testGetObject()
    {
        String wrongObjID = "id"+ids.length;

        GraphicObject object = manager.getObject(ids[0]);

        assertThrows(ObjectNotFoundException.class, () ->
        {
           GraphicObject object1 = manager.getObject(wrongObjID);
        });

    }

    @Test
    public void testGroupObjects()
    {

        assertThrows(IllegalArgumentException.class, () ->
        {
            manager.groupObject(new HashSet<>());
        });

        assertThrows(ObjectNotFoundException.class, () ->
        {
           Set<String> grpIDs = new HashSet<>();
            grpIDs.add(ids[0]);
            grpIDs.add(ids[1]);
            grpIDs.add("id10");
           manager.groupObject(grpIDs);
        });

        Set<String> grpIDs = new HashSet<>();
        grpIDs.add(ids[0]);
        grpIDs.add(ids[1]);
        String grpID = manager.groupObject(grpIDs);

        assertThrows(ObjectNotFoundException.class, () ->
        {
           manager.getObject(ids[0]);
        });

        assertThrows(ObjectNotFoundException.class, () ->
        {
            manager.getObject(ids[1]);
        });
    }


    @Test
    public void testUngroupObject()
    {

        assertThrows(ObjectNotFoundException.class, () ->
        {
           manager.ungroupObject("id10");
        });

        assertThrows(NotAGroupException.class, () ->
        {
            manager.ungroupObject(ids[0]);
        });

        manager.ungroupObject(ids[4]);

        assertThrows(ObjectNotFoundException.class, () ->
        {
            manager.ungroupObject(ids[4]);
        });
    }

    @Test
    public void testGetManagedObjects()
    {
            Map<String,GraphicObject> managedObject = manager.getManagedObjects();

            assertThrows(UnsupportedOperationException.class, () ->
            {
               managedObject.put("id10", new CircleObject(new Point2D.Double(10,30), 10));
            });
    }

}
