
import org.antlr.v4.runtime.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * <b>This is the detailed description for this class</b><br>
 * This class provide implementation for a subset of the available methods provided in {@link JavaParserBaseListener}.<br>
 * This class <b>MyHTMLGenerator</b> main goal is to generate an HTML with highlighted red/green/orange lines.
 * \param parser JavaParser.
 * \param rewriter TokenStreamRewriter.
 * \param enteredBlocks map that assign each entered block to a color.
 */
public class MyHTMLGenerator extends JavaParserBaseListener {

    JavaParser parser;
    TokenStreamRewriter rewriter;
    Map<Integer, String> enteredBlocks;
    int blockNumber = 0;

    public MyHTMLGenerator(JavaParser parser, TokenStreamRewriter rewriter, Map<Integer, String> enteredBlocks) {
        this.parser = parser;
        this.rewriter = rewriter;
        this.enteredBlocks = enteredBlocks;
    }

    /**
     * <b>This function is responsible for generating html header</b><br>
     * set default background green color for the java file by rapping it with pre html tag.
     * @param ctx that contain the children of this rule.
     * @return {@link Void}
     */
    @Override
    public void enterCompilationUnit(JavaParser.CompilationUnitContext ctx) {
        String HTTMLHeader = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "    <title>\n" +
                "        Background\n" +
                "    </title>\n" +
                "    <body style=\"margin: 0%;\">\n" +
                "        <pre style=\"background-color: rgb(181, 248, 190); margin: 0%;\">";
        rewriter.insertBefore(ctx.getStart(), "\n" + HTTMLHeader + "\n");
    }

    /**
     *<b>This function is responsible for generating html footer</b><br>
     * adding html footer at the end of the java file.
     * @param ctx that contain the children of this rule.
     * @return {@link Void}
     */
    @Override
    public void exitCompilationUnit(JavaParser.CompilationUnitContext ctx) {
        String HTMLFooter = "</pre>\n" +
                "    </body>\n" +
                "</html>";
        rewriter.insertAfter(ctx.getStop(), "\n" + HTMLFooter + "\n");
    }
}
