package is.shapes.specificcommand;

import is.command.Command;
import is.interpreter.ExecutionResult;
import is.interpreter.Parser;
import is.interpreter.SyntaxException;
import is.manager.ObjectManager;

import javax.swing.*;

public class IntrDeleteCmd implements Command {

    private ObjectManager manager;
    private String objID;
    private Parser parser;

    public IntrDeleteCmd(ObjectManager m,String o)
    {
        this.manager = m;
        this.objID = o;
        this.parser = new Parser();
    }
    @Override
    public boolean doIt() {
        try{
            ExecutionResult res = parser.parse("del " + objID)
                                    .getCommand().execute(manager);
            if(res.isExecuted())
            {
                JOptionPane.showMessageDialog(null,
                        "Object with id "+objID+" was deleted" ,"Delete",
                        JOptionPane.PLAIN_MESSAGE, null);
            }
            else
            {
                JOptionPane.showMessageDialog(null,
                        res.getMessage(),"Error", JOptionPane.WARNING_MESSAGE);
            }
        }
        catch (SyntaxException e)
        {
            JOptionPane.showMessageDialog(null,
                    e.getMessage(),"Syntax Error", JOptionPane.WARNING_MESSAGE);
        }

        return false;
    }

    @Override
    public boolean undoIt() {
        return false;
    }
}
