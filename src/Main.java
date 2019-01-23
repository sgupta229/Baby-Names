import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Setting up constructors for tests");

        //Accessing a url
        BabyNames urlObject = new BabyNames("https://www2.cs.duke.edu/courses/compsci307/spring19/assign/01_data/data/", true);
        urlObject.storeAllData();

        //accessing data from file system
        BabyNames fileObject = new BabyNames("/Users/SGGS1234/Desktop/workspace307/data_sg390/data/fullData", false);
        fileObject.storeAllData();

        //accessing test files that I created
        BabyNames testFileObject = new BabyNames("/Users/SGGS1234/Desktop/workspace307/data_sg390/data/testData", false);
        testFileObject.storeAllData();
        System.out.print("\n\n\n");

        //b.storeAllData();

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

        ///////////////
        //ERROR TESTS//
        ///////////////

        //an invalid or empty data source
        BabyNames errorTest = new BabyNames("/Users/SGGS1234/Desktop/workspace307/data_sg390/data/emptyFolderTest", false);
        System.out.println("TESTING AN EMPTY LOCAL SOURCE");
        errorTest.storeAllData();

        //testing a fake URL
        System.out.println("\nTESTING A FAKE URL");
        errorTest = new BabyNames("fakeurl.org", true);
        errorTest.storeAllData();

        //testing a URL with no .txt files
        System.out.println("\nTESTING A URL WITH NO .TXT FILES");
        errorTest = new BabyNames("google.com", true);
        errorTest.storeAllData();

        //testing a range of years that does not fit the source data
        System.out.println("\nTESTING RANGE OF YEARS THAT DOES NOT FIT IN THE SOURCE DATA '(1850 - 2192)'");
        System.out.println(fileObject.rangeOfYears("Florence", "F", 1850, 2192));

        //testing a name that has a different case than the text files
        System.out.println("\nTESTING NAMES THAT DO NOT MATCH CASE IN THE FILES");
        System.out.print("Average rank of name fLoReNcE from 1880 - 1908: ");
        System.out.println(fileObject.averageGenderRankOverRange("fLoReNcE", "F", 1880, 1908));

        //testing an invalid gender
        System.out.println("\nTESTING AN INVALID GENDER");
        System.out.print("Average rank of name James (fakeGender) from 1880 - 1908: ");
        System.out.println(fileObject.averageGenderRankOverRange("James", "fakeGender", 1880, 1908));

        //////////////////
        //QUESTION TESTS//
        //////////////////


//        //QUESTION 1 TESTS
//        System.out.println("Find matching rank of John (M) in 1980");
//        System.out.println(b.equalRankInRecentYear("John", "m", 1980));
//        System.out.println("\n");
//
//        //QUESTION 2 TEST
//        System.out.println("Average gender rank over range");
//        System.out.println(b.averageGenderRankOverRange("Sam", "F", 2004, 2017));
//        System.out.println("\n");
//
//        //QUESTION 3 TEST
//        System.out.println("Average rank over range");
//        System.out.println(b.averageRankOverRange("Sam", 2005, 2017));
//        System.out.println("\n");
//
//        //QUESTION 4 TEST
//        System.out.println("RECENT YEARS RANK");
//        System.out.println(b.averageRankForRecentYears("John", 5));
//        System.out.println("\n");
//
//        //QUESTION 5 TEST
//        System.out.println(b.mostPopularName(2000, 2017));
//        System.out.println("\n");
//
//        //QUESTION 6 TEST
//        System.out.println(b.mostPopularGender(2000, 2017));
//        System.out.println("\n");
//
//        //QUESTION 7 TEST
//        System.out.println(b.popularLetter("female", 2000, 2002));
//        System.out.println("\n");
//
//        //QUESTION 8 TEST
//        System.out.println(b.popularLetter("both", 2002, 2004));
//        System.out.println("\n");
//
    }
}
