import org.antlr.v4.runtime.TokenStreamRewriter;

public class MyJavaListener extends JavaParserBaseListener{


    JavaParser parser;
    TokenStreamRewriter rewriter;


    public MyJavaListener(JavaParser parser, TokenStreamRewriter rewriter) {
        this.parser = parser; this.rewriter = rewriter;
    }


    // handling for & if & else & while with no blocks
    @Override
    public void enterStatement(JavaParser.StatementContext ctx) {
        if(ctx.FOR() != null  || ctx.WHILE() != null || ctx.IF() != null)
        {

            int statementIndex = 0;

            if(!ctx.statement(statementIndex).getStart().getText().equals("{"))
            {

                rewriter.insertBefore(ctx.statement(statementIndex).getStart(), "{\n\t    ");

                rewriter.insertAfter(ctx.statement(statementIndex).getStop(), "\n\t}\n");
            }
        }

        if(ctx.ELSE() != null)
        {
            int statementIndex = 1;

            if(!ctx.statement(statementIndex).getStart().getText().equals("if"))
            {
                if(!ctx.statement(statementIndex).getStart().getText().equals("{"))
                {

                    rewriter.insertBefore(ctx.statement(statementIndex).getStart(), "{\n\t    ");

                    rewriter.insertAfter(ctx.statement(statementIndex).getStop(), "\n\t}\n");
                }
            }
        }

    }



}
