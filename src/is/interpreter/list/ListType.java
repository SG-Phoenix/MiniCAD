package is.interpreter.list;

import is.interpreter.Symbols;

public class ListType extends List {

    private Symbols type;

    public ListType(Symbols type) {
        this.type = type;
    }

    @Override
    public void interpreta() {

    }

    @Override
    public String toString()
    {
        return "ListType["+type.toString()+"]";
    }
}
