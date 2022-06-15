package is.shapes.specificcommand;

import is.command.Command;
import is.interpreter.ExecutionResult;
import is.interpreter.Parser;
import is.interpreter.SyntaxException;
import is.manager.ObjectManager;

import javax.swing.*;
import java.awt.geom.Point2D;

public class IntrImgCreateCmd implements Command {

    private ObjectManager manager;
    private String imagePath;
    private Point2D pos;

    private String objID;
    private Parser parser;

    public IntrImgCreateCmd(ObjectManager m, String i,Point2D p)
    {
        this.manager = m;
        this.imagePath = i;
        this.pos = p;
        this.parser = new Parser();
    }
    @Override
    public boolean doIt() {
        try {

            ExecutionResult res = parser.parse(
                    "create img (\""+imagePath+"\") ("+pos.getX()+","+pos.getY()+")"
            ).getCommand().execute(manager);
            if(res.isExecuted())
            {
                objID = res.getMessage();
                JOptionPane.showMessageDialog(null,
                        "Image with id "+res.getMessage()+" created" ,"Create",
                        JOptionPane.PLAIN_MESSAGE, null);

            }
            else
            {
                JOptionPane.showMessageDialog(null,
                        res.getMessage(),"Error", JOptionPane.WARNING_MESSAGE);

            }


        }catch (SyntaxException|IllegalArgumentException e)
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
