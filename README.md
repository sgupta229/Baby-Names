Baby Names Data Parser
====

This project uses data about [baby names from the US Social Security Administration](https://www.ssa.gov/oact/babynames/limits.html) to answer specific questions. 

Name: Sahil Gupta

### Timeline

Hours Spent: 24

### Resources Used

Stack Overflow, Piazza

### Running the Program

Main class: 

I have two class: BabyNames.java and Main.java. Main.java contains the tests and is the class that should be ran.
BabyNames.java sets up the data and has the methods that answers the questions.

Data sets used and created: 

I used three different data sets. I used the URL with the 
text files (https://www2.cs.duke.edu/courses/compsci307/spring19/assign/01_data/data/), 
I downloaded the text files and used the data set on my local file system, and I made a test data set
with a few names and values to test. The test data set has a lot less names to see if my methods output the correct
things and do the right thing.

How to change the data set used in the code: 

I have one class called BabyNames. I have a constructor that takes in two arguments. 
The first argument is either the full pathname to the folder with the .txt files (a string) or
the URL with the .txt files (also a string). The second argument is a boolean that represents whether or not the 
dataset is a url or not. "true" means it IS a url and "false" means it is not. 

### How to Run

In the Main.java class create a BabyNames object by passing either a URL or pathname to a folder with the .txt files
as the first argument to the constructor and a boolean as the second argument (true if the dataset is a url and false
if it is a pathname). THEN, run the storeAllData() method on the object (BabyNamesObject.storeAllData();) Then call
whichever method you want with the appropriate input values.
