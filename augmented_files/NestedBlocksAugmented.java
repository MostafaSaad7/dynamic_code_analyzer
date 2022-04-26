
package augmented_files;
/* NestedBlocks.java
 *
 * illustrates how to call nested blocks
 */


import java.io.IOException;

import java.io.FileWriter;
public class NestedBlocksAugmented
{

		static FileWriter myWriter;

    public static void main(String[] args) throws IOException{

		myWriter = new FileWriter("visited_blocks/NestedBlocksAugmentedVisitedBlocks.txt");


		// block number: 1
		myWriter.write("Entered Block Number: 1\n");

        int x = 10;
        int y= x/2;

        if (x==11)
            {


		// block number: 2
		myWriter.write("Entered Block Number: 2\n");
System.out.println(" X not equal 11");
		}

        else{

		// block number: 3
		myWriter.write("Entered Block Number: 3\n");

            if (x==10){

		// block number: 4
		myWriter.write("Entered Block Number: 4\n");

                if (y <5) {


		// block number: 5
		myWriter.write("Entered Block Number: 5\n");
System.out.println("x equal 10 & y <4");
		}

                else if (y>5) {


		// block number: 6
		myWriter.write("Entered Block Number: 6\n");
System.out.println("x equal 10 & y >4");
		}

                else {

		// block number: 7
		myWriter.write("Entered Block Number: 7\n");

                    System.out.println("good");
                    x=11;
                    y=6;
                }
            }
        }

        if (x==11)
            {


		// block number: 8
		myWriter.write("Entered Block Number: 8\n");
if(true)
                {


		// block number: 9
		myWriter.write("Entered Block Number: 9\n");
if(y==6)
                    {


		// block number: 10
		myWriter.write("Entered Block Number: 10\n");
System.out.println("finish");
		}

		}

		}

    

		myWriter.close();
}
}