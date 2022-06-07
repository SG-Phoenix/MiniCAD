package is.interpreter;

import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.io.StringReader;

public class TestMain {

    public static void main(String... args) throws IOException {
        String test = "perimeter all";
        Reader reader = new StringReader(test);
        Parser parser = new Parser(reader);

        System.out.println(parser.getCommand().toString());
    }
}
