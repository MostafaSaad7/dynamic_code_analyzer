import org.antlr.v4.runtime.TokenStreamRewriter;

/**
 * This is the detailed description for this class.
 * This class provide implementation for a subset of the available methods provided in {@link JavaParserListener}.
 *
 * This class <b>MyJavaCodeAugmenter</b> main goal is to augment the input java code used in a dynamic code analyzer
 * to handle flow control statements like while, for, if, and else with no blocks.
 *
 * \param parser JavaParser
 * \param rewriter TokenStreamRewriter
 */
public class MyJavaCodeAugmenter extends JavaParserBaseListener{


    JavaParser parser;
    TokenStreamRewriter rewriter;


    public MyJavaCodeAugmenter(JavaParser parser, TokenStreamRewriter rewriter) {
        this.parser = parser; this.rewriter = rewriter;
    }

    // handling for & if & else & while with no blocks
    /**
     *
     * This function is responsible for handling for & if & else & while with no blocks
     * by inserting curly brackets { } after and before the statements if there are no curly brackets.
     *
     * The production of the grammar rule is:
     * block: '{' blockStatement* '}';
     *
     * \param ctx Ctx that contains the children of this rule.
     *
     * \return {@link Void}
     */

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
