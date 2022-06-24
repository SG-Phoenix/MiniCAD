package is.shapes.specificcommand;

import is.command.Command;
import is.interpreter.ExecutionResult;
import is.interpreter.Parser;
import is.interpreter.SyntaxException;
import is.manager.ObjectManager;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;

public class IntrRectangleCreateCmd implements Command {

    private ObjectManager manager;
    private Point2D pos;
    private Dimension dim;

    private String objID;
    private Parser parser;

    public IntrRectangleCreateCmd(ObjectManager m, Point2D p, Dimension d)
    {
        this.manager = m;
        this.pos = p;
        this.dim = d;
        parser = new Parser();
    }
    @Override
    public boolean doIt() {

        try
        {
            ExecutionResult res = parser.parse(
                            "new rectangle ("+dim.getWidth()+","+dim.getHeight()+") ("+pos.getX()+","+pos.getY()+")")
                    .getCmd().execute(manager);

            if(res.isExecuted())
            {
                objID = res.getMessage();
                JOptionPane.showMessageDialog(null,
                        "Rectangle with id " +res.getMessage()+" created" ,"Create",
                        JOptionPane.PLAIN_MESSAGE, null);
            }
            else
            {
                JOptionPane.showMessageDialog(null,
                        res.getMessage(),"Error", JOptionPane.WARNING_MESSAGE);

            }

        }catch (SyntaxException | IllegalArgumentException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(),"Syntax Error", JOptionPane.WARNING_MESSAGE);

        }
        return false;
    }

    @Override
    public boolean undoIt() {
        parser.parse("del "+objID).getCmd().execute(manager);
        return true;
    }
}
