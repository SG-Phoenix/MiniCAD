package is.interpreter;

import is.manager.ObjectManager;
import is.shapes.view.GraphicObjectPanel;

public class Command implements GrammarElement {

    private CommandIF command;

    public Command(CommandIF command)
    {
        this.command = command;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Command[");
        sb.append(command.toString());
        sb.append("]");
        return sb.toString();
    }

    @Override
    public String interpreta(ObjectManager context) {
        return command.interpreta(context);
    }
}
