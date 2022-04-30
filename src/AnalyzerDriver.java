import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;


/**
 *
 * <p>This is the detailed description for this class.</p>
 *
 * <p>This class <b>AnalyzerDriver</b> works as the main driver for a dynamic code analyzer for Java programming language.</p>
 *
 * This class is responsible for taking a java file path from the user and generating a text file that contains the
 * details of the visited blocks, and generating an html file that contains statement and branches code coverage reports.
 *
 */

public class AnalyzerDriver {

    /**
     *
     * This function acts as the driver for {@link AnalyzerDriver} class.
     *
     * \return {@link Void}
     *
     */

    public static void main(String[] args) throws IOException, InterruptedException {

        // get augmented code after handling for & if & else & while with no blocks
        String augmentedJavaCode = augmentedCode();

        // deliverable 2 (generate a text files for visited blocks)
        String textFileName = textFileGenerator(augmentedJavaCode);

        // wait for the text file with visited block to be saved
        TimeUnit.SECONDS.sleep(1);

        // deliverable 3 (generate html file for statement & branches code converge)
        htmlGenerator(augmentedJavaCode, textFileName);

    }

    /**
     *
     * This function acts as the driver for {@link MyJavaCodeAugmenter} class.
     *
     * This function prompts the user for the path of a java file and initialize an instance of {@link MyJavaCodeAugmenter}
     * to handle the flow control statements like [for & if & else & while] with no blocks and returns the augmented code.
     *
     *
     * \return augmentedJavaCode {@link String}
     *
     */
    public static String augmentedCode() throws IOException {

        // read java filename
        System.out.print("Enter java file path: ");
        Scanner sc = new Scanner(System.in);
        String fileName = sc.nextLine();

        // open file
        File file = new File(fileName);
        FileInputStream fis = null;

        // open the input file stream
        fis = new FileInputStream(file);

        // starter code
        ANTLRInputStream input = new ANTLRInputStream(fis);

        JavaLexer lexer = new JavaLexer(input);

        CommonTokenStream tokens = new CommonTokenStream(lexer);

        JavaParser parser = new JavaParser(tokens);

        ParseTree tree = parser.compilationUnit();

        ParseTreeWalker walker = new ParseTreeWalker();

        // create token stream rewriter to inject code snippets
        TokenStreamRewriter rewriter = new TokenStreamRewriter(tokens);

        //original code
//        System.out.println(rewriter.getText());

        MyJavaCodeAugmenter extractor = new MyJavaCodeAugmenter(parser,rewriter);

        walker.walk(extractor, tree);

        // modified code
//        System.out.println(rewriter.getText());

        fis.close();

        return rewriter.getText();
    }

    /**
     *
     * This function acts as the driver for {@link MyTextFileGenerator} class.
     *
     * This function takes a java code as an argument and initialize an instance of {@link MyTextFileGenerator} to
     * generate an augmented java program that is responsible for generating a text file containing the details of
     * the visited code blocks.
     *
     *
     * \param javaCode {@link String}
     *
     * \return textFileName {@link String}
     *
     */
    public static String textFileGenerator(String code) throws IOException {

        // starter code
        ANTLRInputStream input = new ANTLRInputStream(code);

        JavaLexer lexer = new JavaLexer(input);

        CommonTokenStream tokens = new CommonTokenStream(lexer);

        JavaParser parser = new JavaParser(tokens);

        // create token stream rewriter to inject code snippets
        TokenStreamRewriter textFileRewriter = new TokenStreamRewriter(tokens);

        ParseTree tree = parser.compilationUnit();

        ParseTreeWalker walker = new ParseTreeWalker();

        MyTextFileGenerator extractor = new MyTextFileGenerator(parser,textFileRewriter);

        walker.walk(extractor, tree);

        // code after injecting visited blocks
//            System.out.println(textFileRewriter.getText());

        // save augmented code
        String augmentedFilePath = "./augmented_files/"+extractor.getNewClassName()+".java";
        FileWriter myWriter = new FileWriter(augmentedFilePath, false);
        myWriter.write(textFileRewriter.getText());
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

        return extractor.getNewClassName();
    }

    /**
     * <b>This function is responsible for generating html file</b><br>
     * read the visited blocks from the text file to assign color for each block.<br>
     * generate a html with colored blocks.<br>
     * running the generated html file automatically using a desired browser.
     * @param code this is the augmented java code.
     * @param textFileName this is the augmented file name.
     * @throws IOException throw an I/O exception if a file does not exist.
     * @return {@link Void}
     */
    public static void htmlGenerator(String code, String textFileName) throws IOException {

        Map<Integer, String> enteredBlocks = new HashMap<Integer, String>();

        // starter code
        ANTLRInputStream input = new ANTLRInputStream(code);

        JavaLexer lexer = new JavaLexer(input);

        CommonTokenStream tokens = new CommonTokenStream(lexer);

        JavaParser parser = new JavaParser(tokens);

        // create token stream rewriter to inject code snippets
        TokenStreamRewriter htmlRewriter = new TokenStreamRewriter(tokens);

        ParseTree tree = parser.compilationUnit();

        ParseTreeWalker walker = new ParseTreeWalker();

        // read visited blocks from the text file
        FileInputStream textFis=new FileInputStream("visited_blocks/"+textFileName+"VisitedBlocks.txt");    //creates a new file instance
        Scanner textFileScanner=new Scanner(textFis);
        while(textFileScanner.hasNextLine())
        {
            String[] mapData = textFileScanner.nextLine().split(" ");

            int blockNum = Integer.parseInt(mapData[0]);

            if(enteredBlocks.containsKey(blockNum) && enteredBlocks.get(blockNum).trim().equals("green"))
                continue;

            enteredBlocks.put(blockNum, mapData[1].trim());
        }
        textFileScanner.close();    //closes the stream and release the resources


        MyHTMLGenerator htmlGenerator = new MyHTMLGenerator(parser,htmlRewriter, enteredBlocks);

        walker.walk(htmlGenerator, tree);

        //html cod
//        System.out.println(htmlRewriter.getText());

        // save html code
        String htmlFilePath = "./html_files/"+textFileName+".html";
        FileWriter myWriter = new FileWriter(htmlFilePath, false);
        myWriter.write(htmlRewriter.getText());
        myWriter.close();

        // running the generated html file automatically using the desired browser
        File htmlFile = new File("html_files/"+textFileName+".html");
        Desktop.getDesktop().browse(htmlFile.toURI());
    }
}