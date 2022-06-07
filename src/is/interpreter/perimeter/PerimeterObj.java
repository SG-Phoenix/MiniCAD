package is.interpreter.perimeter;

public class PerimeterObj extends Perimeter {

    private String objID;

    public PerimeterObj(String objID) {
        this.objID = objID;
    }

    @Override
    public void interpreta() {

    }

    @Override
    public String toString()
    {
        return "PerimeterObj["+objID+"]";
    }
}
