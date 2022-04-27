import org.antlr.v4.runtime.*;

public class MyJavaListener extends JavaParserBaseListener{

    boolean fileWriterImport = false;
    boolean ioExceptionImport = false;

    JavaParser parser;
    TokenStreamRewriter rewriter;

    int blockNumber= 1;
    String newClassName = "augmented";

    public MyJavaListener(JavaParser parser, TokenStreamRewriter rewriter) {
        this.parser = parser; this.rewriter = rewriter;
    }


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

        }
        rewriter.insertBefore(0, "\npackage augmented_files;\n");
    }


    @Override
    public void enterClassDeclaration(JavaParser.ClassDeclarationContext ctx) {
        newClassName = ctx.identifier().getText()+"Augmented";
        rewriter.replace(ctx.identifier().getStart(),ctx.identifier().getStop(),newClassName);

        rewriter.insertAfter(ctx.classBody().getStart(), "\n\n\t\tstatic FileWriter myWriter;\n");

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
    public void enterImportDeclaration(JavaParser.ImportDeclarationContext ctx) {
        if(ctx.qualifiedName().getText().equals("java.io.FileWriter"))
            fileWriterImport = true;

        if(ctx.qualifiedName().getText().equals("java.io.IOException"))
            ioExceptionImport = true;
    }

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


    @Override
    public void enterBlock(JavaParser.BlockContext ctx) {

        // inject file write code
        String insertCode = "// block number: "+blockNumber+"\n\t\tmyWriter.write(\"Entered Block Number: "+ blockNumber++ +"\\n\");";

        rewriter.insertAfter(ctx.getStart(), "\n\n\t\t" + insertCode + "\n");
    }


    // handling for & if & else & while with no blocks
    @Override
    public void enterStatement(JavaParser.StatementContext ctx) {
        if(ctx.FOR() != null  || ctx.WHILE() != null || ctx.IF() != null)
        {
            int statementIndex = 0;

            if(!ctx.statement(statementIndex).getStart().getText().equals("{"))
            {

                String insertCode = "// block number: "+blockNumber+"\n\t\tmyWriter.write(\"Entered Block Number: "+ blockNumber++ +"\\n\");";

                rewriter.insertBefore(ctx.statement(statementIndex).getStart(), "\n\n\t\t" + insertCode + "\n");

                rewriter.insertBefore(ctx.statement(statementIndex).getStart(), "{\n");

                rewriter.insertAfter(ctx.statement(statementIndex).getStop(), "\n\t\t}\n");
            }
        }

        if(ctx.ELSE() != null)
        {
            if(!ctx.statement(1).getStart().getText().equals("if"))
            {
                int statementIndex = 1;

                if(!ctx.statement(statementIndex).getStart().getText().equals("{"))
                {

                    String insertCode = "// block number: "+blockNumber+"\n\t\tmyWriter.write(\"Entered Block Number: "+ blockNumber++ +"\\n\");";

                    rewriter.insertBefore(ctx.statement(statementIndex).getStart(), "\n\n\t\t" + insertCode + "\n");

                    rewriter.insertBefore(ctx.statement(statementIndex).getStart(), "{\n");

                    rewriter.insertAfter(ctx.statement(statementIndex).getStop(), "\n\t\t}\n");
                }
            }
        }

    }

    public String getNewClassName()
    {
        return newClassName;
    }


}
