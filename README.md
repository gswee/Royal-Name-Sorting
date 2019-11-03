# [UPDATE: 02/11/19]

## Files in this folder
```
testinput[n].txt    Files containing lines of input, 1 royal name each. 
[n] =
1                   3 lines of input
2                   9 lines of input, testing alphabetical sorting
3                   Testing for invalid alphabetical input
4                   Testing for invalid ordinal input
```

## Instructions on running:

1. Open main.java AND RoyalRumble.java
2. In main.java, change the "testinput[n].txt" file into a test file of your own.
```
12        File file = new File("testinput[n].txt");
```
3. Run main.java. The output should be as follows:
```
Input:
12        File file = new File("testinput1.txt");

Output:
Elizabeth I, George VI, William I, William II
[total speed]
```

# [INITIAL CONTENTS] 

## Files in this folder
```
readme.txt                          This file
Main.class                          Class containing the main() method
RoyalRumble.java                    The file to be implemented and submitted
input1.txt                          Sample input file
output1.txt                         Sample output file corresponding to input1.txt
run.sh                              Bash script to help you run Main.main()
run.bat                             Batch file to help you run Main.main()
```

Instructions
--------------------
1) Implement RoyalRumble.java
2) If you compile RoyalRumble into the same folder, you can use either run.sh or run.bat to help you run Main.main()
3) If you use an IDE, you will have to setup your own workflow to run Main.main() and pass the input file as parameter to the program.
4) Submit only RoyalRumble.java, and unit test files if you have written any.
