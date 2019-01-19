import java.util.*;

public class BabyNames {

    private HashMap<Integer, HashMap<String, Integer>> males = new HashMap<>();
    private HashMap<Integer, HashMap<String, Integer>> females = new HashMap<>();

    public void setDataInYear(int year) {

        HashMap<String, Integer> maleYear = new HashMap<>();
        HashMap<String, Integer> femaleYear = new HashMap<>();

        String filename = "yob" + year + ".txt";
        Scanner file = new Scanner (BabyNames.class.getClassLoader().getResourceAsStream(filename));
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

        males.put(year, maleYear);
        females.put(year, femaleYear);

    }

    public void storeAllData() {
        for(int i = 1880; i < 2018; i++) {
            setDataInYear(i);
        }
    }

    public ArrayList<Integer> allYears(String name, String gender) {
        return rangeOfYears(name, gender, 1880, 2017);
    }

    public ArrayList<Integer> rangeOfYears(String name, String gender, int start, int end) {
        ArrayList<Integer> popOfPerson = new ArrayList<>();
        HashMap<Integer, HashMap<String, Integer>> use;

        if(gender.equals("M") || gender.equals("male") || gender.equals("m") || gender.equals("Male")) {
            use = this.males;
        }
        else if (gender.equals("F") || gender.equals("female") || gender.equals("f") || gender.equals("Female")){
            use = this.females;
        }
        else {
            throw new IllegalArgumentException("Gender input is invalid");
        }

        for(int i = start; i < end + 1; i++) {
            HashMap<String, Integer> checkYear = use.get(i);
            Integer result = checkYear.get(name);
            if (result == null) {
                popOfPerson.add(0);
            }
            else {
                popOfPerson.add(result);
            }
        }
        return popOfPerson;
    }

    public static void main(String[] args) {
        BabyNames b = new BabyNames();
        b.storeAllData();

        System.out.println("Popularity of Florence (F) from 1880-1890:");
        System.out.println(b.rangeOfYears("Florence", "F", 1880, 1890));
        System.out.println("\n");
        System.out.println("Popularity of Florence (F) for ALL years:");
        System.out.println(b.allYears("Florence","F"));
        System.out.println("\n");

        System.out.println("Popularity of John (M) from 1927-1929:");
        System.out.println(b.rangeOfYears("John", "M", 1927, 1929));
        System.out.println("\n");
        System.out.println("Popularity of John (M) for ALL years:");
        System.out.println(b.allYears("John","M"));
        System.out.println("\n");

        System.out.println("Popularity of Volney (M) from 2000-2017:");
        System.out.println(b.rangeOfYears("Volney", "M", 2000, 2017));
        System.out.println("\n");
        System.out.println("Popularity of Volney (M) for ALL years:");
        System.out.println(b.allYears("Volney","M"));
        System.out.println("\n");

        //Test to see output of a fake name
        System.out.println("Popularity of FAKENAME (M) from 2000-2017:");
        System.out.println(b.rangeOfYears("FAKENAME", "M", 2000, 2017));
        System.out.println("\n");

        //Test to see output of a fake gender
        System.out.println("Popularity of John (sdklfj) from 2000-2017:");
        System.out.println(b.rangeOfYears("John", "sdklfj", 2000, 2017));
        System.out.println("\n");
    }
}


