import java.util.*;
import java.io.*;
import java.net.*;

public class BabyNames {

    private HashMap<Integer, HashMap<String, Integer>> totalPopular = new HashMap<>();
    private HashMap<Integer, HashMap<String, Integer>> maleRankings = new HashMap<>();
    private HashMap<Integer, HashMap<String, Integer>> femaleRankings = new HashMap<>();
    private HashMap<Integer, HashMap<String, Integer>> genderRankings = new HashMap<>();
    private int startYear = Integer.MAX_VALUE;
    private int endYear = Integer.MIN_VALUE;

    //TO CHANGE LOCATION OF THE DATA
    //ENTER the pathname to the folder or the URL to the website
    private String dataLocation;
    private boolean typeURL;

    public BabyNames(String pathname, boolean url) {
        dataLocation = pathname;
        typeURL = url;
    }

    public void setTypeURL(boolean type) {
        this.typeURL = type;
    }

    public void setDataLocation(String location) {
        this.dataLocation = location;
    }

    public int getStartYear() {
        return startYear;
    }

    public int getEndYear() {
        return endYear;
    }

    //reports the rankings of a specified name for all years
    public ArrayList<Integer> allYears(String name, String gender) {
        return rangeOfYears(name, gender, this.startYear, this.endYear);
    }

    //reports the rankings of a specified name for the range of years specified
    public ArrayList<Integer> rangeOfYears(String name, String gender, int start, int end) {
        name = name.toLowerCase();
        ArrayList<Integer> popOfPerson = new ArrayList<>();
        HashMap<Integer, HashMap<String, Integer>> use;
        if(validYear(start) == false || validYear(end) == false) {
            System.out.println("Invalid year inputted");
            return popOfPerson;
        }
        if(validGender(gender) == false) {
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

    //QUESTION 1 - name/gender pair with the same rank in the most recent year
    public String equalRankInRecentYear(String name, String gender, int year) {
        HashMap<Integer, HashMap<String, Integer>> use;
        name = name.toLowerCase();
        if(validYear(year) == false) {
            System.out.println("Invalid year inputted");
            return "";
        }
        if (validGender(gender) == false) {
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

    //QUESTION 2 - reports average rank for a specific name within the range of years
    public double averageGenderRankOverRange(String name, String gender, int start, int end) {
        name = name.toLowerCase();
        int calculate = 0;
        HashMap<Integer, HashMap<String, Integer>> use;
        if (validYear(start) == false || validYear(end) == false) {
            System.out.println("Invalid year inputted");
            return -1;
        }
        if(validGender(gender) == false) {
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
        return ((double) calculate / ((double) end - (double) start + 1.0));
    }

    //QUESTION 3 - report average rank of the name regardless of gender
    public double averageRankOverRange(String name, int start, int end) {
        name = name.toLowerCase();

        if (validYear(start) == false || validYear(end) == false) {
            System.out.println("Invalid year inputted");
            return -1;
        }

        HashMap<String, Integer> use;
        int averagePopularity = 0;
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
        return averagePopularity / (end - start + 1);
    }

    //QUESTION 4 - reports average rank of a name for the most recent number of years
    public double averageRankForRecentYears(String name, int recentYears) {
        name = name.toLowerCase();
        return averageRankOverRange(name, this.endYear - recentYears + 1, this.endYear);
    }

    //QUESTION 5 - report which name was ranked first most often AND how many years it came first
    public String mostPopularName(int start, int end) {

        if (validYear(start) == false || validYear(end) == false) {
            System.out.println("Invalid year inputted");
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

    //QUESTION 6 - report which gender was more popular and how many years this gender was the most popular name
    public String mostPopularGender(int start, int end) {
        if (validYear(start) == false || validYear(end) == false) {
            System.out.println("Invalid year inputted");
            return "";
        }
        int maleWins = 0;
        int femaleWins = 0;
        int ties = 0;
        for(int i = start; i < end + 1; i++) {
            if(genderRankings.get(i).get("m") > genderRankings.get(i).get("f")) {
                maleWins++;
            }
            else if (genderRankings.get(i).get("m") < genderRankings.get(i).get("f")) {
                femaleWins++;
            }
            else {
                ties++;
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



    //QUESTIONS 7 AND 8 - given a gender (or both), reports the most popular letter that names starts with
    //and returns a list of all the names that start with that letter
    public String popularLetter(String gender, int start, int end) {
        HashMap<Character, Integer> letterCounts = new HashMap<>();
        HashMap<Integer, HashMap<String, Integer>> use;
        if (validYear(start) == false || validYear(end) == false) {
            System.out.println("Invalid year inputted");
            return "";
        }
        if(validGender(gender) == false) {
            return "";
        }
        if(gender.toLowerCase().equals("f") || gender.toLowerCase().equals("female")) {
            use = femaleRankings;
        }
        else if(gender.toLowerCase().equals("m") || gender.toLowerCase().equals("male")) {
            use = maleRankings;
        }
        else {
            use = totalPopular;
        }
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

    //checks to make sure user inputted a valid year into a method
    public boolean validYear(int year) {
        return(startYear <= year &&  year <= endYear);
    }

    //checks to see what the range of years the dataset has
    public void findRangeOfYears() {
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

    public void setDataInYearURL(int year) {
        HashMap<String, Integer> maleYear = new HashMap<>();
        HashMap<String, Integer> femaleYear = new HashMap<>();

        try {
            URL url = new URL(this.dataLocation + "yob" + year + ".txt");
            Scanner file = new Scanner(url.openStream());

            while(file.hasNext() == true) {
                String currentName = file.next();
                String[] currentNameArray = currentName.split(",");
                if(currentNameArray[1].equals("F")) {
                    femaleYear.put(currentNameArray[0], Integer.parseInt(currentNameArray[2]));
                }
                else {
                    maleYear.put(currentNameArray[0], Integer.parseInt(currentNameArray[2]));
                }
            }
        }
        catch (IOException ex) {
            System.out.println("Website not found");
        }
    }

    //stores the data into the hashmaps for the specified year
    public void setDataInYear(int year) {

        HashMap<String, Integer> maleYearRank = new HashMap<>();
        HashMap<String, Integer> femaleYearRank = new HashMap<>();
        HashMap<String, Integer> totalPop = new HashMap<>();
        HashMap<String, Integer> genderYear = new HashMap<>();

        genderYear.put("f", 0);
        genderYear.put("m", 0);

        int femaleCount = 0;
        int maleCount = 0;

        try {
            String filename = dataLocation + "/yob" + year + ".txt";
            File test = new File(filename);

            Scanner file = new Scanner(test);

            while(file.hasNext() == true) {
                String currentName = file.next();
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
        catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    //stores data in the data structures for ALL years
    public void storeAllData() {
        findRangeOfYears();
        if(typeURL == false) {
            for(int i = this.startYear; i < this.endYear + 1; i++) {
                setDataInYear(i);
            }
        }
        else {
            for(int i = this.startYear; i < this.endYear + 1; i++) {
                setDataInYearURL(i);
            }
        }
    }

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


