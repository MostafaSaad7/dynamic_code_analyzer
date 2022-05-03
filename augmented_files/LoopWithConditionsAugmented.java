
import java.io.IOException;

import java.io.FileWriter;
class LoopWithConditionsAugmented {

		static FileWriter myWriter;

	
    public static void main(String[] args) throws IOException{

		myWriter = new FileWriter("visited_blocks/LoopWithConditionsAugmentedVisitedBlocks.txt");


		// block number: 0
		myWriter.write(0+" green\n");


        int x = 10;
        int y = 5;
        int z = 2;

        while(x > 0) {

		// block number: 1
		myWriter.write(1+" green\n");

            // this should be green

            if(x > 5 || y == 5)
            {
			if(x>5){

		// block number: 2
		myWriter.write(2+" orange\n");
			}
			else {

		// block number: 2
		myWriter.write(2+" green\n");
			}
                // this will be orange in the first 5 iterations then it should become green
                System.out.println("Hello, World!");
            }

            else if (y>5) {

		// block number: 3
		myWriter.write(3+" green\n");

                // this should be red (will never be reached)
                System.out.println("Hello, World!");
            }

            if(x > 5 || y == 5 || z==2)
            {
			if(x>5||y==5){

		// block number: 4
		myWriter.write(4+" orange\n");
			}
			else {

		// block number: 4
		myWriter.write(4+" green\n");
			}
                // this will be orange in all 10 iterations because the third condition
                // will never be evaluated

                System.out.println("Hello, World!");
            }

            x--;

        }

    

		myWriter.close();
}
	
}