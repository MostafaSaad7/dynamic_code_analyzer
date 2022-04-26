import org.antlr.v4.runtime.*;

public class MyJavaListener extends JavaParserBaseListener{

    JavaParser parser;
    TokenStreamRewriter rewriter;

    int blockNumber= 1;

    public MyJavaListener(JavaParser parser, TokenStreamRewriter rewriter) {
        this.parser = parser; this.rewriter = rewriter;
    }


    @Override
    public void enterBlock(JavaParser.BlockContext ctx) {

        // inject file write code
        String insertCode = "// block number: "+blockNumber+"\n\t\tmyWriter.write(\"Entered Block Number: "+ blockNumber++ +"\\n\");";

        rewriter.insertAfter(ctx.getStart(), "\n\n\t\t" + insertCode + "\n");
    }



}
