import java.util.*;
import java.io.*;
import java.net.*;

public class BabyNames {

    private HashMap<Integer, HashMap<String, Integer>> malePopular = new HashMap<>();
    private HashMap<Integer, HashMap<String, Integer>> femalePopular = new HashMap<>();
    private int startYear = Integer.MAX_VALUE;
    private int endYear = Integer.MIN_VALUE;
    private HashMap<Integer, HashMap<String, Integer>> maleRankings = new HashMap<>();
    private HashMap<Integer, HashMap<String, Integer>> femaleRankings = new HashMap<>();
    private HashMap<Integer, HashMap<String, Integer>> noGenderRankings = new HashMap<>();


    //TO CHANGE LOCATION OF THE DATA
    //ENTER the pathname to the folder or the URL to the website
    private String dataLocation;
    private boolean typeURL;

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

    public boolean URLisValid(String url) {
        try {
            new URL(url).toURI();
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public boolean validYear(int year) {
        return(startYear <= year &&  year <= endYear);
    }

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
            else {
                if(URLisValid(dataLocation + "yob" + year + ".txt") == true) {
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

    public void setRanks() {
        for(int i = 1880; i < 2018; i++) {

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

            malePopular.put(year, maleYear);
            femalePopular.put(year, femaleYear);
        }

        catch (IOException ex) {
            System.out.println("Website not found");
        }
    }

    public void setDataInYear(int year) {

        HashMap<String, Integer> maleYear = new HashMap<>();
        HashMap<String, Integer> femaleYear = new HashMap<>();

        HashMap<String, Integer> maleYearRank = new HashMap<>();
        HashMap<String, Integer> femaleYearRank = new HashMap<>();

        HashMap<String, Integer> noGenderYearRank = new HashMap<>();
        int femaleCount = 0;
        int maleCount = 0;
        int generalCount = 0;

       //String filename = this.dataLocation + "/yob" + year + ".txt";
        //Scanner file = new Scanner (BabyNames.class.getClassLoader().getResourceAsStream(filename));

        try {
            String filename = dataLocation + "/yob" + year + ".txt";
            File test = new File(filename);

            Scanner file = new Scanner(test);

            while(file.hasNext() == true) {
                generalCount++;
                String currentName = file.next();
                String[] currentNameArray = currentName.split(",");
                if(currentNameArray[1].equals("F")) {
                    femaleCount++;
                    femaleYear.put(currentNameArray[0], Integer.parseInt(currentNameArray[2]));
                    femaleYearRank.put(currentNameArray[0], femaleCount);
                }
                else {
                    maleCount++;
                    maleYear.put(currentNameArray[0], Integer.parseInt(currentNameArray[2]));
                    maleYearRank.put(currentNameArray[0], maleCount);
                }
                noGenderYearRank.put(currentNameArray[0], generalCount);
            }

            malePopular.put(year, maleYear);
            femalePopular.put(year, femaleYear);

            maleRankings.put(year, maleYearRank);
            femaleRankings.put(year, femaleYearRank);
            noGenderRankings.put(year, noGenderYearRank);
        }

        catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

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

    public HashMap<Integer, HashMap<String, Integer>> whichRankingMap(String gender) {
        if(gender.toLowerCase().equals("male") || gender.toLowerCase().equals("m")) {
            return this.maleRankings;
        }
        else if (gender.toLowerCase().equals("female") || gender.toLowerCase().equals("f")){
            return this.femaleRankings;
        }
        return new HashMap<>();
    }

    public HashMap<Integer, HashMap<String, Integer>> whichPopularityMap(String gender) {
        if(gender.toLowerCase().equals("male") || gender.toLowerCase().equals("m")) {
            return this.malePopular;
        }
        else if (gender.toLowerCase().equals("female") || gender.toLowerCase().equals("f")){
            return this.femalePopular;
        }
        return new HashMap<>();
    }

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

    public ArrayList<Integer> allYears(String name, String gender) {
        return rangeOfYears(name, gender, this.startYear, this.endYear);
    }

    public ArrayList<Integer> rangeOfYears(String name, String gender, int start, int end) {
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

    //method for question number 1
    public String equalRankInRecentYear(String name, String gender, int year) {
        HashMap<Integer, HashMap<String, Integer>> use;

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

    //method for question number 2
    public int averageGenderRankOverRange(String name, String gender, int start, int end) {
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
            calculate += use.get(i).get(name);
        }
        return(calculate / (end - start + 1));
    }



}


