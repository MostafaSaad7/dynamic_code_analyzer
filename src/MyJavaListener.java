import org.antlr.v4.runtime.*;

public class MyJavaListener extends JavaParserBaseListener{

    JavaParser parser;
    TokenStreamRewriter rewriter;

    public MyJavaListener(JavaParser parser, TokenStreamRewriter rewriter) {
        this.parser = parser; this.rewriter = rewriter;
    }



}
