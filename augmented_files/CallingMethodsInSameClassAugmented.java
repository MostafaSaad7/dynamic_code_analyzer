
package augmented_files;
/* CallingMethodsInSameClass.java
 *
 * illustrates how to call static methods a class
 * from a method in the same class
 */


import java.io.IOException;

import java.io.FileWriter;
public class CallingMethodsInSameClassAugmented
{

		static FileWriter myWriter;

	public static void main(String[] args) throws IOException{

		myWriter = new FileWriter("visited_blocks/CallingMethodsInSameClassAugmentedVisitedBlocks.txt");


		// block number: 1
		myWriter.write("Entered Block Number: 1\n");

		printOne();
		printOne();
		printTwo();
	

		myWriter.close();
}

	public static void printOne() throws IOException{

		// block number: 2
		myWriter.write("Entered Block Number: 2\n");

		System.out.println("Hello World");
	}

	public static void printTwo() throws IOException{

		// block number: 3
		myWriter.write("Entered Block Number: 3\n");

		printOne();
		printOne();
	}
}