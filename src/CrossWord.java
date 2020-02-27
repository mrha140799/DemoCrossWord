import java.util.*;

public class CrossWord {
    private static final int TABLE_LENGTH = 13;
    private static final String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static void main(String[] args) {
        char[][] table = new char[TABLE_LENGTH][TABLE_LENGTH];
        int totalNumberWord = 0;
        List<String> arrayWord = new ArrayList<>();
        Random random = new Random();
        arrayWord.add("CROSSWORD");
        arrayWord.add("exception");
        arrayWord.add("Reverse");
        arrayWord.add("STRING");
        arrayWord.add("LIGHT");
        arrayWord.add("FIGHTING");
        arrayWord.add("SAW");
        arrayWord.add("printf");
        arrayWord.add("functionality");
        arrayWord.add("generate");
        arrayWord.add("characters");
        arrayWord.add("involves");
        arrayWord.add("birthday");
        arrayWord.add("connection");
        arrayWord.add("positively");
        arrayWord.add("holiday");
        arrayWord.add("hollywood");
        arrayWord.add("event");
        arrayWord.add("announcement");
        System.out.println("array before sort: ");
        System.out.println(arrayWord);

        for (String word : arrayWord) {
            totalNumberWord += word.length();
        }

        for (int i = 0; i < arrayWord.size() - 1; i++) {
            for (int j = i + 1; j < arrayWord.size(); j++) {
                if (arrayWord.get(i).length() < arrayWord.get(j).length()) {
                    arrayWord.add(i, arrayWord.get(j));
                    arrayWord.remove(j + 1);
                }
            }
        }
        System.out.println("array after sort: ");
        System.out.println(arrayWord);

        if (addWordToTable(table, arrayWord)) {
            System.out.println("table");
            for (int i = 0; i < TABLE_LENGTH; i++) {
                for (int j = 0; j < TABLE_LENGTH; j++) {
                    char charRandom = SALTCHARS.charAt(random.nextInt(26));
                    System.out.print((table[i][j] == '\0') ? ("-|") : (table[i][j] + "|"));
                }
                System.out.println();
            }
        }
        WordPositionManagement management = WordPositionManagement.getInstance();
        Iterator listWordPositionIterator = management.getWordPositionMap().entrySet().iterator();
        while (listWordPositionIterator.hasNext()) {
            Map.Entry listWordPosition = (Map.Entry) listWordPositionIterator.next();
            System.out.println("word: " + listWordPosition.getKey() + "- position: " + listWordPosition.getValue());
        }
        System.out.println("Total number word of list: " + totalNumberWord);
    }
    public static void printTable(String title,char[][] table) {
        Random random = new Random();
        System.out.println(title);
        for (int i = 0; i < TABLE_LENGTH; i++) {
            for (int j = 0; j < TABLE_LENGTH; j++) {
                char charRandom = SALTCHARS.charAt(random.nextInt(26));
                System.out.print((table[i][j] == '\0') ? ("-|") : (table[i][j] + "|"));
            }
            System.out.println();
        }
    }

    private static boolean addWordToTable(char[][] table, List<String> wordArray) {
        List<Integer> gridPosition = new ArrayList<>();
        if (wordArray.size() == 0) return true;
        WordPositionManagement management = WordPositionManagement.getInstance();
        Random random = new Random();
        int run = 0;
        while (run < TABLE_LENGTH) {
            int direction = random.nextInt(3);
            int revert = random.nextInt(2);
            String word = wordArray.get(0).toUpperCase();
            StringBuilder stringBuilder = new StringBuilder(word);
            if (revert == 1) {
                word = stringBuilder.reverse().toString();
            }
            char[] wordCharArray = word.toCharArray();
            String wordDelete = wordArray.get(0);
            int row = random.nextInt(TABLE_LENGTH);
            int column = random.nextInt(TABLE_LENGTH);
            if (direction == 0) {
                if (isValid(table, wordCharArray, row, column, direction, gridPosition)) {
                    //them vao table
                    for (int i = 0; i < wordCharArray.length; i++)
                        table[row][column + i] = wordCharArray[i];
                    wordArray.remove(wordDelete);
                    management.addWordPosition(row, column, wordDelete, direction);
                    printTable("create",table);
                    System.out.println("Add word: "+wordDelete);
                    run++;
                    if (addWordToTable(table, wordArray)) return true;
                    //xoa khoi table
                    for (int i = 0; i < wordCharArray.length; i++)
                        table[row][column + i] = '\0';
                    printTable("delete",table);
                    System.out.println("delete word: "+wordDelete);
                    //add lai tu vua xoa de tro lai them lai
                    wordArray.add(0, wordDelete);
                    management.removeWordPosition(wordDelete);
                    int grdPosition = row * TABLE_LENGTH + column;
                    gridPosition.add(grdPosition);
                    run--;
                }else {
                    return false;
                }
            }
            if (direction == 1) {
                if (isValid(table, wordCharArray, row, column, direction, gridPosition)) {
                    //them vao table
                    for (int i = 0; i < wordCharArray.length; i++)
                        table[row + i][column] = wordCharArray[i];
                    wordArray.remove(wordDelete);
                    management.addWordPosition(row, column, wordDelete, direction);
                    printTable("create",table);
                    System.out.println("Add word: "+wordDelete);
                    run++;
                    if (addWordToTable(table, wordArray)) return true;
                    //xoa khoi table
                    for (int i = 0; i < wordCharArray.length; i++)
                        table[row + i][column] = '\0';
                    printTable("delete",table);
                    System.out.println("delete word: "+wordDelete);
                    //add lai tu vua xoa de tro lai them lai
                    wordArray.add(0, wordDelete);
                    management.removeWordPosition(wordDelete);
                    int grdPosition = row * TABLE_LENGTH + column;
                    gridPosition.add(grdPosition);
                    run--;
                }else {
                    return false;
                }
            }
            if (direction == 2) {
                if (isValid(table, wordCharArray, row, column, direction, gridPosition)) {
                    //them vao table
                    for (int i = 0; i < wordCharArray.length; i++)
                        table[row + i][column + i] = wordCharArray[i];
                    wordArray.remove(wordDelete);
                    management.addWordPosition(row, column, wordDelete, direction);
                    printTable("create",table);
                    System.out.println("Add word: "+wordDelete);
                    run++;
                    if (addWordToTable(table, wordArray)) return true;
                    //xoa khoi table
                    for (int i = 0; i < wordCharArray.length; i++)
                        table[row + i][column + i] = '\0';
                    printTable("delete",table);
                    System.out.println("delete word: "+wordDelete);
                    //add lai tu vua xoa de tro lai them lai
                    wordArray.add(0, wordDelete);
                    management.removeWordPosition(wordDelete);
                    int grdPosition = row * TABLE_LENGTH + column;
                    gridPosition.add(grdPosition);
                    run--;
                }else {
                    return false;

                }
            }

        }
        return false;
    }

    private static boolean isValid(char[][] table, char[] wordCharArray, int row, int column, int direction, List<Integer> gridPosition) {
        int grdPosition = row * TABLE_LENGTH + column;
        if (gridPosition.size() >= Math.pow(TABLE_LENGTH, 2)) return false;
        if (gridPosition.contains(grdPosition)) return false;
        if (direction == 0) {   //ngang
            for (int i = 0; i < wordCharArray.length; i++) {
                if ((column > TABLE_LENGTH - wordCharArray.length) || (table[row][column + i] != '\0' && table[row][column + i] != wordCharArray[i])) {
                    return false;
                }
            }
        } else if (direction == 1) { //doc
            for (int i = 0; i < wordCharArray.length; i++) {
                if ((row > TABLE_LENGTH - wordCharArray.length) || (table[row + i][column] != '\0' && table[row + i][column] != wordCharArray[i])) {
                    return false;
                }
            }
        } else if (direction == 2) { //cheo
            for (int i = 0; i < wordCharArray.length; i++) {
                if ((row > TABLE_LENGTH - wordCharArray.length || column > TABLE_LENGTH - wordCharArray.length) || (table[row + i][column + i] != '\0' && table[row + i][column + i] != wordCharArray[i])) {
                    return false;
                }
            }
        }
        return true;
    }
}
