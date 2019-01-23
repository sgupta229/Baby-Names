data
====

This project uses data about [baby names from the US Social Security Administration](https://www.ssa.gov/oact/babynames/limits.html) to answer specific questions. 

Name: Sahil Gupta

### Timeline

Start Date: 1/19/19

Finish Date: 1/22/19

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

Known Bugs:

I do not have any known bugs. I did not account for ties in my program though. This, however, does not lead to any
bugs.

Extra credit:

I did not do the extra credit questions.


### Notes

HOW TO RUN THE PROGRAM:

In the Main.java class create a BabyNames object by passing either a URL or pathname to a folder with the .txt files
as the first argument to the constructor and a boolean as the second argument (true if the dataset is a url and false
if it is a pathname). THEN, run the storeAllData() method on the object (BabyNamesObject.storeAllData();) Then call
whichever method you want with the appropriate input values.

-----------------------------------

GLOBAL VARIABLES:

I have a few different HashMaps that store different types of rankings based on gender or year etc. I use these 
to answer various questions, and these are set up when calling storeAllData(). I have a startYear and an endYear variable
which represent the start and end years in the data set. I also have typeURL variable, which tells the program
whether the data set is a url or a file pathname. 

-----------------------------------

TESTS:

For each method I ran 3 tests. I essentially tried to run each test using the URL data set, the local file data set, 
and the test data set.

My code checks for invalid URL's, URL's that do not have proper .txt files, improper gender inputs, and invalid year
inputs based on the range of txt files in the data set. The code is fairly robust and should be able to handle 
improper values. The code does assume that data sets have a continous set of years and there are not gaps in the timeline.

I do not throw exceptions for errors since I do not want to halt program execution. Instead, I print a line
that indicates what happened. I return an empty object for whatever type of object the method that ran into the error
is supposed to return. If the output is a [], -1.0, or -1, that means there was an error and the method just returned 
an invalid object or primitive. 

If there are -1's in an output for a method that asks for rankings for a specific
time range, that means the name was not present in that year so there is no rank. A -1 indicates that it was not 
present in that year. For example, if I want the rankings of the name James from 1880-1882 and the program returns
[5, -1, 2], that means in 1881, James was not used as a name and therefore was not ranked that year. If a method asks
for the average rank across a number of years and there is a -1 for at least 1 of the years, the program will
indicate that there is an error since average ranking cannot be calculated if there was a year when the name was not
ranked within the range specified.

ASSUMPTIONS AND EXPLANATIONS FOR QUESTIONS:

Method for question #1 (equalRankInRecentYear(String name, String gender, int year)):

I return the name/gender pair with the same rank in the most recent year as the inputted name/gender pair 
had in the specified year. Edge case: if the input is (Joe, male, 1980) and the most recent year is 2017 but
Joe was not used in 2017, the code will proceed to check the next most recent year until finding a year that had the 
name and return the rank in that year.

Method for question #2 (averageGenderRankOverRange(String name, String gender, int start, int end)):

This method reports the average rank for a specified name within a range of years. Edge case: if there is at least
1 year in the range of years where the name was not used (and therefore not ranked), the program will specify that 
there was an error since there were one or more years where the name was not used. Therefore it's average rank
cannot be calculated across that specific range of years.

Method for question #3 (averageRankOverRange(String name, int start, int end)):

This method returns the average rank of a name regardless of gender. For this method I made a data structure
that stored the total popularity of each name for each year (so I added the male and female popularities for each name).
I then determined it's rank. If there was a year in the range of years specified where the name was not used, the
program will specify that there is an error since the average rank cannot be calculated if there was a year where
the name was not used.

Method for question #4 (averageRankForRecentYears(String name, int recentYears)):

This method returns the average rank for a name for the n most recent years. This method just calls
averageRankOverRange. 

Method for question #5 (mostPopularName(int start, int end)):

This method reports which name (regardless of gender) was ranked most often and how many times it was ranked 
 number 1 for the range of years specified. There are no edge cases with this method.
 
Method for question #6 (mostPopularGender(int start, int end)):
 
 This method reports which gender was more popular for the given range of years and how many years
 the most popular name was of that gender. If the genders were tied for most popular across the range of 
 years the method will specify this. If the most popular gender was a male, the method will find the most popular
 name for each year. If the name is a male name, then there is a counter that will be incremented to determine
 how many times the gender was top name.
 
 Method for question #7 and #8 (public String popularLetter(String gender, int start, int end)) :
 
 Both #7 and #8 use the same method. To answer question number 7, for gender input "f" or "female". To answer question
 number 8, for gender input "both".

### Impressions

I thought this assignment was a good introduction project since it does not require any frontend and began to help
me see how important design is for implementing new features and functionality. I thought the assignment was somewhat
tedious since after a while it wasn't as exciting to continue manipulating data. I still enjoyed this project and it
made me excited for what we'll learn in the future to making code more elegant and efficient. 