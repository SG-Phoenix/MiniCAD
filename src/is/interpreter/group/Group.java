package is.interpreter.group;

import is.interpreter.CommandIF;
import is.manager.ObjectManager;


import java.util.*;

public class Group implements CommandIF {

    private Set<String> keyList;

    public Group(Set keyList) {
        this.keyList = keyList;
    }

    @Override
    public String interpreta(ObjectManager context) {

        return context.groupObject(keyList);
    }

    @Override
    public String toString()
    {
        return "Group["+keyList.toString()+"]";
    }


}
