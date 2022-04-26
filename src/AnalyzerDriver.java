import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.io.*;
import java.util.Scanner;


public class AnalyzerDriver {

    public static void main(String[] args) throws IOException {

        // read java filename
        System.out.print("Enter java file path: ");
        Scanner sc = new Scanner(System.in);
        String fileName = sc.nextLine();

        // open file
        File file = new File(fileName);
        FileInputStream fis = null;


        try {
            // open the input file stream
            fis = new FileInputStream(file);

            // starter code
            ANTLRInputStream input = new ANTLRInputStream(fis);

            JavaLexer lexer = new JavaLexer(input);

            CommonTokenStream tokens = new CommonTokenStream(lexer);

            JavaParser parser = new JavaParser(tokens);

            ParseTree tree = parser.compilationUnit();

            // close the input file
            fis.close();

            ParseTreeWalker walker = new ParseTreeWalker();

            // create token stream rewriter to inject code snippets
            TokenStreamRewriter rewriter = new TokenStreamRewriter(tokens);

            System.out.println(rewriter.getText());

            MyJavaListener extractor = new MyJavaListener(parser,rewriter);

            walker.walk(extractor, tree);

            System.out.println(rewriter.getText());

            // save augmented code
            String augmentedFilePath = "./augmented_files/"+extractor.getNewClassName()+".java";
            FileWriter myWriter = new FileWriter(augmentedFilePath, false);
            myWriter.write(rewriter.getText());
            myWriter.close();


            // running the generated file automatically using a process
            try
            {
                Runtime.getRuntime().exec("java "+augmentedFilePath);
            }
            catch(IOException e)
            {
                System.err.println("Error on exec() method");
                e.printStackTrace();
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}