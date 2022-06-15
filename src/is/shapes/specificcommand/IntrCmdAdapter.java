package is.shapes.specificcommand;

import is.command.Command;
import is.interpreter.ExecutionResult;
import is.interpreter.IntrCommand;
import is.interpreter.SyntaxException;
import is.manager.ObjectManager;

import javax.swing.*;

public class IntrCmdAdapter implements Command {

    private IntrCommand cmd;
    private ObjectManager context;

    public IntrCmdAdapter(IntrCommand cmd, ObjectManager context) {
        this.cmd = cmd;
        this.context = context;
    }

    @Override
    public boolean doIt() {

        try{
            ExecutionResult res = cmd.execute(context);
            if(res.isExecuted())
            {
                JOptionPane.showMessageDialog(null,res.getMessage(),
                        "Command response", JOptionPane.PLAIN_MESSAGE);
            }
            else
            {
                JOptionPane.showMessageDialog(null,res.getMessage(),
                        "Error", JOptionPane.WARNING_MESSAGE);
            }
        }catch ( SyntaxException | IllegalArgumentException e)
        {
            JOptionPane.showMessageDialog(null,e.getMessage(),
                    "Error", JOptionPane.WARNING_MESSAGE);
        }

        return false;
    }

    @Override
    public boolean undoIt() {

        return false;
    }
}
