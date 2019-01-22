public class Main {
    public static void main(String[] args) {
        BabyNames b = new BabyNames("/Users/SGGS1234/Desktop/workspace307/data_sg390/data/fullData", false);
        b.storeAllData();
        System.out.println(b.getStartYear());
        System.out.println(b.getEndYear());

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

        System.out.println("Popularity of Volney (M) from 1800-2017:");
        System.out.println(b.rangeOfYears("Volney", "M", 1800, 2017));
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

        System.out.println("Find matching rank of John (M) in 1980");
        System.out.println(b.equalRankInRecentYear("John", "m", 1880));
        System.out.println("\n");

        System.out.println("Average gender rank over range");
        System.out.println(b.averageGenderRankOverRange("Sam", "F", 1989, 2017));
        System.out.println("\n");

        System.out.println("Average rank over range");
        System.out.println(b.averageRankOverRange("Sam", 1989, 2017));
        System.out.println("\n");

        System.out.println(b.mostPopularName(1989, 2017));
        System.out.println("\n");

        System.out.println(b.mostPopularGender(1989, 2017));
        System.out.println("\n");

        System.out.println(b.popularLetter("m", 2000, 2017));
        System.out.println("\n");

    }
}
