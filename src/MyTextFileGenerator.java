import org.antlr.v4.runtime.*;

/**
 *
 * <p>This is the detailed description for this class.</p>
 * This class provide implementation for a subset of the available methods provided in {@link JavaParserListener}.
 *
 * This class <b>MyTextFileGenerator</b> main goal is to augment the input java code to generate
 * a text file containing the number of the visited blocks that can be used in building a dynamic code analyzer for Java.
 *
 * \param parser {@link JavaParser}
 * \param rewriter {@link TokenStreamRewriter}
 *
 */

public class MyTextFileGenerator extends JavaParserBaseListener{

    boolean fileWriterImport = false;
    boolean ioExceptionImport = false;
    boolean isFlowControlStatement = false;

    JavaParser parser;
    TokenStreamRewriter rewriter;

    int blockNumber= 0;
    String newClassName = "augmented";

    public MyTextFileGenerator(JavaParser parser, TokenStreamRewriter rewriter) {
        this.parser = parser; this.rewriter = rewriter;
    }


    /**
     * This function is responsible for handling required imports if there are no other import statements.
     *
     * The production of the grammar rule is : \n\n
     * compilationUnit \n
     *     : packageDeclaration? importDeclaration* typeDeclaration* \n
     *     | moduleDeclaration EOF \n
     *     ; \n
     *
     * \param ctx Ctx that contain the children of this rule
     *
     * \return {@link Void}
     */
    @Override
    public void enterCompilationUnit(JavaParser.CompilationUnitContext ctx) {

        if(ctx.importDeclaration().isEmpty())
        {
            if(ctx.packageDeclaration() == null)
            {

                rewriter.insertBefore(ctx.getStart(), "\nimport java.io.FileWriter;\n");
                rewriter.insertBefore(ctx.getStart(), "\nimport java.io.IOException;\n");
            }
            else
            {
                rewriter.insertAfter(ctx.packageDeclaration().getStop(), "\nimport java.io.FileWriter;\n");
                rewriter.insertAfter(ctx.packageDeclaration().getStop(), "\nimport java.io.IOException;\n");
            }

            fileWriterImport = true;
            ioExceptionImport = true;

        }
    }


    /**
     * This function is responsible for handling augmented class name. \n
     * it creates new name for the class to be saved with
     *
     * The production of the grammar rule is : \n\n
     *
     * classDeclaration \n
     *     : CLASS identifier typeParameters?\n
     *       (EXTENDS typeType)?\n
     *       (IMPLEMENTS typeList)?\n
     *       (PERMITS typeList)? // Java17\n
     *       classBody\n
     *     ;\n
     *
     * \param ctx Ctx that contain the children of this rule
     *
     * \return {@link Void}
     */
    @Override
    public void enterClassDeclaration(JavaParser.ClassDeclarationContext ctx) {
        newClassName = ctx.identifier().getText()+"Augmented";
        rewriter.replace(ctx.identifier().getStart(),ctx.identifier().getStop(),newClassName);

        rewriter.insertAfter(ctx.classBody().getStart(), "\n\n\t\tstatic FileWriter myWriter;\n");

    }


    /**
     * This function is responsible for handling exceptions to throw IOException \n
     * also make sure to initialize the FileWriter in the main function.
     *
     * The production of the grammar rule is : \n\n
     * methodDeclaration
     *     : typeTypeOrVoid identifier formalParameters ('[' ']')*
     *       (THROWS qualifiedNameList)?
     *       methodBody
     *     ;\n
     *
     * \param ctx Ctx that contain the children of this rule
     *
     * \return {@link Void}
     */
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

    /**
     * This function is responsible for injecting code snippet for closing the text file at main function
     *
     * \param  ctx  that contains the children of this rule
     *
     * \return {@link Void}
     */
    @Override
    public void exitMethodDeclaration(JavaParser.MethodDeclarationContext ctx) {
        String methodName = ctx.identifier().getText();

        if(methodName.equals("main"))
        {
            rewriter.insertBefore(ctx.methodBody().getStop(), "\n\n\t\tmyWriter.close();\n");
        }
    }


    /**
     * This function is responsible for preventing imports duplications by checking if FileWriter or IOException classes already imported
     *
     * \param  ctx  that contains the children of this rule
     *
     * \return {@link Void}
     */
    @Override
    public void enterImportDeclaration(JavaParser.ImportDeclarationContext ctx) {
        if(ctx.qualifiedName().getText().equals("java.io.FileWriter"))
            fileWriterImport = true;

        if(ctx.qualifiedName().getText().equals("java.io.IOException"))
            ioExceptionImport = true;
    }


    /**
     * This function is responsible for handling imports if there are other import statements by inserting imports before it
     *
     * \param  ctx  that contains the children of this rule
     *
     * \return {@link Void}
     */
    @Override
    public void enterTypeDeclaration(JavaParser.TypeDeclarationContext ctx) {
        super.enterTypeDeclaration(ctx);
        if(fileWriterImport == false)
        {
            rewriter.insertBefore(ctx.getStart(), "\nimport java.io.FileWriter;\n");
        }
        if(ioExceptionImport == false)
        {
            rewriter.insertBefore(ctx.getStart(), "\nimport java.io.IOException;\n");
        }
    }

    /**
     * This function is responsible for injecting the code snippet that write the entered block number and its color in a text file. \n
     *
     * the production of the grammar rule is :
     * block :
     * '{' blockStatement* '}' ;
     *
     * \param ctx Ctx that contain the children of this rule
     *
     * \return {@link Void}
     */
    @Override
    public void enterBlock(JavaParser.BlockContext ctx) {

        if(!isFlowControlStatement)
        {
            // inject file write code
            String insertCode = "// block number: "+blockNumber+"\n\t\tmyWriter.write("+ blockNumber++ +"+\" green\\n\");";

            rewriter.insertAfter(ctx.getStart(), "\n\n\t\t" + insertCode + "\n");
        }

        isFlowControlStatement = false;
    }


    // injecting Entered blocked code


    /**
     * This function is responsible for handling branch coverage by
     * injecting entered block number and its color in a text file, the color will either be Orange or Green depending on the expression. \n
     *
     * the production of the grammar rule is : \n
     * statement : \n
     * IF parExpression statement (ELSE statement)? \n
     * | FOR '(' forControl ')' statement \n
     * | WHILE parExpression statement;
     *
     * \param ctx Ctx that contain the children of this rule
     *
     * \return {@link Void}
     */
    @Override
    public void enterStatement(JavaParser.StatementContext ctx) {


        if (ctx.FOR() != null || ctx.WHILE() != null || ctx.IF() != null)
        {

            String expression = "";
            if (ctx.FOR() != null)
            {

                expression  = ctx.forControl().expression().getText().trim();
            }
            else if(ctx.WHILE() != null || ctx.IF() != null)
            {
                expression = ctx.parExpression().expression().getText().trim();
            }

            int lastOrIndex = expression.lastIndexOf("||");
            if (lastOrIndex != -1)
            {
                isFlowControlStatement = true;
                String newExpr = expression.substring(0, lastOrIndex);

                String colorCodeOrange = "\n\t\t// block number: " + blockNumber + "\n\t\tmyWriter.write(" + blockNumber + "+\" orange\\n\");";
                String colorCodeGreen = "\n\t\t// block number: " + blockNumber + "\n\t\tmyWriter.write(" + blockNumber + "+\" green\\n\");";

                String insertCondition = "\n\t\t\tif(" + newExpr + "){\n" + colorCodeOrange + "\n\t\t\t}\n" + "\t\t\telse {\n" + colorCodeGreen + "\n\t\t\t}";

                rewriter.insertAfter(ctx.statement(0).getStart(), insertCondition);
                blockNumber++;

            }
        }
    }

    /**
     * This function responsible for returning the new class name which is augmented in
     *  {@link #enterClassDeclaration} function.
     *  \return newClassName {@link String}
     */

    public String getNewClassName()
    {

        return newClassName;
    }


}
