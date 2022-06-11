package is.interpreter.move;


import is.interpreter.CommandIF;
import is.manager.ObjectManager;
import is.shapes.model.GraphicObject;
import is.shapes.view.GraphicObjectPanel;

import java.awt.geom.Point2D;

public class Move implements CommandIF {

    private boolean isRelative;
    private String objID;
    private Point2D pos;

    public Move(boolean isRelative, String objID, Point2D pos) {
        this.isRelative = isRelative;
        this.objID = objID;
        this.pos = pos;
    }

    @Override
    public String interpreta(ObjectManager context) {
        Point2D newPosition;
        GraphicObject object = context.getObject(objID);
        if(object == null)
            return "Object "+objID+" not found";

        if(isRelative)
        {
            Point2D oldPosition = object.getPosition();
            newPosition = new Point2D.Double(oldPosition.getX()+pos.getX(),oldPosition.getY()+pos.getY());
        }
        else
            newPosition = pos;

        object.moveTo(newPosition);

        return "Object "+objID+" moved to ( "+newPosition.getX()+" , "+newPosition.getY()+" )";



    }

    @Override
    public String toString()
    {
        return "Move["+isRelative+" , "+objID+" , "+pos+"]";
    }


}
