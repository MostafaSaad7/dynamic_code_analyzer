// Simple Java Program


import java.io.IOException;

import java.io.FileWriter;
class HelloWorldAugmented {

		static FileWriter myWriter;

	
    public static void main(String[] args) throws IOException{

		myWriter = new FileWriter("visited_blocks/HelloWorldAugmentedVisitedBlocks.txt");


		// block number: 0
		myWriter.write(0+" green\n");

		
        System.out.println("Hello, World!"); 
		
    

		myWriter.close();
}
	
}