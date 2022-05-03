
import java.io.IOException;

import java.io.FileWriter;
public class PrimitiveParametersAugmented
{

		static FileWriter myWriter;
	
	public static void main(String[] args)
	throws IOException{

		myWriter = new FileWriter("visited_blocks/PrimitiveParametersAugmentedVisitedBlocks.txt");


		// block number: 0
		myWriter.write(0+" green\n");
	go();
	

		myWriter.close();
}
	
	public static void go()
	throws IOException{

		// block number: 1
		myWriter.write(1+" green\n");
	int x = 3;
		int y = 2;
		System.out.println("In method go. x: " + x + " y: " + y);
		falseSwap(x,y);
		System.out.println("in method go. x: " + x + " y: " + y);
//		moreParameters(x,y);
//		System.out.println("in method go. x: " + x + " y: " + y);
	}
	
	public static void falseSwap(int x, int y)
	throws IOException{

		// block number: 2
		myWriter.write(2+" green\n");
	System.out.println("in method falseSwap. x: " + x + " y: " + y);
		int temp = x;
		x = y;
		y = temp;
		System.out.println("in method falseSwap. x: " + x + " y: " + y);
	}
	
	public static void moreParameters(int a, int b)
	throws IOException{

		// block number: 3
		myWriter.write(3+" green\n");
	System.out.println("in method moreParameters. a: " + a + " b: " + b);
		a = a * b;
		b = 12;
		System.out.println("in method moreParameters. a: " + a + " b: " + b);
		falseSwap(b,a);
		System.out.println("in method moreParameters. a: " + a + " b: " + b);	
	}
}