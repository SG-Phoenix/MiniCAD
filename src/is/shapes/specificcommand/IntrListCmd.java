package is.shapes.specificcommand;

import is.command.Command;
import is.interpreter.ExecutionResult;
import is.interpreter.Parser;
import is.interpreter.SyntaxException;
import is.manager.ObjectManager;

import javax.swing.*;

public class IntrListCmd implements Command {

    private ObjectManager manager;
    private String target;

    private Parser parser;
    public IntrListCmd(ObjectManager m, String t)
    {
        this.manager = m;
        this.target = t;
        parser = new Parser();
    }
    @Override
    public boolean doIt() {
        try{
           ExecutionResult res = parser.parse("ls "+ target).getCmd().execute(manager);
           if(res.isExecuted())
           {
               JOptionPane.showMessageDialog(null, res.getMessage(),
                       "List", JOptionPane.PLAIN_MESSAGE, null);
               return true;
           }
           else
           {
               JOptionPane.showMessageDialog(null, res.getMessage(),
                       "Error", JOptionPane.PLAIN_MESSAGE, null);
               return false;
           }

        }catch (SyntaxException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(),"Syntax Error", JOptionPane.WARNING_MESSAGE);
            return false;
        }

    }

    @Override
    public boolean undoIt() {
        return false;
    }
}
