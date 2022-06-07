package is.interpreter.group;

import is.interpreter.CommandIF;

public class Group implements CommandIF {

    private java.util.List<String> objList;

    public Group(java.util.List objList) {
        this.objList = objList;
    }

    @Override
    public void interpreta() {

    }

    @Override
    public String toString()
    {
        return "Group["+objList.toString()+"]";
    }
}
