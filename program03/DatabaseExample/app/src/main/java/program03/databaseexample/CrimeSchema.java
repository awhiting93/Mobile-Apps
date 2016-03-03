package program03.databaseexample;

/**
 * Created by jsparks on 11/17/2015.
 */
public class CrimeSchema {
    public static final class CrimeTable {
        public static final String NAME = "crimes";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String DATE = "date";
            public static final String SOLVED = "solved";

        }
    }
}
