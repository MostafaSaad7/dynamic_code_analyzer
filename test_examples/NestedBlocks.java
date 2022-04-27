/* NestedBlocks.java
 *
 * illustrates how to call nested blocks
 */

public class NestedBlocks
{
    public static void main(String[] args) {
        int x = 10;
        int y= x/2;

        if (x==11)
            System.out.println(" X not equal 11");
        else{
            if (x==10){
                if (y <5) System.out.println("x equal 10 & y <4");
                else if (y>5) System.out.println("x equal 10 & y >4");
                else {
                    System.out.println("good");
                    x=11;
                    y=6;
                }
            }
        }

        if (x==11)
            if(true)
                if(y==6)
                    System.out.println("finish");
    }
}