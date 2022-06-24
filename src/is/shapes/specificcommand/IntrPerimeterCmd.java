package is.shapes.specificcommand;

import is.command.Command;
import is.interpreter.ExecutionResult;
import is.interpreter.Parser;
import is.interpreter.SyntaxException;
import is.manager.ObjectManager;

import javax.swing.*;
import java.util.Locale;

public class IntrPerimeterCmd implements Command {

    private ObjectManager manager;
    private String target;

    private Parser parser;

    public IntrPerimeterCmd(ObjectManager m, String t)
    {
        this.manager = m;
        this.target = t;
        this.parser = new Parser();
    }
    @Override
    public boolean doIt() {
        try
        {
            ExecutionResult res = parser.parse(
                            "perimeter "+target)
                    .getCmd().execute(manager);

            if(res.isExecuted())
            {
                JOptionPane.showMessageDialog(null,
                        "Area: "+res.getMessage() ,"Area "+target.toLowerCase(Locale.ROOT),
                        JOptionPane.PLAIN_MESSAGE, null);
                return true;
            }
            else
            {
                JOptionPane.showMessageDialog(null,
                        res.getMessage(),"Error", JOptionPane.WARNING_MESSAGE);
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

        return true;
    }
}
