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
        System.out.println("\nTESTING A FAKE URL (fakeurl.org)");
        errorTest = new BabyNames("fakeurl.org", true);
        errorTest.storeAllData();

        //testing a URL with no .txt files
        System.out.println("\nTESTING A URL WITH NO .TXT FILES (google.com)");
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


        //QUESTION 1 TESTS
        System.out.println("\nTEST QUESTION #1");
        //see if method works with local files
        System.out.println("Find matching rank of John (M) in 1980 (local files)");
        System.out.println(fileObject.equalRankInRecentYear("John", "m", 1980));
        System.out.println("\n");
        //see if method works with URL and matches local file result
        System.out.println("Find matching rank of John (M) in 1980 (url files)");
        System.out.println(urlObject.equalRankInRecentYear("John", "m", 1980));
        System.out.println("\n");
        //see if method works with test files with John not being in the file
        System.out.println("Find matching rank of John (M) in 2016 (test files)");
        System.out.println(testFileObject.equalRankInRecentYear("John", "m", 2016));
        System.out.println("\n");

        //QUESTION 2 TEST
        System.out.println("\nTEST QUESTION #2");
        //see if local files produce no rank if not ranked in one of the years
        System.out.println("Average gender rank over range (local files)");
        System.out.println(fileObject.averageGenderRankOverRange("Volney", "F", 2004, 2017));
        System.out.println("\n");
        //check speed of url object for ALL years
        System.out.println("Average gender rank over range (url files)");
        System.out.println(urlObject.averageGenderRankOverRange("John", "m", 1880, 2017));
        System.out.println("\n");
        //check rank with test files for only one year to see if it is correct
        System.out.println("Average gender rank over range (test files - one year)");
        System.out.println(testFileObject.averageGenderRankOverRange("Brio", "m", 2017, 2017));
        System.out.println("\n");


        //QUESTION 3 TEST
        System.out.println("\nTEST QUESTION #3");
        //Mary started becoming more popular towards the end of the 80s
        //Check rank of John for 1880 - 1885 and then 1880 - 1890
        //Should be slightly greater than 1
        System.out.println("Check average rank of John from 1880 - 1885");
        System.out.println(fileObject.averageRankOverRange("John", 1880, 1885));
        System.out.println("\n");
        System.out.println("Check average rank of John from 1880 - 1890");
        System.out.println(fileObject.averageRankOverRange("John", 1880, 1890));
        System.out.println("\n");
        //Emma is number 1 for all years except 2017 where she is the last female. Check and see if there is a valid
        //rank
        System.out.println("Check average rank of Emma from 2014 - 2017 (with an outlier from being #1)");
        System.out.println(testFileObject.averageRankOverRange("Emma", 2014, 2017));
        System.out.println("\n");

        //QUESTION 4 TEST
        //this method uses the same method as question #3
        System.out.println("\nTEST QUESTION #4");
        //Check invalid range
        System.out.println("RECENT YEARS RANK url files with negative year input");
        System.out.println(urlObject.averageRankForRecentYears("Michelle", -1));
        System.out.println("\n");
        //most popular name in the last few years
        System.out.println("Check for average manually in short range of years");
        System.out.println(urlObject.averageRankForRecentYears("Sahil", 4));
        System.out.println("\n");
        //check test file beyond range of years
        System.out.println("RECENT YEARS RANK test files with greater than range input");
        System.out.println(testFileObject.averageRankForRecentYears("Emma", 10));
        System.out.println("\n");

        //QUESTION 5 TEST
        System.out.println("\nTEST QUESTION #5");
        //check to see if the same name is most popular within a short range
        System.out.println("Check for consistency of most popular name with a short range of years");
        System.out.println(fileObject.mostPopularName(1940, 1942));
        System.out.println("\n");
        //return all time most popular name according to website of what most popular name is
        System.out.println("\nSee what most popular name is from 1880 and cross check with website result");
        System.out.println(urlObject.mostPopularName(1880, 2017));
        System.out.println("\n");
        //Use test files to check Emma in the 4 years. She was most popular for 3 of the years.
        System.out.println("\nCheck to see if test files reveals emma as being most popular 3/4 years");
        System.out.println(testFileObject.mostPopularName(2014, 2017));
        System.out.println("\n");

        //QUESTION 6 TEST
        System.out.println("\nTEST QUESTION #6");
        //check and see if males have always been the most popular gender
        System.out.println("Check to make sure males have been most popular for all years");
        System.out.println(fileObject.mostPopularGender(1880, 2017));
        System.out.println("\n");
        //check to see if functino works across one year and can output female
        System.out.println("Check to see female popularity in just one year");
        System.out.println(testFileObject.mostPopularGender(2017, 2017));
        System.out.println("\n");
        //check test files and see that female should be ranked first for all years
        System.out.println("Check to see that female should be most popular gender across all 4 years in test files");
        System.out.println(testFileObject.mostPopularGender(2014, 2017));
        System.out.println("\n");

        //QUESTION 7 and 8 TEST
        System.out.println("\nTEST QUESTION #7 and #8 (they use the same method");
        //check females in test files
        System.out.println("Check to see which female letter is most popular in test files");
        System.out.println(testFileObject.popularLetter("female", 2014, 2017));
        System.out.println("\n");
        //check method for males for test files
        System.out.println("Check to see which male letter is most popular in test files");
        System.out.println(testFileObject.popularLetter("male", 2014, 2017));
        System.out.println("\n");
        //check method for both males and females
        System.out.println("Check to see which letter is most popular in test files");
        System.out.println(testFileObject.popularLetter("both", 2014, 2017));
        System.out.println("\n");
        //check method for females in actual data set from 2000-2017
        System.out.println("Check to see which letter is most popular for females in actual data set (URL) from 2000-2017");
        System.out.println(fileObject.popularLetter("female", 2000, 2017));
        System.out.println("\n");
        //check method for males in actual data set from 2000-2017
        System.out.println("Check to see which letter is most popular for males in actual data set (URL) from 2000-2017");
        System.out.println(fileObject.popularLetter("male", 2000, 2017));
        System.out.println("\n");
        //check method for males in actual data set from 2000-2017
        System.out.println("Check to see which letter is most popular for both males and females in actual data set (URL) from 2000-2017");
        System.out.println(fileObject.popularLetter("both", 2000, 2017));
        System.out.println("\n");
    }
}
