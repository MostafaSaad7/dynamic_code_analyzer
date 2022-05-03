class LoopWithConditions {
	
    public static void main(String[] args) {

        int x = 10;
        int y = 5;
        int z = 2;

        while(x > 0) {
            // this should be green

            if(x > 5 || y == 5)
            {
                // this will be orange in the first 5 iterations then it should become green
                System.out.println("Hello, World!");
            }

            else if (y>5) {
                // this should be red (will never be reached)
                System.out.println("Hello, World!");
            }

            if(x > 5 || y == 5 || z==2)
            {
                // this will be orange in all 10 iterations because the third condition
                // will never be evaluated

                System.out.println("Hello, World!");
            }

            x--;

        }

    }
	
}