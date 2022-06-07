package is.interpreter.perimeter;

import is.interpreter.Symbols;

public class PerimeterType extends Perimeter {

    private Symbols type;

    public PerimeterType(Symbols type) {
        this.type = type;
    }

    @Override
    public void interpreta() {

    }

    @Override
    public String toString()
    {
        return "PerimeterType["+type.toString()+"]";
    }
}
