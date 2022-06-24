package is.interpreter.typeconstr;

import is.Main;
import is.shapes.model.AbstractGraphicObject;
import is.shapes.model.GraphicObject;
import is.shapes.model.ImageObject;

import javax.swing.*;
import java.awt.geom.Point2D;
import java.nio.file.Path;

public class ImgConstr implements TypeConstr {

    private String path;

    public ImgConstr(String path) {
        this.path = path.replace("\\","/");
    }


    @Override
    public String toString()
    {
        return "ImgConstr["+path.toString()+"]";
    }

    @Override
    public GraphicObject create(Point2D point) {
        return new ImageObject(path, point);
    }
}
