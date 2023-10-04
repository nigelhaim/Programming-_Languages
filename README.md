# Programming-_Languages

## Specifications 

java version "21" 2023-09-19 LTS
Java(TM) SE Runtime Environment (build 21+35-LTS-2513)
Java HotSpot(TM) 64-Bit Server VM (build 21+35-LTS-2513, mixed mode, sharing)

# Message 
- The main program for the project is NestedIfElse.java 
- Recommended to use VSC for smooth operation

## Main Program 
- You can use List and HashMap please comment out depending on your liking
#### InputBasic.txt
- This contains less complicated line which contains a valid Nested If-then-Else statement

#### Input.txt
- This contains a bit more complicaed line which detects variable initiations, printing, etc. 

#### IncorrectInput.txt
- This contains an incorrect Nested If-then-Else statement

# TODO
- Make if statement syntax checker
- Make then statement syntax checker 
- Make else statment syntax checker 
- figure out how to check every if else 
- make all syntax checker into functions 

# COMPLETED
- Successful input of multiple lines of a .txt file 

## Side notes 
- My idea on the project is that every if statement will enter a recursion method to check the current If statement's condition. If it returned true then it will ender inside then checks if theres another if statement. If there is antoher if statement then it will return to an another recurssion method then checks the syntaxes (this is my reason why we put the syntax checkers in functions). When everything is good then we end the program with a positive prompt. Else we end the program by printing what line is the error and the comment why.