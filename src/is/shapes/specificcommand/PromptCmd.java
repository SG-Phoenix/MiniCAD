package is.shapes.specificcommand;

import is.command.Command;
import is.interpreter.Parser;
import is.interpreter.SyntaxException;
import is.manager.ObjectManager;

import javax.swing.*;
import java.io.StringReader;

public class PromptCmd implements Command {

    private String cmd;
    private ObjectManager manager;
    private Parser parser;
    public PromptCmd(String cmd, ObjectManager manager) {
        this.cmd = cmd;
        this.manager = manager;
        parser = new Parser();

    }

    @Override
    public boolean doIt() {
        try {
            parser.setInput(new StringReader(cmd));
            is.interpreter.Command intrCommand = parser.getCommand();
            System.out.println(cmd);
            System.out.println(intrCommand);
            String result = intrCommand.interpreta(manager);
            JOptionPane.showMessageDialog(null, result);

        }catch (SyntaxException e)
        {
            JOptionPane.showMessageDialog(null,"SyntaxException : " + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean undoIt() {
        return false;
    }
}
