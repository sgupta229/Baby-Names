CompSci 307: Data Project Analysis
===================

> This is the link to the [assignment](http://www.cs.duke.edu/courses/compsci307/current/assign/01_data/):

Design Review
=======

### Status

I think my code is generally readable. All of my variable names and method names are specifically named
to help the user understand what they do. My helper methods are very easy to understand, as I name
them things like "checkURLValid" or "storeAllData". By using the name of a method and briefly looking
through its contents, I think the code is understandable. Above my methods, I also include a brief
comment that explains what the method achieves. Some of my methods, however, are very long and
are probably difficult to understand. While it is easy to understand what my "storeAllData" does, it may
be confusing to understand how it works since it calls on other helper methods. I also call some helper 
methods from other helper methods, which may confuse the reader. I think one of my major flaws is 
that I only have one class. This may be the biggest problem in the readability of my code since 
one class with hundreds of lines of code is hard to digest.

This was my setDataInYear method:

```java
    //stores the data into the hashmaps for the specified year
    public void setDataInYear(int year) throws IOException {
        HashMap<String, Integer> maleYearRank = new HashMap<>();
        HashMap<String, Integer> femaleYearRank = new HashMap<>();
        HashMap<String, Integer> totalPop = new HashMap<>();
        HashMap<String, Integer> genderYear = new HashMap<>();
        genderYear.put("f", 0);
        genderYear.put("m", 0);
        int femaleCount = 0;
        int maleCount = 0;
        Scanner scanDoc;
        if(typeURL == true) {
            URL url = new URL(dataLocation + "yob" + year + ".txt");
            scanDoc = new Scanner(url.openStream());
        }
        else {
            String filename = dataLocation + "/yob" + year + ".txt";
            File test = new File(filename);
            scanDoc = new Scanner(test);
        }
        while(scanDoc.hasNext() == true) {
            String currentName = scanDoc.next();
            String[] currentNameArray = currentName.split(",");
            if(currentNameArray[1].equals("F")) {
                femaleCount++;
                femaleYearRank.put(currentNameArray[0].toLowerCase(), femaleCount);
                genderYear.put("f", genderYear.get("f") + Integer.parseInt(currentNameArray[2]));
            }
            else {
                maleCount++;
                maleYearRank.put(currentNameArray[0].toLowerCase(), maleCount);
                genderYear.put("m", genderYear.get("m") + Integer.parseInt(currentNameArray[2]));
            }
            if(totalPop.containsKey(currentNameArray[0].toLowerCase())) {
                totalPop.put(currentNameArray[0].toLowerCase(), (totalPop.get(currentNameArray[0].toLowerCase())) + Integer.parseInt(currentNameArray[2]));
            }
            else {
                totalPop.put(currentNameArray[0].toLowerCase(), Integer.parseInt(currentNameArray[2]));
            }
        }
        totalPopular.put(year, totalPop);
        maleRankings.put(year, maleYearRank);
        femaleRankings.put(year, femaleYearRank);
        genderRankings.put(year, genderYear);
    }

```

I spent a lot of time with this method since this method does pretty much all the work for storing the 
data. The only parameter this method takes in is a year. What this method first does is it initializes 
a bunch of hashmaps, that will be stored in a global variable hashmap. My global variable hashmaps are nested
hasmaps. The keys are years, and the values are hashmaps that store the data I am interested in. The hashmaps
that I initialize at the top of this method would be the hashmap that the inputted year maps too. For example,
if the year inputted is 2017, the maleYearRank hashmap would contain all the male names in 2017 as keys and
all the values would be the names' respective popularities. In this method I then initialize a scanner, and 
depending on whether the data source is a URL or a local folder, I begin reading in the data line by line, parsing
the data, and then storing data in their respective hashmaps. I do this for each line in the text file
corresponding for that year. I then store the key/value pair in the global hashmaps at the end of the method. 
The key is the year inputted in the method and the value is the hashmap constructed from the data of that year. 
I do not think the code is THAT hard to understand. It makes sense logically if you understand the problem and
what the method is doing. My problem with the method is that it is too long. I wasn't sure how to optimally design
this method. Moreover, I may have extra data structures. I probably could've solved all of the problems only 
using three hashmaps, but I used four. Maybe had I created a seperate class for URL data and local file data
I could've made methods smaller and more easy to understand since the user would know each class handles
only one type of data. As for efficiency, I do not think this method is innefficient speed wise. I loop through
each line in the text file, parse it accordingly, and then add the data to different hashmaps. None of these
take that long to execute.

The purpose of the code in the overall project is that, in the storeAllDate method, I call this method for each
year in the dataset. So this method creates the hashmap of data for each year and stores it in the global variable.
This method is essentially the method that does all the set up of the data so that it can be used to answer
questions.

Another method I had was my averageRankOverRange method:

```java

//QUESTION 3 - report average rank of the name regardless of gender
    public double averageRankOverRange(String name, int start, int end) {
        name = name.toLowerCase();
        if (validYear(start) == false || validYear(end) == false) {
            return -1;
        }
        HashMap<String, Integer> use;
        double averagePopularity = 0;
        for(int i = start; i < end + 1; i++) {
            use = totalPopular.get(i);
            if(totalPopular.get(i).get(name) == null) {
                System.out.println("This name was not used in year " + i);
                return -1;
            }
            int yearPopularity = totalPopular.get(i).get(name);
            int peopleAbove = 1;
            for(String person : use.keySet()) {
                if(use.get(person) > yearPopularity) {
                    peopleAbove++;
                }
            }
            averagePopularity += peopleAbove;
        }
        return averagePopularity / ((double) end - (double) start + 1.0);
    }

```

The purpose of this method is to calculate the average rank of a name (regardless of gender)
over a certain time period. I first convert the name to all lowercase letters to 
avoid case errors. I then call a helper method called validYear, which checks to see if a 
certain year is within the year range. Afer that, I loop through the year range provided, and 
I retrieve the ith value of the totalPopular map. The totalPopular map is a HashMap<Integer, HashMap<String, Integer>>
that maps a year to a hashmap. In the nested hashmap, the key is a name and the value is the total
popularity of the name from that year. So if 2000 males had the name Sam in 2017 and 3500 females
had the name Sam in 2017, then the value in the nested hashmap would be 5500. I retrieve the
hashmap for that year and check to see if the name existed within that year. If the name did not exist
within that year, I exit the method. I decided that the average could not be calculated if 
a name is not ranked in one of the years. If the name DOES, exist, I store its popularity. I then
count the number of people who had a popularity above that one by looping through the keyset and
keeping a counter of which names were more popular that year. After I have its rank, I add that to a counter
that keeps a total of its ranks across the years. At the end I divide by the number of years.

While this code works, it is not efficient. Determining rank by looping through the entire
keyset and keeping track of how many names had a popularity number above the name in question
is definitely not the best way to go about the problem. I think it woudld've been better to use
a TreeSet. I could've possibly set up my maps in a different layout and had treesets so that
I could sort by popularity. Then I could retrieve the name and know its rank fairly easily.
This would have been a lot more efficient, and I would want to implement this if I were
to improve upon the project.

I think the code is readable. I feel that the code is straightforward to follow and understand.
There is no complicated syntax, and the method is about 20 lines. It is brief and easy to 
uderstand, especially with the comment above the method.


### Design

The design of my project is simple, as I essentially only have one class. Although this design does not necessarily meet
my design goals since the presentation seems clunky and hard to interpret, my one class essentially does everything.
It reads the data depending on the data source, it stores the data in global variables, and then it has multiple
methods to interpret the data and output results/answers to the questions. As far as my design goals went, while I 
wanted my design to be clean and efficient, I could not see the utility in having a parent class or an interface. 
I have two methods to read in the data. One handles URL's and one handles local files. After the data is read in, 
there are methods to return answers to specific questions about the data. I think my overall code is lacking in efficiency. 
There were many places were I could've implemented better algorithms to search for specific things or achieve something. 
My design is also lacking because it is one class. I designed my code this way because I wasn't sure
what the justfication would be to add another class or interface other than the fact that it could've possibly 
helped with readability. I couldn't see how it would help with modularity or anything else. 

To reduce code duplication, I made a lot of helper methods that I knew a lot of my methods would need to call. 
This includes checking to see if a valid gender or year were inputted. I also refactored some code
that I saw I was using in a couple of different methods. My mostPopularName method is a method I made
specifically because I saw a few methods using those exact same lines, so I made it a functional method.


### Alternate Designs

1. A major altnerate design that I considered for most of the my project was whether or not 
I should add more classes. I considered having a class dedicated to handling URL sources and
a class handling local file sources. If I did this implementation, then I would've had a 
parent class that would've had all the methods for answering the questions. My only problem
with this implementation was that I wasn't sure if doing that was justified functionality wise
though. I understand what adding parent classes and interfaces can do (they help with
duplicate code, polymorphism, etc.), but I wasn't sure if this was an instance where I should
actually do that. A pro of this design is that it would've been more readable and possibly(??)
better if this project was being worked on by a group. I am still not sure what the benefits
of me doing that would be. If I were to pick between this design and my current
design, I still think I would pick my current design. At this point in my understanding of
software development, I do not see how adding new classes or interfaces would be vastly useful
UNLESS this project were to be a group project, which it wasn't. I guess in the future
if we were supposed to develop this project further except with a partner, it would make
sense to prefer the design option with more classes.

2. Another alternate design I was debating on was using treesets instead of hashmaps
as some of my data structures so the data would already be sorted. This would've definitely made
more my program more efficient. I also considered using jsoup to read in the url sources. The
pro with this design choice is definitely efficiency. Currently when I read in url's, I read
check to see if the url exists and THEN I retrieve the data from it from another method. I loop
through URL's from 1870-2020, and if the URL exists I mark that year as existing. This is
the reason reading URL's for my code takes so long, and jsoup would've been able to extract all 
that data fairly quickly. I honestly do not think there were any cons to these design choices. I 
would definitely prefer this design implementation over my current one. I was going to 
install the jar file for jsoup, but for some reason my module did not have a dependency 
option and the URL feature is what I implemented towards the end. Therefore, I did not have much
time to figure out how to install jsoup.


