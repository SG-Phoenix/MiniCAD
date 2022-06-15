package is.shapes.specificcommand;

import is.command.Command;
import is.interpreter.ExecutionResult;
import is.interpreter.Parser;
import is.interpreter.SyntaxException;
import is.manager.ObjectManager;

import javax.swing.*;
import java.util.Set;
import java.util.stream.Collectors;

public class IntrGroupCmd implements Command {

    private Set<String> elements;

    private ObjectManager manager;

    private Parser parser;
    public IntrGroupCmd(ObjectManager manager,Set<String> toGroup) {
        this.elements = toGroup;
        this.manager = manager;
        this.parser = new Parser();
    }

    @Override
    public boolean doIt() {
        try
        {
            ExecutionResult res = parser.parse(
                    "grp " + this.elements.stream().collect(Collectors.joining(",")))
                    .getCommand().execute(manager);

            if(res.isExecuted())
            {
                JOptionPane.showMessageDialog(null,
                        "Group with id "+res.getMessage()+" created" ,"Group",
                        JOptionPane.PLAIN_MESSAGE, null);
            }
            else
            {
                JOptionPane.showMessageDialog(null,
                        res.getMessage(),"Error", JOptionPane.WARNING_MESSAGE);
            }

        }catch (SyntaxException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(),"Syntax Error", JOptionPane.WARNING_MESSAGE);
        }
        return false;
    }

    @Override
    public boolean undoIt() {
        return false;
    }
}
