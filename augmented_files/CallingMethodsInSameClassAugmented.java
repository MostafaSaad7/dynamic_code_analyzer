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


		// block number: 0
		myWriter.write(0+" green\n");

		printOne();
		printOne();
		printTwo();
	

		myWriter.close();
}

	public static void printOne() throws IOException{

		// block number: 1
		myWriter.write(1+" green\n");

		System.out.println("Hello World");
	}

	public static void printTwo() throws IOException{

		// block number: 2
		myWriter.write(2+" green\n");

		printOne();
		printOne();
	}
}