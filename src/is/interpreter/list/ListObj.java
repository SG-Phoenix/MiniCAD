package is.interpreter.list;

import is.manager.ObjectManager;

public class ListObj extends List
{
    private String objID;

    public ListObj(String objID) {
        this.objID = objID;
    }

    @Override
    public String interpreta(ObjectManager manager) {
        return manager.getManagedObjects().get(objID).toString();
    }

    @Override
    public String toString()
    {
        return "ListObj["+objID+"]";
    }


}
