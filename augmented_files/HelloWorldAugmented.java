
import java.io.IOException;

import java.io.FileWriter;
class HelloWorldAugmented {

		static FileWriter myWriter;

	
    public static void main(String[] args) throws IOException{

		myWriter = new FileWriter("visited_blocks/HelloWorldAugmentedVisitedBlocks.txt");


		// block number: 0
		myWriter.write(0+" green\n");

        boolean k = true;
        System.out.println("Hello, World!");

        int x = 5;
        if(x == 5 || x == 6)
            {
			if(x==5){

		// block number: 1
		myWriter.write(1+" orange\n");
			}
			else {

		// block number: 1
		myWriter.write(1+" green\n");
			}
	    System.out.println("hello");
	}

        else if(true)
            {

		// block number: 2
		myWriter.write(2+" green\n");

	    System.out.println("hello");
	}

        else
            {

		// block number: 3
		myWriter.write(3+" green\n");

	    System.out.println("hello");
	}


        for(int i = 0 ; k || i < 5  ;i++)
        {
			if(k){

		// block number: 4
		myWriter.write(4+" orange\n");
			}
			else {

		// block number: 4
		myWriter.write(4+" green\n");
			}
            System.out.println("hello");

            if(i == 4)
                {

		// block number: 5
		myWriter.write(5+" green\n");

	    break;
	}



        }
        int z = 5;

        while((--z) > 0)
        {

		// block number: 6
		myWriter.write(6+" green\n");

            if(z>0 || true)
            {
			if(z>0){

		// block number: 7
		myWriter.write(7+" orange\n");
			}
			else {

		// block number: 7
		myWriter.write(7+" green\n");
			}
                System.out.println("hello");
            }

        }
    

		myWriter.close();
}
	
}