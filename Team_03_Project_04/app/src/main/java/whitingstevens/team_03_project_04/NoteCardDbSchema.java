package whitingstevens.team_03_project_04;

/**
 * Created by 121667 on 12/3/2015.
 */
public class NoteCardDbSchema {
    public static final class FolderTable{
        public static final String NAME = "Folders";

        public static final class Cols{
            public static final String FOLDER_NAME = "folderName";
            public static final String UUID = "uuid";
        }
    }

    public static final class DeckTable{
        public static final String NAME = "Decks";

        public static final class Cols{
            public static final String DECK_NAME = "name";
            public static final String FOLDER_UUID = "folderUuid";
            public static final String UUID = "uuid";
        }
    }
    public static final class CardTable{
        public static final String NAME = "Cards";

        public static final class Cols{
            public static final String UUID = "uuid";
            public static final String BACK_TEXT = "backText";
            public static final String FRONT_TEXT = "frontText";
            public static final String DATE_CREATED = "dateCreated";
            public static final String DECK_UUID = "deckUuid";
        }
    }
}
