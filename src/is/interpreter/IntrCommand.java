package is.interpreter;

import is.manager.ObjectManager;

public interface IntrCommand{

    ExecutionResult execute(ObjectManager context);

}
