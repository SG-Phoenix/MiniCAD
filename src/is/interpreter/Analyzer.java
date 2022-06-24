package is.interpreter;

import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;

public class Analyzer {

    private StreamTokenizer input;
    private Symbols symbol;

    public Analyzer(Reader in) {

        input = new StreamTokenizer(in);
        input.resetSyntax();
        input.eolIsSignificant(false);
        input.wordChars('a', 'z');
        input.wordChars('A', 'Z');
        input.whitespaceChars('\u0000', ' ');
        input.ordinaryChar('(');
        input.ordinaryChar(')');
        input.quoteChar('"');
        input.parseNumbers();

    }

    public String getString() {
        return input.sval;
    }
    public Double getNumber() {
        return input.nval;
    }

    public Symbols getSymbol() {
        try {
            switch (input.nextToken()) {
                case StreamTokenizer.TT_EOF:
                    symbol = Symbols.EOF;
                    break;
                case StreamTokenizer.TT_NUMBER:
                    symbol = Symbols.NUMBER;
                    break;
                case StreamTokenizer.TT_WORD:
                    if (input.sval.equalsIgnoreCase("new"))
                        symbol = Symbols.CREATE;
                    else if (input.sval.equalsIgnoreCase("del"))
                        symbol = Symbols.REMOVE;
                    else if (input.sval.equalsIgnoreCase("mv"))
                        symbol = Symbols.MOVE;
                    else if (input.sval.equalsIgnoreCase("mvoff"))
                        symbol = Symbols.MOVEOFF;
                    else if (input.sval.equalsIgnoreCase("scale"))
                        symbol = Symbols.SCALE;
                    else if (input.sval.equalsIgnoreCase("ls"))
                        symbol = Symbols.LIST;
                    else if (input.sval.equalsIgnoreCase("grp"))
                        symbol = Symbols.GROUP;
                    else if (input.sval.equalsIgnoreCase("ungrp"))
                        symbol = Symbols.UNGROUP;
                    else if (input.sval.equalsIgnoreCase("area"))
                        symbol = Symbols.AREA;
                    else if (input.sval.equalsIgnoreCase("perimeter"))
                        symbol = Symbols.PERIMETER;
                    else if (input.sval.equalsIgnoreCase("circle"))
                        symbol = Symbols.CIRCLE;
                    else if (input.sval.equalsIgnoreCase("rectangle"))
                        symbol = Symbols.RECTANGLE;
                    else if (input.sval.equalsIgnoreCase("img"))
                        symbol = Symbols.IMG;
                    else if (input.sval.equalsIgnoreCase("all"))
                        symbol = Symbols.ALL;
                    else if (input.sval.equalsIgnoreCase("groups"))
                        symbol = Symbols.GROUPS;
                    else if( input.sval.matches("id[0-9]+"))
                        symbol = Symbols.ID;
                    else
                        symbol = Symbols.INVALID;
                    break;
                case '"':
                    symbol = Symbols.QUOTE;
                    break;
                case '(':
                    symbol = Symbols.OPEN_BRACKET;
                    break;
                case ')':
                    symbol = Symbols.CLOSE_BRACKET;
                    break;
                case ',':
                    symbol = Symbols.COMMA;
                    break;
                default:
                    symbol = Symbols.INVALID;
            }
        } catch (IOException e) {
            symbol = Symbols.EOF;
        }
        return symbol;
    }
}
