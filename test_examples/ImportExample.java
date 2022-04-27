import java.util.Map;
import java.util.HashMap;

public class ImportExample {
    public static void main(String[] arg)
    {
        Map<String,String> newMap = new HashMap<>();

        newMap.put("1", "Mostafa Ayman");
        newMap.put("2", "Mostafa Amin");
        newMap.put("3", "Mostafa Saad");
        newMap.put("4", "Mariam Gad");
        newMap.put("5", "Mayar Hanafy");
        newMap.put("6", "Maha ELkomy");
        for (Map.Entry<String,String> entry : newMap.entrySet())
            System.out.println("Key = " + entry.getKey() +
                    ", Value = " + entry.getValue());
    }
}
