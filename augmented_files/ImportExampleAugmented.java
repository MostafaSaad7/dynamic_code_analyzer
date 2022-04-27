
package augmented_files;
import java.util.Map;
import java.util.HashMap;


import java.io.IOException;

import java.io.FileWriter;
public class ImportExampleAugmented {

		static FileWriter myWriter;

    public static void main(String[] arg)
    throws IOException{

		myWriter = new FileWriter("visited_blocks/ImportExampleAugmentedVisitedBlocks.txt");


		// block number: 1
		myWriter.write("Entered Block Number: 1\n");

        Map<String,String> newMap = new HashMap<>();

        newMap.put("1", "Mostafa Ayman");
        newMap.put("2", "Mostafa Amin");
        newMap.put("3", "Mostafa Saad");
        newMap.put("4", "Mariam Gad");
        newMap.put("5", "Mayar Hanafy");
        newMap.put("6", "Maha ELkomy");
        for (Map.Entry<String,String> entry : newMap.entrySet())
            {


		// block number: 2
		myWriter.write("Entered Block Number: 2\n");
System.out.println("Key = " + entry.getKey() +
                    ", Value = " + entry.getValue());
		}

    

		myWriter.close();
}
}
