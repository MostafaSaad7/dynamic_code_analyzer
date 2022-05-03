
import java.io.IOException;

import java.io.FileWriter;
public class NestedBlocksAugmented
{

		static FileWriter myWriter;

    public static void main(String[] args) throws IOException{

		myWriter = new FileWriter("visited_blocks/NestedBlocksAugmentedVisitedBlocks.txt");


		// block number: 0
		myWriter.write(0+" green\n");

        int x = 10;
        int y= x/2;

        if (x==11)
            {

		// block number: 1
		myWriter.write(1+" green\n");

	    System.out.println(" X not equal 11");
	}

        else{

		// block number: 2
		myWriter.write(2+" green\n");

            if (x==10){

		// block number: 3
		myWriter.write(3+" green\n");

                if (y <5) {

		// block number: 4
		myWriter.write(4+" green\n");

	    System.out.println("x equal 10 & y <4");
	}

                else if (y>5) {

		// block number: 5
		myWriter.write(5+" green\n");

	    System.out.println("x equal 10 & y >4");
	}

                else {

		// block number: 6
		myWriter.write(6+" green\n");

                    System.out.println("good");
                    x=11;
                    y=6;
                }
            }
        }

        if (x==11)
            {

		// block number: 7
		myWriter.write(7+" green\n");

	    if(true || false)
                {
			if(true){

		// block number: 8
		myWriter.write(8+" orange\n");
			}
			else {

		// block number: 8
		myWriter.write(8+" green\n");
			}
	    if(y==6)
                    {

		// block number: 9
		myWriter.write(9+" green\n");

	    System.out.println("finish");
	}

	}

	}

    

		myWriter.close();
}
}