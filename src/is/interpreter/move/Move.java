package is.interpreter.move;


import is.interpreter.ExecutionResult;
import is.interpreter.IntrCommand;
import is.manager.ObjectManager;
import is.manager.ObjectNotFoundException;
import is.shapes.model.GraphicObject;

import java.awt.geom.Point2D;

public class Move implements IntrCommand {

    private boolean isRelative;
    private String objID;
    private Point2D pos;

    public Move(boolean isRelative, String objID, Point2D pos) {
        this.isRelative = isRelative;
        this.objID = objID;
        this.pos = pos;
    }

    @Override
    public String toString()
    {
        return "Move["+isRelative+" , "+objID+" , "+pos+"]";
    }


    @Override
    public ExecutionResult execute(ObjectManager context) {
        Point2D newPosition;
        try
        {
            GraphicObject object = context.getObject(objID);
            Point2D oldPosition = object.getPosition();
            if(isRelative)
            {
                newPosition = new Point2D.Double(oldPosition.getX()+pos.getX(),oldPosition.getY()+pos.getY());
            }
            else
                newPosition = pos;
            this.pos = newPosition;
            object.moveTo(newPosition);
            return new ExecutionResult(true,
                    object.getType() + " " + objID + " moved to ("+pos.getX()+","+pos.getY()+")");
        }catch (ObjectNotFoundException e)
        {
            return new ExecutionResult(false, e.getMessage());
        }

    }

}
