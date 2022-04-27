
package augmented_files;

import java.io.IOException;

import java.io.FileWriter;
public class FactorialAugmented
{

		static FileWriter myWriter;

	public static void main(String[] args)
	throws IOException{

		myWriter = new FileWriter("visited_blocks/FactorialAugmentedVisitedBlocks.txt");


		// block number: 1
		myWriter.write("Entered Block Number: 1\n");
	final int NUM_FACTS = 100;
		for(int i = 0; i < NUM_FACTS; i++)
			{


		// block number: 2
		myWriter.write("Entered Block Number: 2\n");
System.out.println( i + "! is " + factorial(i));
		}

	

		myWriter.close();
}
	
	public static int factorial(int n)
	throws IOException{

		// block number: 3
		myWriter.write("Entered Block Number: 3\n");
	int result = 1;
		for(int i = 2; i <= n; i++)
			{


		// block number: 4
		myWriter.write("Entered Block Number: 4\n");
result *= i;
		}

		return result;
	}
}