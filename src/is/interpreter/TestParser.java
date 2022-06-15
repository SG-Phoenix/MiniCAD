package is.interpreter;

import is.Main;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
public class TestParser
{
    private Parser parser;
    @Before
    public void init()
    {
        parser = new Parser();
    }

    @Test
    public void testCreateCircle()
    {
        assertThrows(SyntaxException.class, () ->
        {
            parser.parse("create circle 10) (100,30)");
        });
        assertThrows(SyntaxException.class, () ->
        {
            parser.parse("create circle (10 (100,30)");
        });
        assertThrows(SyntaxException.class, () ->
        {
            parser.parse("create circle (notanumber) (100,30)");
        });

        assertThrows(SyntaxException.class, () ->
        {
            parser.parse("create circle (10) 100,30)");
        });

        assertThrows(SyntaxException.class, () ->
        {
            parser.parse("create circle (10) (notanumber,30)");
        });

        assertThrows(SyntaxException.class, () ->
        {
            parser.parse("create circle (10) (100 30)");
        });

        assertThrows(SyntaxException.class, () ->
        {
            parser.parse("create circle (10) (100,notanumber)");
        });

        assertThrows(SyntaxException.class, () ->
        {
            parser.parse("create circle (10) (100,30");
        });

        parser.parse("create circle (10) (100,30)");

        assertThrows(SyntaxException.class, () ->
        {
            parser.parse("create circle (10) (100,30) noteof");
        });
    }

    @Test
    public void testCreateRectangle()
    {
        assertThrows(SyntaxException.class, () ->
        {
            parser.parse("create rectangle 30,10) (100,30)");
        });
        assertThrows(SyntaxException.class, () ->
        {
            parser.parse("create rectangle (notanumber, 10) (100,30)");
        });
        assertThrows(SyntaxException.class, () ->
        {
            parser.parse("create rectangle (30 10) (100,30)");
        });
        assertThrows(SyntaxException.class, () ->
        {
            parser.parse("create rectangle (30,notanumber) (100,30)");
        });
        assertThrows(SyntaxException.class, () ->
        {
            parser.parse("create rectangle (30,10) 100,30)");
        });

        assertThrows(SyntaxException.class, () ->
        {
            parser.parse("create rectangle (30,10) (notanumber,30)");
        });

        assertThrows(SyntaxException.class, () ->
        {
            parser.parse("create rectangle (30,10) (100 30)");
        });

        assertThrows(SyntaxException.class, () ->
        {
            parser.parse("create rectangle (30,10) (100,notanumber)");
        });
        assertThrows(SyntaxException.class, () ->
        {
            parser.parse("create rectangle (30,10) (100,30");
        });

        parser.parse("create rectangle (30,10) (100,30)");

        assertThrows(SyntaxException.class, () ->
        {
            parser.parse("create rectangle (30,10) (100,30) noteof");
        });
    }

    @Test
    public void testCreateImg()
    {
        String path = Main.class.getResource("shapes/model/NyaNya.gif").getPath();
        assertThrows(SyntaxException.class, () ->
        {
            parser.parse("create img \""+path+"\") (100,100)");
        });
        assertThrows(SyntaxException.class, () ->
        {
            parser.parse("create img ("+path+"\") (100,30)");
        });
        assertThrows(SyntaxException.class, () ->
        {
            parser.parse("create img (\""+path+") (100,30)");
        });
        assertThrows(SyntaxException.class, () ->
        {
            parser.parse("create img (\""+path+"\" (100,30)");
        });
        assertThrows(SyntaxException.class, () ->
        {
            parser.parse("create img (\""+path+"\") 100,30)");
        });

        assertThrows(SyntaxException.class, () ->
        {
            parser.parse("create img (\""+path+"\") (notanumber,30)");
        });

        assertThrows(SyntaxException.class, () ->
        {
            parser.parse("create img (\""+path+"\") (100 30)");
        });

        assertThrows(SyntaxException.class, () ->
        {
            parser.parse("create img (\""+path+"\") (100,notanumber)");
        });
        assertThrows(SyntaxException.class, () ->
        {
            parser.parse("create img (\""+path+"\") (100,30");
        });

        parser.parse("create img (\""+path+"\") (100,30)");

        assertThrows(SyntaxException.class, () ->
        {
            parser.parse("create img (\""+path+"\") (100,30) noteof");
        });
    }

    @Test
    public void testRemove()
    {
        assertThrows(SyntaxException.class, () ->
        {
            parser.parse("del notanid");
        });

        parser.parse("del id0");

        assertThrows(SyntaxException.class, () ->
        {
            parser.parse("del id0 noteof");
        });
    }

    @Test
    public void testMove()
    {
        assertThrows(SyntaxException.class, () ->
        {
            parser.parse("mv notanid (20,40)");
        });

        assertThrows(SyntaxException.class, () ->
        {
            parser.parse("mvoff notanid (20,40)");
        });

        assertThrows(SyntaxException.class, () ->
        {
            parser.parse("mv id0 20,40)");
        });

        assertThrows(SyntaxException.class, () ->
        {
            parser.parse("mvoff id0 20,40)");
        });

        assertThrows(SyntaxException.class, () ->
        {
            parser.parse("mv id0 (notanumber,40)");
        });

        assertThrows(SyntaxException.class, () ->
        {
            parser.parse("mvoff id0 (notanumber,40)");
        });

        assertThrows(SyntaxException.class, () ->
        {
            parser.parse("mv id0 (20 40)");
        });

        assertThrows(SyntaxException.class, () ->
        {
            parser.parse("mvoff id0 (20 40)");
        });

        assertThrows(SyntaxException.class, () ->
        {
            parser.parse("mv id0 (20,notanumber)");
        });

        assertThrows(SyntaxException.class, () ->
        {
            parser.parse("mvoff id0 (20,notanumber)");
        });

        assertThrows(SyntaxException.class, () ->
        {
            parser.parse("mv id0 (20,40");
        });

        assertThrows(SyntaxException.class, () ->
        {
            parser.parse("mvoff id0 (20,40");
        });


        parser.parse("mvoff id0 (20,40)");
        parser.parse("mv id0 (20,40)");

        assertThrows(SyntaxException.class, () ->
        {
            parser.parse("mvoff id0 (20,40) noteof");
        });
        assertThrows(SyntaxException.class, () ->
        {
            parser.parse("mv id0 (20,40) noteof");
        });
    }

    @Test
    public void testScale()
    {
        assertThrows(SyntaxException.class, () ->
        {
            parser.parse("scale notanid 1.2");
        });
        assertThrows(SyntaxException.class, () ->
        {
            parser.parse("scale id0 notanumber");
        });

        parser.parse("scale id0 1.2");

        assertThrows(SyntaxException.class, () ->
        {
            parser.parse("scale id0 1.2 noteof");
        });
    }

    @Test
    public void testList()
    {
        assertThrows(SyntaxException.class, () ->
        {
            parser.parse("ls notavalidsymbol");
        });

        parser.parse("ls all");
        parser.parse("ls id0");
        parser.parse("ls circle");
        parser.parse("ls groups");

        assertThrows(SyntaxException.class, () ->
        {
            parser.parse("ls all noteof");
        });
    }

    @Test
    public void testGroup()
    {
        assertThrows(SyntaxException.class, () ->
        {
            parser.parse("grp notanid");
        });

        assertThrows(SyntaxException.class, () ->
        {
            parser.parse("grp id0,notanid");
        });
        assertThrows(SyntaxException.class, () ->
        {
            parser.parse("grp id0 id1");
        });

        parser.parse("grp id0");
        parser.parse("grp id0,id1");

        assertThrows(SyntaxException.class, () ->
        {
            parser.parse("grp id0,id1 noteof");
        });
    }

    @Test
    public void testUngroup()
    {
        assertThrows(SyntaxException.class, () ->
        {
            parser.parse("ungrp notanid");
        });

        parser.parse("ungrp id0");

        assertThrows(SyntaxException.class, () ->
        {
            parser.parse("grp id0,id1 noteof");
        });
    }

    @Test
    public void testArea()
    {
        assertThrows(SyntaxException.class, () ->
        {
            parser.parse("area notavalidsymbol");
        });

        parser.parse("area all");
        parser.parse("area id0");
        parser.parse("area circle");

        assertThrows(SyntaxException.class, () ->
        {
            parser.parse("area all noteof");
        });
    }

    @Test
    public void testPerimeter()
    {
        assertThrows(SyntaxException.class, () ->
        {
            parser.parse("perimeter notavalidsymbol");
        });

        parser.parse("perimeter all");
        parser.parse("perimeter id0");
        parser.parse("perimeter circle");

        assertThrows(SyntaxException.class, () ->
        {
            parser.parse("perimeter all noteof");
        });
    }
}
