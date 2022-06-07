package is.interpreter.area;

public class AreaObj extends Area {

    private String objID;

    public AreaObj(String objID) {
        this.objID = objID;
    }

    @Override
    public void interpreta() {

    }

    @Override
    public String toString()
    {
        return "AreaObj["+objID+"]";
    }
}
