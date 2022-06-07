package is.interpreter.scale;

import is.interpreter.CommandIF;

public class Scale implements CommandIF {

    private String objID;
    private double scale;

    public Scale(String objID, double scale) {
        this.objID = objID;
        this.scale = scale;
    }

    @Override
    public void interpreta() {

    }

    @Override
    public String toString()
    {
        return "Scale["+objID+" , "+scale+"]";
    }
}
