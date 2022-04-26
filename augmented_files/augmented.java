
package augmented_files;
// Simple Java Program


import java.io.IOException;

import java.io.FileWriter;
class HelloWorld {
	
    public static void main(String[] args) throws IOException{

		myWriter = new FileWriter("visited_blocks/nullVisitedBlocks.txt");


		// block number: 1
		myWriter.write("Entered Block Number: 1\n");

		
        System.out.println("Hello, World!"); 
		
    

		myWriter.close();
}
	
}