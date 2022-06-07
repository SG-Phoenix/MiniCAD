package is.interpreter.area;

import is.interpreter.Symbols;

public class AreaType extends Area {

    private Symbols type;

    public AreaType(Symbols type) {
        this.type = type;
    }

    @Override
    public void interpreta() {

    }

    @Override
    public String toString()
    {
        return "AreaType["+type.toString()+"]";
    }
}
