package is.shapes.view;

import is.shapes.model.GraphicObject;
import is.shapes.model.GroupObject;

import java.awt.*;

public class GroupObjectView implements GraphicObjectView{
    @Override
    public void drawGraphicObject(GraphicObject go, Graphics2D g) {
        GroupObject groupObject = (GroupObject) go;

        for(GraphicObject child : groupObject.getObjects().values())
        {
            child.getView().drawGraphicObject(child, g);
        }
    }
}
