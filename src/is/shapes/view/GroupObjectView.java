package is.shapes.view;

import is.shapes.model.GraphicObject;
import is.shapes.model.GroupObject;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class GroupObjectView implements GraphicObjectView{

    private Map<Class<? extends GraphicObject>, GraphicObjectView> viewMap;

    public GroupObjectView(Map<Class<? extends GraphicObject>, GraphicObjectView> viewMap)
    {
        this.viewMap = new HashMap<>(viewMap);
    }
    @Override
    public void drawGraphicObject(GraphicObject go, Graphics2D g) {
        GroupObject groupObject = (GroupObject) go;

        for(GraphicObject child : groupObject.getObjects())
        {
            if(child instanceof GroupObject)
                drawGraphicObject(child,g);
            else
                viewMap.get(child.getClass()).drawGraphicObject(child, g);
        }
    }
}
