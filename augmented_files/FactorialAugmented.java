
import java.io.IOException;

import java.io.FileWriter;
public class FactorialAugmented
{

		static FileWriter myWriter;

	public static void main(String[] args)
	throws IOException{

		myWriter = new FileWriter("visited_blocks/FactorialAugmentedVisitedBlocks.txt");


		// block number: 0
		myWriter.write(0+" green\n");
	final int NUM_FACTS = 100;
		for(int i = 0; i < NUM_FACTS; i++)
			{

		// block number: 1
		myWriter.write(1+" green\n");

	    System.out.println( i + "! is " + factorial(i));
	}

	

		myWriter.close();
}
	
	public static int factorial(int n)
	throws IOException{

		// block number: 2
		myWriter.write(2+" green\n");
	int result = 1;
		for(int i = 2; i <= n; i++)
			{

		// block number: 3
		myWriter.write(3+" green\n");

	    result *= i;
	}

		return result;
	}
}