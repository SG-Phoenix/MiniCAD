package is.interpreter;

import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.io.StringReader;

public class TestMain {

    public static void main(String... args) throws IOException {
        String test = "create img (\"/shapes/model/NyaNya.gif\") (15.0,15.0)";
        Reader reader = new StringReader(test);
        Parser parser = new Parser();
        parser.setInput(reader);

        System.out.println(parser.getCommand().toString());
    }
}
