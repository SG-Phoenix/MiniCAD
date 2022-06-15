package is.shapes.specificcommand;

import is.command.Command;
import is.interpreter.ExecutionResult;
import is.interpreter.Parser;
import is.interpreter.Position;
import is.interpreter.SyntaxException;
import is.manager.ObjectManager;

import javax.swing.*;
import java.awt.geom.Point2D;

public class IntrCircleCreateCmd implements Command {

    private double radius;
    private Point2D pos;
    private ObjectManager manager;

    private String objID;
    private Parser parser;

    public IntrCircleCreateCmd(ObjectManager m,double r, Point2D p)
    {
        this.manager = m;
        this.pos = p;
        this.radius = r;
        this.parser = new Parser();
    }

    @Override
    public boolean doIt() {
        try
        {
            ExecutionResult res = parser.parse(
                            "create circle ("+this.radius+") ("+this.pos.getX()+","+this.pos.getY()+")")
                    .getCommand().execute(manager);
            if(res.isExecuted())
            {
                objID = res.getMessage();
                JOptionPane.showMessageDialog(null,
                        "Circle with id " +res.getMessage()+" created" ,"Create",
                        JOptionPane.PLAIN_MESSAGE, null);
            }
            else
            {
                JOptionPane.showMessageDialog(null, res.getMessage(),
                        "Error", JOptionPane.WARNING_MESSAGE);
            }


        }catch (SyntaxException|IllegalArgumentException e )
        {
            JOptionPane.showMessageDialog(null, e.getMessage(),"Syntax Error", JOptionPane.WARNING_MESSAGE);
        }
    return false;
    }

    @Override
    public boolean undoIt() {
        parser.parse("del "+objID).getCommand().execute(manager);
        return true;
    }
}
