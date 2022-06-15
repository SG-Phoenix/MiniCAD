package is.interpreter.group;

import is.interpreter.ExecutionResult;
import is.interpreter.IntrCommand;
import is.manager.ObjectManager;
import is.manager.ObjectNotFoundException;

import java.util.*;

public class Group implements IntrCommand {

    private Set<String> keyList;

    public Group(Set keyList) {
        this.keyList = keyList;
    }

    @Override
    public String toString()
    {
        return "Group["+keyList.toString()+"]";
    }


    @Override
    public ExecutionResult execute(ObjectManager context) {
        try {
            return new ExecutionResult(true, context.groupObject(keyList));
        } catch (ObjectNotFoundException e) {

            return new ExecutionResult(false, e.getMessage());
        }

    }

}
