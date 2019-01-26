import java.util.*;
import java.io.*;
import java.net.*;

/**
 * <h1>Baby Names Statistics</h1>
 * <p>The purpose of the BabyNames program is to analyze past popularities of baby names. Given a set of text files from a URL or
 *  * local folder, the program will read in the data and can answer a variety of questions regarding baby names from the years of data
 *  * provided.</p>
 *
 * <p>The code is pretty robust, so improper values or data sets should not cause the program to fail. Rather, the program
 * should print out an error stating what is causing the problem. A facet of the problem that was not considered is
 * what happens when there are ties. The code does not have a mechanism of which name to pick when two names are tied (in rank, popularity,
 * etc.)</p>
 *
 * <p>This class does not depend on any packages. There is one other Main class, which is where all the tests and error checking occurs.
 * If the user wishes to use the program, create an instance of BabyNames in the main class and then call the storeAllData method
 * on the instance.</p>
 *
 * <p> An example of how to use the program would be to go to the Main class. Create an instance of the object like such:
 * <p>BabyNames urlObject = new BabyNames("https://www2.cs.duke.edu/courses/compsci307/spring19/assign/01_data/data/", true);</p>
 * Then call:
 * <p>urlObject.storeAllData();</p>
 * Once you have done this, you can call whichever method on the object you would like. If the data set is invalid/empty, or the user
 * input for one of the methods is invalid or incorrect, the program will tell you.
 * </p>
 *
 *
 * @author Sahil Gupta
 * @version 1.0
 * @since 1/19/19
 */

public class BabyNames {

    /**
     * The {@link HashMap} representing the total popularities of names. The key is a year, the value is
     * a HashMap with a key being a name and the value being the popularity of that name (both male and female)
     * in that year.
     */
    private HashMap<Integer, HashMap<String, Integer>> totalPopular = new HashMap<>();

    /**
     * The {@link HashMap} representing the male popularities of names. The key is a year, the value is
     * a HashMap with a key being a male name and the value being the popularity of that name as a male
     * in that year.
     */

    private HashMap<Integer, HashMap<String, Integer>> maleRankings = new HashMap<>();

    /**
     * The {@link HashMap} representing the female popularities of names. The key is a year, the value is
     * a HashMap with a key being a female name and the value being the popularity of that name as a female
     * in that year.
     */

    private HashMap<Integer, HashMap<String, Integer>> femaleRankings = new HashMap<>();

    /**
     * The {@link HashMap} representing the gender popularities. The key is a year, the value is
     * a HashMap with a key being a gender and the value being the popularity of that gender in that year.
     */

    private HashMap<Integer, HashMap<String, Integer>> genderRankings = new HashMap<>();

    /**
     * An int representing the starting year of a data set.
     */

    private int startYear = Integer.MAX_VALUE;

    /**
     * An int representing the ending year of a data set.
     */

    private int endYear = Integer.MIN_VALUE;

    /**
     * A boolean representing whether or not the data set is from a URL or local file folder. "false" means the data
     * is not from a URL and "true" means the data is from a URL.
     */

    private boolean typeURL;

    /**
     * The {@link String} representing where the data is located. The user puts this into the constructor when creating an
     * instance of BabyNames. The value can either be a folder pathname containing "yob + year + .txt" files or it can
     * be a URL with "yob + year + .txt." files on the page that is linked.
     */

    private String dataLocation;

    /**
     * This is the constructor the user must use to test the data. If the data location is a pathname, the path
     * MUST NOT end in a "/". If the data location is a URL, the URL MUST END in a "/".
     * @param pathname The pathname of the data. The {@link String} can be a local folder pathname or a URL.
     * @param url A boolean. false means the data is NOT from a URL and true means the data IS from a URL.
     */

    public BabyNames(String pathname, boolean url) {
        dataLocation = pathname;
        typeURL = url;
    }

    /**
     * Sets the boolean representing whether the data set is from a URL or local file system.
     * @param type The boolean representing whether the data is from a URL or local file system.
     */

    public void setTypeURL(boolean type) {
        this.typeURL = type;
    }

    /**
     * Sets the data location.
     * @param location A {@link String} local file path name or URL where the text files are stored.
     */

    public void setDataLocation(String location) {
        this.dataLocation = location;
    }

    /**
     * Returns the start year of the data.
     * @return startYear An instance variable representing what year the data set starts at.
     */

    public int getStartYear() {
        return startYear;
    }

    /**
     * Returns the start year of the data set.
     * @return endYear An instance variable representing what year the data set ends at.
     */

    public int getEndYear() {
        return endYear;
    }

    /**
     * The user can use this function to print out an {@link ArrayList} of the rankings of a specific name
     * for all the years in the data set.
     * @param name The name for which you want the rankings for.
     * @param gender The gender of the name you specified you want the rankings for.
     * @return rangeOfYears The rankings of name in an {@link ArrayList} for all the years in the data set.
     */

    //reports the rankings of a specified name for all years
    public ArrayList<Integer> allYears(String name, String gender) {
        return rangeOfYears(name, gender, this.startYear, this.endYear);
    }

    /**
     * This method returns the rankings of a name for the range of years the user specifies.
     * @param name The name for which you want the rankings for.
     * @param gender The gender of the specified name.
     * @param start The year that the returned rankings start at.
     * @param end The year that the returned rankings end at.
     * @return This method returns an {@link ArrayList} of the rankings for the name/gender specified in the range of years.
     */

    public ArrayList<Integer> rangeOfYears(String name, String gender, int start, int end) {
        name = name.toLowerCase();
        ArrayList<Integer> popOfPerson = new ArrayList<>();
        HashMap<Integer, HashMap<String, Integer>> use;
        if(validYear(start) == false || validYear(end) == false || validGender(gender) == false) {
            return popOfPerson;
        }
        else {
            use = whichRankingMap(gender);
        }
            for(int i = start; i < end + 1; i++) {
                HashMap<String, Integer> checkYear = use.get(i);
                Integer result = checkYear.get(name);
                if (result == null) {
                    popOfPerson.add(-1);
                }
                else {
                    popOfPerson.add(result);
                }
            }
        return popOfPerson;
    }

    /**
     * Given a name, a gender, and a year, the method will calculate the ranking of that name/gender pair in the year provided. The
     * method returns the name/gender pair with the same ranking in the most recent year. If there is not a name with the same
     * ranking in the most recent year, the method will check the next most recent year. The method will keep doing this until it finds
     * a name with the same ranking.
     * @param name The name of the person for which you want to find a matched ranking with.
     * @param gender The gender of the name you are interested in.
     * @param year The year in which you want to calculate the ranking of the name/gender.
     * @return A string representing the name that had the same ranking in the most recent year as as the provided name/gender did in the specified year.
     */

    //QUESTION 1 - name/gender pair with the same rank in the most recent year
    public String equalRankInRecentYear(String name, String gender, int year) {
        HashMap<Integer, HashMap<String, Integer>> use;
        name = name.toLowerCase();
        if(validYear(year) == false || validGender(gender) == false) {
            return "";
        }
        else {
            use = whichRankingMap(gender);
        }
        if(use.get(year).get(name) == null) {
            System.out.println("This name was not used in year " + year);
            return "";
        }
        int rankOfName = use.get(year).get(name);
        int useYear = endYear;
        while(true) {
            HashMap<String, Integer> checkMap = use.get(useYear);
            for(String key : checkMap.keySet()) {
                if(checkMap.get(key) == rankOfName) {
                    System.out.println("The most recent year with rank " + rankOfName + " for gender " + gender.toUpperCase() + " is " + useYear);
                    return(key + " (" + gender + ")");
                }
            }
            useYear = useYear - 1;
        }
    }

    /**
     * Reports the average rank for the specified name within the range of years. If the name/gender is not
     * ranked in at least one of the years within the range, the method will output an error and say the average
     * rank cannot be calculated.
     * @param name The name of the person for which the average rank should be calculated for.
     * @param gender The gender of the name specified.
     * @param start The start year for which the average rank should be calculated.
     * @param end The end year for which the average rank should be calculated.
     * @return A double representing the average the name/gender specified across the range of years.
     */

    public double averageGenderRankOverRange(String name, String gender, int start, int end) {
        name = name.toLowerCase();
        double calculate = 0;
        HashMap<Integer, HashMap<String, Integer>> use;
        if (validYear(start) == false || validYear(end) == false || validGender(gender) == false) {
            return -1;
        }
        else {
            use = whichRankingMap(gender);
        }
        for(int i = start; i < end + 1; i++) {
            if(use.get(i).get(name) == null) {
                System.out.println("This name was not used as gender " + gender + " in year " + i);
                return -1;
            }
            calculate += use.get(i).get(name);
        }
        return (calculate / ((double) end - (double) start + 1.0));
    }

    /**
     * Reports the average rank of a name across the span of years regardless of gender.
     * @param name The name for which the rankings should be calculated for.
     * @param start The start year to calculate the rankings.
     * @param end The end year to calculate the rankings.
     * @return A double representing the average ranking of a name (regardless of gender) across the span of years.
     */

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

    /**
     * Reports the average rank of a name for the most recent years. This method calls averageRankOverRange
     * for its computations.
     * @param name The name for which rankings should be calculated for.
     * @param recentYears The number of recent years to calculate the average rank.
     * @return A double representing the average rank of a name for the past recentYears.
     */

    //QUESTION 4 - reports average rank of a name for the most recent number of years
    public double averageRankForRecentYears(String name, int recentYears) {
        name = name.toLowerCase();
        if((endYear - startYear) < recentYears) {
            System.out.println("Not enough years in data set");
            return -1.0;
        }
        return averageRankOverRange(name, this.endYear - recentYears + 1, this.endYear);
    }

    /**
     * This method is interesting because it tells the user which name was ranked first most often within a range of years. This method
     * can also be used to see which name was ranked most popular in a given year by providing the same start and end year.
     * @param start The start year of seeing which name was ranked most popular
     * @param end The end year of seeing which name was ranked most popular
     * @return A {@link String} of the name that was ranked first most often during the range of years and how many times it ranked first.
     */

    //QUESTION 5 - report which name was ranked first most often AND how many years it came first
    public String mostPopularName(int start, int end) {
        if (validYear(start) == false || validYear(end) == false) {
            return "";
        }
        HashMap<String, Integer> topRankers = new HashMap<>();
        for(int i = start; i < end + 1; i++) {
            String topPerson = mostPopularName(i);
            if(topRankers.containsKey(topPerson)) {
                topRankers.put(topPerson, topRankers.get(topPerson) + 1);
            }
            else {
                topRankers.put(topPerson, 1);
            }
        }
        int finalMax = 0;
        String finalWinner = "";
        for(String toppers : topRankers.keySet()) {
            if(topRankers.get(toppers) > finalMax) {
                finalMax = topRankers.get(toppers);
                finalWinner = toppers;
            }
        }
        return "The most popular name from years " + start + " to year " + end + " was " + finalWinner + ". This name was ranked first " +
                finalMax + " times.";
    }

    /**
     * Similar to the previous method, this method returns a {link @String} telling the user which gender was most popular
     * within the range of years and how many of those years was the most popular name that gender.
     * @param start Start year of the range
     * @param end End year of the range
     * @return A {@link String} that includes the most popular gender and how many years a name of that gender was ranked number 1.
     */

    //QUESTION 6 - report which gender was more popular and how many years this gender was the most popular name
    public String mostPopularGender(int start, int end) {
        if (validYear(start) == false || validYear(end) == false) return "";
        int maleWins = 0;
        int femaleWins = 0;
        for(int i = start; i < end + 1; i++) {
            if(genderRankings.get(i).get("m") > genderRankings.get(i).get("f")) {
                maleWins++;
            }
            else if (genderRankings.get(i).get("m") < genderRankings.get(i).get("f")) {
                femaleWins++;
            }
        }
        String finalWinner;
        HashMap<Integer, HashMap<String, Integer>> use;
        if(maleWins > femaleWins) {
            use = this.maleRankings;
            finalWinner = "male";
        }
        else if (maleWins < femaleWins){
            use = this.femaleRankings;
            finalWinner = "female";
        }
        else {
            return "Both genders were the most popular in this range of years " + maleWins + " times.";
        }
        int numYear = 0;
        for(int i = start; i < end + 1; i++) {
            String topPerson = mostPopularName(i);
            if(use.get(i).containsKey(topPerson)) {
                numYear++;
            }
        }
        return "The most popular gender: " + finalWinner + ". A " + finalWinner + " ranked as the most popular name " +
                numYear + " times from " + start + " to " + end + ".";
    }

    /**
     * Given a gender (or can calculate irregardless of gender), this method will return the most popular letter through
     * the range of years specified AND return a list of all the names that start with that letter within that range of years.
     * @param gender This parameter is a {@link String} that can be "female", "male", or "both" (includes both genders).
     * @param start Start year for calculations
     * @param end End year for calculations
     * @return A String that has the most popular letter of the gender specified and the names of that gender that start with that letter.
     */


    //QUESTIONS 7 AND 8 - given a gender (or both), reports the most popular letter that names starts with
    //and returns a list of all the names that start with that letter
    public String popularLetter(String gender, int start, int end) {
        HashMap<Character, Integer> letterCounts = new HashMap<>();
        HashMap<Integer, HashMap<String, Integer>> use;
        if (validYear(start) == false || validYear(end) == false) return "";
        if(gender.equals("both")) {
            use = totalPopular;
        }
        else if(validGender(gender) == true) {
            use = whichRankingMap(gender);
        }
        else return "";
        for(int i = start; i < end + 1; i++) {
            for(String person : use.get(i).keySet()) {
                if(letterCounts.containsKey(person.charAt(0))) {
                    letterCounts.put(person.charAt(0), letterCounts.get(person.charAt(0)) + 1);
                }
                else {
                    letterCounts.put(person.charAt(0), 1);
                }
            }
        }
        char maxChar = 'b';
        int maxNum = 0;
        for(char i : letterCounts.keySet()) {
            if(letterCounts.get(i) > maxNum) {
                maxChar = i;
                maxNum = letterCounts.get(i);
            }
        }
        Set<String> finalNames = new HashSet<>();
        for(int i = start; i < end + 1; i++) {
            for(String person : use.get(i).keySet()) {
                if(person.charAt(0) == (maxChar)) {
                    finalNames.add(person);
                }
            }
        }
        List<String> finalList = new ArrayList<>();
        finalList.addAll(finalNames);
        Collections.sort(finalList);
        System.out.println("Most popular letter: " + maxChar);
        return Arrays.toString(finalList.toArray());
    }

    ///////////////////////////////////////////////////
    //THESE ARE HELPER METHODS / HELP SET UP THE DATA//
    ///////////////////////////////////////////////////

    /**
     * Given a URL, will check to see if the URL exists.
     * @param checkThisWebsite A {@link String} representing a URL to be tested.
     * @return A boolean. false means the URL does not exist and true means the URL does exist.
     */

    //checks to see if a URL is valid
    public boolean checkURLValid(String checkThisWebsite) {
        try {
            URL url = new URL(checkThisWebsite);
            HttpURLConnection huc = (HttpURLConnection) url.openConnection();
            int responseCode = huc.getResponseCode();
            if (responseCode != 404) {
                return true;
            } else {
                return false;
            }
        }
        catch (IOException e) {
            return false;
        }
    }

    /**
     * This method sets the startYear and endYear instance variables if the data set is a local folder. This method determines
     * what range of years are in the data set by checking files from the year 1850 - 2020 and seeing whether or not they exist
     * within the folder specified. The pathname of the data MUST NOT end with a "/", as that is provided in the string concatenation
     * of the method.
     */

    //checks to see what the range of years the dataset has in local files
    public void findRangeOfYears() {
        System.out.println("Compiling data ... ");
        System.out.println("Will take ~5 seconds");
        for(int year = 1850; year < 2020; year++) {
            if(typeURL == false) {
                String filename = dataLocation + "/yob" + year + ".txt";
                File f = new File(filename);
                if(f.getAbsoluteFile().exists() ==  true) {
                    if (year < this.startYear) {
                        this.startYear = year;
                    }
                    if (year > this.endYear) {
                        this.endYear = year;
                    }
                }
            }
        }
    }

    /**
     * Helper method that determines the range of the years in the data set given a URL. This method checks URL years ranging from
     * the year 1870 to 2020 and sees if they exist by calling the checkURLValid method. This method does not return anything, but it
     * sets the startYear and endYear instance variables. The inputted URL MUST END in a "/".
     */

    //checks to see what the range of years the dataset has for a URL
    public void findRangeOfYearsURL() {
        System.out.println("Retrieving .txt files from website ... ");
        System.out.println("This will take ~15 seconds depending on WiFi connection");
        for(int year = 1870; year < 2020; year++) {
            String website = dataLocation + "yob" + year + ".txt";
            if(checkURLValid(website)) {
                if (year < this.startYear) {
                    this.startYear = year;
                }
                if (year > this.endYear) {
                    this.endYear = year;
                }
            }
        }
    }

    /**
     * This method is responsible for setting up the majority of the data. Given a year, the method
     * sets up an appropriate scanner depending on whether the data location is a URL or a local file system. The
     * method then reads through each line of the file, parses it, and stores it in the proper instance variable to set
     * the data up.
     * @param year The year for which text file it needs to parse and store the data for
     * @throws IOException If the user inputs an improper year.
     */

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

    /**
     * This method MUST be called on the object when the BabyNames instance is created to set up all the data. This method calls
     * the findRangeOfYears or findRangeOfYearsURL method to determine the range of years in the data set depending on whether
     * the data is from a local file system or a URL. This method then loops through the startYear and endYear and calls the setDataInYear
     * method for each year.
     * @throws IOException Throws an exception because of setDataInYear.
     */

    //stores data in the data structures for ALL years
    public void storeAllData() throws IOException {
        if(typeURL == false) {
            findRangeOfYears();
        }
        else {
            findRangeOfYearsURL();
        }
        if(this.startYear == Integer.MAX_VALUE || this.endYear == Integer.MIN_VALUE) {
            System.out.println("The source is empty or does not contain valid data.");
        }
        for(int i = this.startYear; i < this.endYear + 1; i++) {
            setDataInYear(i);
        }
    }

    /**
     * This method returns which global HashMap to use depending on the user's input of gender.
     * @param gender The gender the question is asking for.
     * @return returns global variable HashMap that should be used.
     */

    //picks which map to work with depending on the specified gender
    public HashMap<Integer, HashMap<String, Integer>> whichRankingMap(String gender) {
        if(gender.toLowerCase().equals("male") || gender.toLowerCase().equals("m")) {
            return this.maleRankings;
        }
        else if (gender.toLowerCase().equals("female") || gender.toLowerCase().equals("f")){
            return this.femaleRankings;
        }
        return new HashMap<>();
    }

    /**
     * A helper method that checks whether the user inputted a proper gender. The methods are not case senstive,
     * so the user can input "m" or "male" (with different cases) for the male gender and "f" or "female" (with varying cases)
     * for the female gender.
     * @param gender
     * @return A boolean telling whether or not the inputted gender is valid.
     */

    //checks to see if user inputted a valid gender
    public boolean validGender(String gender) {
        if (!(gender.toLowerCase().equals("male") || gender.toLowerCase().equals("m") ||
                gender.toLowerCase().equals("female") || gender.toLowerCase().equals("f"))) {
            System.out.println("Invalid gender inputted");
            return false;
        }
        else {
            return true;
        }
    }

    /**
     * A helper method that checks whether the user inputted a proper year.
     * @param year Check and see if this year is valid
     * @return A boolean telling whether or not the year is valid.
     */

    public boolean validYear(int year) {
        if(startYear > year || year > endYear) {
            System.out.println("Invalid year(s) inputted");
            return false;
        }
        return true;
    }

    /**
     * Return the most popular name within a certain year. This is a helper method used by a couple of other methods.
     * @param year Year to find most popular name.
     * @return A String of the most popular name in that year.
     */

    //returns the most popular name within a certain year
    public String mostPopularName(int year) {
        int max = 0;
        String topPerson = "";
        for(String person : totalPopular.get(year).keySet()) {
            if (totalPopular.get(year).get(person) > max) {
                max = totalPopular.get(year).get(person);
                topPerson = person;
            }
        }
        return topPerson;
    }

}


