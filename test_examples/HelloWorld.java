class HelloWorld {
	
    public static void main(String[] args) {
        boolean k = true;
        System.out.println("Hello, World!");

        int x = 5;
        if(x == 5 || x == 6)
            System.out.println("hello");
        else if(true)
            System.out.println("hello");
        else
            System.out.println("hello");

        for(int i = 0 ; k || i < 5  ;i++)
        {
            System.out.println("hello");

            if(i == 4)
                break;


        }
        int z = 5;

        while((--z) > 0)
        {
            if(z>0 || true)
            {
                System.out.println("hello");
            }

        }
    }
	
}