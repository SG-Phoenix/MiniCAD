package is.interpreter;

import is.manager.ObjectManager;

public class Command implements IntrCommand {

    private IntrCommand command;

    public Command(IntrCommand command)
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
    public ExecutionResult execute(ObjectManager context) {
        return this.command.execute(context);
    }

}
