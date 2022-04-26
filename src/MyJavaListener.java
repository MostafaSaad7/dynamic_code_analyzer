import org.antlr.v4.runtime.*;

public class MyJavaListener extends JavaParserBaseListener{

    JavaParser parser;
    TokenStreamRewriter rewriter;

    int blockNumber= 1;
    String newClassName;

    public MyJavaListener(JavaParser parser, TokenStreamRewriter rewriter) {
        this.parser = parser; this.rewriter = rewriter;
    }

    @Override
    public void enterMethodDeclaration(JavaParser.MethodDeclarationContext ctx) {
        String methodName = ctx.identifier().getText();

        if(methodName.equals("main"))
        {
            rewriter.insertAfter(ctx.methodBody().getStart(), "\n\n\t\tmyWriter = new FileWriter(\"visited_blocks/"+newClassName+"VisitedBlocks.txt\");\n");
        }

        if(ctx.THROWS() != null)
        {
            String exceptions = ctx.qualifiedNameList().getText();
            if(!exceptions.contains("IOException"))
            {
                rewriter.insertAfter(ctx.THROWS().getSymbol(), " IOException,");
            }
        }
        else
        {
            rewriter.insertBefore(ctx.methodBody().getStart(), "throws IOException");
        }
    }


    @Override
    public void exitMethodDeclaration(JavaParser.MethodDeclarationContext ctx) {
        String methodName = ctx.identifier().getText();

        if(methodName.equals("main"))
        {
            rewriter.insertBefore(ctx.methodBody().getStop(), "\n\n\t\tmyWriter.close();\n");
        }
    }


    @Override
    public void enterBlock(JavaParser.BlockContext ctx) {

        // inject file write code
        String insertCode = "// block number: "+blockNumber+"\n\t\tmyWriter.write(\"Entered Block Number: "+ blockNumber++ +"\\n\");";

        rewriter.insertAfter(ctx.getStart(), "\n\n\t\t" + insertCode + "\n");
    }



}
