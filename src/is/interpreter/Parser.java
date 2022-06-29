package is.interpreter;

import is.interpreter.area.Area;
import is.interpreter.area.AreaAll;
import is.interpreter.area.AreaObj;
import is.interpreter.area.AreaType;
import is.interpreter.create.Create;
import is.interpreter.group.Group;
import is.interpreter.list.*;
import is.interpreter.list.List;
import is.interpreter.move.Move;
import is.interpreter.perimeter.Perimeter;
import is.interpreter.perimeter.PerimeterAll;
import is.interpreter.perimeter.PerimeterObj;
import is.interpreter.perimeter.PerimeterType;
import is.interpreter.remove.Remove;
import is.interpreter.scale.Scale;
import is.interpreter.typeconstr.CircleConstr;
import is.interpreter.typeconstr.ImgConstr;
import is.interpreter.typeconstr.RectangleConstr;
import is.interpreter.typeconstr.TypeConstr;
import is.interpreter.ungroup.Ungroup;

import java.awt.geom.Point2D;
import java.io.StringReader;
import java.util.HashSet;
import java.util.Set;

public class Parser {

    private Analyzer analyzer;
    private Symbols nextSymbol;
    private Command cmd;

    public Parser()
    {

    }

    public Parser parse(String in)
    {

        analyzer = new Analyzer(new StringReader(in.replace("\\","/")));
        this.cmd = command();
        symbolCheck(Symbols.EOF);
        return this;
    }
    private Command command()
    {
        nextSymbol = analyzer.getSymbol();
        IntrCommand comando;
        if(nextSymbol == Symbols.CREATE)
            comando = create();
        else
            if (nextSymbol == Symbols.REMOVE)
                comando = remove();
        else
            if (nextSymbol == Symbols.MOVE || nextSymbol == Symbols.MOVEOFF)
                comando = move();
        else
            if (nextSymbol == Symbols.SCALE)
                comando = scale();
        else
            if (nextSymbol == Symbols.LIST)
                comando = list();
        else
            if (nextSymbol == Symbols.GROUP)
                comando = group();
        else
            if (nextSymbol == Symbols.UNGROUP)
                comando = ungroup();
        else
            if (nextSymbol == Symbols.AREA)
                comando = area();
        else
            if ( nextSymbol == Symbols.PERIMETER )
                comando = perimeter();
        else
            throw new SyntaxException("Not a valid command");

        return new Command(comando);
    }




    private Create create()
    {

        TypeConstr element;

        nextSymbol = analyzer.getSymbol();

        if ( nextSymbol == Symbols.CIRCLE ) {
            symbolCheck(Symbols.OPEN_BRACKET);
            element = circle();
            symbolCheck(Symbols.CLOSE_BRACKET);
        }
        else
            if ( nextSymbol == Symbols.RECTANGLE )
                element = rectangle();
        else
            if ( nextSymbol == Symbols.IMG ) {
                symbolCheck(Symbols.OPEN_BRACKET);
                element = img();
                symbolCheck(Symbols.CLOSE_BRACKET);
            }
        else
            throw new SyntaxException("circle/rectangle/img expected");

        Point2D pos = position();

        return new Create(element, pos);

    }

    private CircleConstr circle()
    {
        symbolCheck(Symbols.NUMBER);
        if(analyzer.getNumber()<0)
            throw new SyntaxException("Radius must be > 0");
        return new CircleConstr(analyzer.getNumber());

    }

    private RectangleConstr rectangle()
    {

        Point2D position = position();

        return new RectangleConstr(position);
    }

    private ImgConstr img()
    {

        String path = path();

        return new ImgConstr(path);
    }

    private Remove remove()
    {
        symbolCheck(Symbols.ID);
        return new Remove(analyzer.getString());
    }

    private Move move()
    {

        boolean isRelative;

        if ( nextSymbol == Symbols.MOVE )
            isRelative = false;
        else
            isRelative = true;

        symbolCheck(Symbols.ID);
        String objID = analyzer.getString();

        Point2D pos = position();


        return new Move(isRelative,objID,pos);

    }

    private Scale scale()
    {
        symbolCheck(Symbols.ID);
        String objID = analyzer.getString();

        symbolCheck(Symbols.NUMBER);
        double scale = analyzer.getNumber();
        if(analyzer.getNumber()<0)
            throw new SyntaxException("Scale factor must be > 0");

        return new Scale(objID,scale);
    }

    private List list()
    {
        nextSymbol = analyzer.getSymbol();
        if ( nextSymbol == Symbols.ALL )
            return new ListAll();
        if ( nextSymbol == Symbols.GROUPS )
            return new ListGroups();
        if ( nextSymbol == Symbols.ID)
            return new ListObj(analyzer.getString());
        if ( nextSymbol == Symbols.CIRCLE || nextSymbol == Symbols.IMG || nextSymbol == Symbols.RECTANGLE)
            return new ListType(nextSymbol.name());
        else
            throw new SyntaxException("objID/type/all/groups expected");
    }

    private Group group()
    {
        Set<String> objList = new HashSet<>();
        do {
            symbolCheck(Symbols.ID);
            objList.add(analyzer.getString());
            nextSymbol = analyzer.getSymbol();
            if(nextSymbol != Symbols.EOF && nextSymbol != Symbols.COMMA)
                throw new SyntaxException("COMMA or EOF expected, got "+ nextSymbol);
        }while(nextSymbol == Symbols.COMMA);

        return new Group(objList);
    }

    private Ungroup ungroup()
    {
        symbolCheck(Symbols.ID);
        return new Ungroup(analyzer.getString());
    }

    private Area area()
    {
        nextSymbol = analyzer.getSymbol();
        if ( nextSymbol == Symbols.ALL )
            return new AreaAll();
        if ( nextSymbol == Symbols.ID)
            return new AreaObj(analyzer.getString());
        if ( nextSymbol == Symbols.CIRCLE ||
             nextSymbol == Symbols.RECTANGLE ||
             nextSymbol == Symbols.IMG)
            return new AreaType(nextSymbol.name());
        else
            throw new SyntaxException("objID/type/all expected");
    }

    private Perimeter perimeter()
    {
        nextSymbol = analyzer.getSymbol();
        if ( nextSymbol == Symbols.ALL )
            return new PerimeterAll();
        if ( nextSymbol == Symbols.ID)
            return new PerimeterObj(analyzer.getString());
        if ( nextSymbol == Symbols.CIRCLE ||
             nextSymbol == Symbols.RECTANGLE ||
             nextSymbol == Symbols.IMG
            )
            return new PerimeterType(nextSymbol.name());
        else
            throw new SyntaxException("objID/type/all expected");
    }



    private String path()
    {
        symbolCheck(Symbols.QUOTE);
        return analyzer.getString();
    }

    private Point2D position()
    {

        symbolCheck(Symbols.OPEN_BRACKET);
        symbolCheck(Symbols.NUMBER);
        if(analyzer.getNumber()<0)
            throw new SyntaxException("X must be > 0");
        double x = analyzer.getNumber();
        symbolCheck(Symbols.COMMA);
        symbolCheck(Symbols.NUMBER);
        if(analyzer.getNumber()<0)
            throw new SyntaxException("Y must be > 0");
        double y = analyzer.getNumber();
        symbolCheck(Symbols.CLOSE_BRACKET);

        return new Point2D.Double(x,y);
    }


    private void symbolCheck(Symbols s)
    {
        nextSymbol = analyzer.getSymbol();
        if (nextSymbol != s) {
            String msg = " found " + nextSymbol + " but was expecting " + s;
            throw new SyntaxException(msg);
        }
    }// symbolCheck

    public Command getCmd()
    {
        return this.cmd;
    }

}
