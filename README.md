# Dynamic code analyzer for Java Language 
This project to build a dynamic code analyzer for Java Language to generate statement and branches code coverage reports.
___

## To build the dynamic code analyzer we followed the following steps:
___

### Step 1: Take a java file as an input for program by defining the path of the file.
path: `test_examples/HelloWorld.java`

![step 1 demo](https://user-images.githubusercontent.com/47724391/166562640-9d72f403-2dc8-45b7-92e8-0e57d0ee218f.png)
___

### Step 2: Output a modified intermediate java file (augmented code).

path: `augmented_files/HelloWorldAugmented`

![step 2 demo](https://user-images.githubusercontent.com/47724391/166566834-8fd16255-f9a5-4fdf-a134-af7d328e96c0.png)
____

### Step 3: Run the modified augmented java file to know which blocks of the code are visited then store the visited block at text file.

path: `visited_blocks/HelloWorldAugmentedVisitedBlocks.java`

![step 3 demo](https://user-images.githubusercontent.com/47724391/166563474-5951ffaf-4949-4916-86d3-4cdee80932f7.png)

**Note:**
- **Green lines are highlighted for visited lines.**
- **Orange lines are highlighted for branches (if/else/for/while) if the boolean expression has more than one condition, like a || b, and the first condition always evaluate to true, this means that the second condition b never executed.** 
-  **Red lines are highlighted for not visited lines.**
___

### Step 4: Use the text file from the previous step to generate an HTML file with highlighted red, green and orange lines as program output.
 
![step 4 demo](https://user-images.githubusercontent.com/47724391/166563697-cf3164a7-eeae-488f-82e3-8090b188476c.png)
_______

# Team members
> * Mostafa Saad  : [@MostafaSaad7 ]( https://github.com/MostafaSaad7)
> * Mostafa Ayman : [@MostafaAE ]( https://github.com/MostafaAE)
> * Mostafa Amin : [@MostafaAmin0 ]( https://github.com/MostafaAmin0 )
> * Mariam Gad : [@MariamGad  ]( https://github.com/Mariamgad)
> * Mayar Hanafy : [@MayarHanafy ]( https://github.com/MayarHanafy)
> * Maha Elkomey : [@MahaElomey ]( https://github.com/MahaElomey )
