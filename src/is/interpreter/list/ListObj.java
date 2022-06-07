package is.interpreter.list;

public class ListObj extends List
{
    private String objID;

    public ListObj(String objID) {
        this.objID = objID;
    }

    @Override
    public void interpreta() {

    }

    @Override
    public String toString()
    {
        return "ListObj["+objID+"]";
    }
}
