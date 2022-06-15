package is.interpreter.ungroup;

import is.interpreter.ExecutionResult;
import is.interpreter.IntrCommand;
import is.manager.NotAGroupException;
import is.manager.ObjectManager;
import is.manager.ObjectNotFoundException;

import javax.swing.*;

public class Ungroup implements IntrCommand {

    private String objID;

    public Ungroup(String objID) {
        this.objID = objID;
    }


    @Override
    public ExecutionResult execute(ObjectManager context) {

        try
        {
            context.ungroupObject(objID);
            return new ExecutionResult(true, "Group "+objID + " ungrouped");
        }catch (ObjectNotFoundException | NotAGroupException e)
        {
            return new ExecutionResult(false, e.getMessage());
        }
    }


    @Override
    public String toString()
    {
        return "Ungroup["+objID+"]";
    }

}
