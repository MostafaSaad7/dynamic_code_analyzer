import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.io.*;
import java.util.Scanner;


public class AnalyzerDriver {

    public static void main(String[] args) throws IOException {

        ANTLRInputStream input = new ANTLRInputStream(System.in);
        JavaLexer lexer = new JavaLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JavaParser parser = new JavaParser(tokens);

        ParseTree tree = parser.compilationUnit();

        ParseTreeWalker walker = new ParseTreeWalker();

        // create token stream rewriter to inject code snippets
        TokenStreamRewriter rewriter = new TokenStreamRewriter(tokens);

        MyJavaListener extractor = new MyJavaListener(parser,rewriter);

        walker.walk(extractor, tree);

    }
}