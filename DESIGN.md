data
====

This project uses data about [baby names from the US Social Security Administration](https://www.ssa.gov/oact/babynames/limits.html) to answer specific questions. 

Name: Sahil Gupta

### Timeline

Start Date: 1/19/19

Finish Date: 1/22/19

Hours Spent: 24

### Design Analysis

The design of my project is simple, as I essentially only have one class. Although this design does not necessarily meet
my design goals since the presentation seems clunky and hard to interpret, my one class essentially does everything.
It reads the data depending on the data source, it stores the data in global variables, and then it has multiple
methods to interpret the data and output results/answers to the questions. As far as my design goals went, while I 
wanted my design to be clean and efficient, I could not see the utility in having a parent class or an interface.
I have a main class where I print out all of my tests.

-------------------------------------

To add new features to my project it's relatively simple. I already have a few data structures that should
be able to answer MOST questions about the data. If this is the case, a new method can be made that uses the data
to return a specific result (similar to the methods that are commented (QUESTION 1 etc.). 
If the data needs to be parsed in a different way, the user can go into the method
"setDataInYear" and make it so each line that is read from a text file reads in the data in a different way/puts it
in a data structure in a different way. Reading through the method, the user can see that I parse the lines differently
and put them in my hashmaps depending on what I want to access. Do I need male popularities, female popularities, 
or gender popularities as my values in the key/value pair? If so, that's how I parse the data. New functionality 
can be added to this method to fix this problem. A new global variable can be created like this:

private HashMap<Integer, HashMap<String, Integer>> newTypeOfRanking = new HashMap<>();

Then, in the while(scanDoc.hasNext() == true) in the setDataInYear function, the user can make if or else
statements to add data to the new data structure.

--------------------------------------

I think my design choice of one class has pros and cons. The pros are that it does not overcomplicated things 
by adding unnecessary classes and parent classes. I also feel like my code is also pretty modular functionality wise. It
is not difficult to add methods to answer different questions and it isn't hard to add different data structures
to answer new questions. The downside is that the class is very long and cumbersome. This could be confusing for
other users. I also do not think this design would be good for a group to work on it. Moreover, 
I feel like having one class for projects like these is not maintainable or efficient with drastic
changes in implementation. While I can add small features pretty easily, changing something
more significant would not be as easy.

--------------------------------------

One thing that is ambiguous with my project is the handling of ties. I did not add any functionality or explanation
of how ties are handled. I also made the assumption that, if a name is not ranked in a year, then the name's average
rank across a range of years with that year in the range could not be calculated since it is unranked for that year.
For example, if the name "John" was not used in the year 2000, and the user wants to find the average rank of the 
name "John" from 1999-2001, the program will state that this is not valid since "John" is unranked in the year 2000.
Those were pretty much the only assumption I made, and I hope my error messages and statements that I 
print out make it clear to the user what happened when they call a specific function and it does not work properly.