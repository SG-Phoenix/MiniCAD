package is.interpreter.ungroup;

import is.interpreter.CommandIF;

public class Ungroup implements CommandIF {

    private String objID;

    public Ungroup(String objID) {
        this.objID = objID;
    }

    @Override
    public void interpreta() {

    }

    @Override
    public String toString()
    {
        return "Ungroup["+objID+"]";
    }
}
