import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        BabyNames b = new BabyNames("https://www2.cs.duke.edu/courses/spring19/compsci307/assign/01_data/data/", true);
        b.storeAllData();
        System.out.println(b.getStartYear());
        System.out.println(b.getEndYear());
//        System.out.println("Popularity of Florence (F) from 1880-1890:");
//        System.out.println(b.rangeOfYears("Florence", "F", 1880, 1890));
//        System.out.println("\n");
//
//        System.out.println("Popularity of Florence (F) for ALL years:");
//        System.out.println(b.allYears("Florence","F"));
//        System.out.println("\n");
//
//        System.out.println("Popularity of John (M) from 1927-1929:");
//        System.out.println(b.rangeOfYears("John", "M", 1927, 1929));
//        System.out.println("\n");
//        System.out.println("Popularity of John (M) for ALL years:");
//        System.out.println(b.allYears("John","M"));
//        System.out.println("\n");
//
//        System.out.println("Popularity of Volney (M) from 1800-2017:");
//        System.out.println(b.rangeOfYears("Volney", "M", 1800, 2017));
//        System.out.println("\n");
//        System.out.println("Popularity of Volney (M) for ALL years:");
//        System.out.println(b.allYears("Volney","M"));
//        System.out.println("\n");
//
//        //Test to see output of a fake name
//        System.out.println("Popularity of FAKENAME (M) from 2000-2017:");
//        System.out.println(b.rangeOfYears("FAKENAME", "M", 2000, 2017));
//        System.out.println("\n");
//
//        //Test to see output of a fake gender
//        System.out.println("Popularity of John (sdklfj) from 2000-2017:");
//        System.out.println(b.rangeOfYears("John", "sdklfj", 2000, 2017));
//        System.out.println("\n");

        //QUESTION 1 TEST
        System.out.println("Find matching rank of John (M) in 1980");
        System.out.println(b.equalRankInRecentYear("John", "m", 1980));
        System.out.println("\n");

        //QUESTION 2 TEST
        System.out.println("Average gender rank over range");
        System.out.println(b.averageGenderRankOverRange("Sam", "F", 2004, 2017));
        System.out.println("\n");

        //QUESTION 3 TEST
        System.out.println("Average rank over range");
        System.out.println(b.averageRankOverRange("Sam", 2005, 2017));
        System.out.println("\n");

        //QUESTION 4 TEST
        System.out.println("RECENT YEARS RANK");
        System.out.println(b.averageRankForRecentYears("John", 5));
        System.out.println("\n");

        //QUESTION 5 TEST
        System.out.println(b.mostPopularName(2000, 2017));
        System.out.println("\n");

        //QUESTION 6 TEST
        System.out.println(b.mostPopularGender(2000, 2017));
        System.out.println("\n");

        //QUESTION 7 TEST
        System.out.println(b.popularLetter("female", 2000, 2002));
        System.out.println("\n");

        //QUESTION 8 TEST
        System.out.println(b.popularLetter("both", 2002, 2004));
        System.out.println("\n");

    }
}
