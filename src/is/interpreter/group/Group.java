package is.interpreter.group;

import is.interpreter.ExecutionResult;
import is.interpreter.IntrCommand;
import is.manager.ObjectManager;
import is.manager.ObjectNotFoundException;

import java.util.*;

public class Group implements IntrCommand {

    private Set<String> idList;

    public Group(Set idList) {
        this.idList = new HashSet<>(idList);
    }

    @Override
    public String toString()
    {
        return "Group["+ idList.toString()+"]";
    }


    @Override
    public ExecutionResult execute(ObjectManager context) {
        try {
            return new ExecutionResult(true, context.groupObject(idList));
        } catch (ObjectNotFoundException e) {

            return new ExecutionResult(false, e.getMessage());
        }

    }

}
