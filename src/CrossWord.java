import java.util.*;

public class CrossWord {
    private static final int TABLE_LENGTH = 10;
    private static final String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static void main(String[] args) {
        char[][] table = new char[TABLE_LENGTH][TABLE_LENGTH];
        int totalNumberWord;
        Stack<String> arrayWord = new Stack<>();


        Random random = new Random();
        arrayWord.add("CROSSWORD");
        arrayWord.add("exception");
        arrayWord.add("Reverse");
        arrayWord.add("STRING");
        arrayWord.add("LIGHT");
        arrayWord.add("FIGHTING");
        arrayWord.add("SAW");
        arrayWord.add("printf");
        arrayWord.add("functional");
        arrayWord.add("generate");
        arrayWord.add("characters");
        arrayWord.add("involves");
        arrayWord.add("birthday");
        arrayWord.add("connection");
        arrayWord.add("positively");
        arrayWord.add("holiday");
        arrayWord.add("hollywood");
        arrayWord.add("event");
//        arrayWord.add("announcement");
        System.out.println("array before sort: ");
        System.out.println(arrayWord);
        if (addListWordToTable(arrayWord, table)) {
            System.out.println("table");
            for (int i = 0; i < TABLE_LENGTH; i++) {
                for (int j = 0; j < TABLE_LENGTH; j++) {
                    char charRandom = SALTCHARS.charAt(random.nextInt(26));
                    System.out.print((table[i][j] == '\0') ? ("-|") : (table[i][j] + "|"));
                }
                System.out.println();
            }
        }


        sortWordArrayByLength(arrayWord);
        System.out.println("array after sort: ");
        System.out.println(arrayWord);
        totalNumberWord = getTotalNumberWord(arrayWord);
        System.out.println("Total number word of list: " + totalNumberWord);
        printAnswerKey();
    }

    private static boolean addListWordToTable(Stack<String> wordArray, char[][] table) {
        if (wordArray.size() <= 0) return true;
        else {
            Random random = new Random();
            List<Integer> gridPositions = new ArrayList<>();
            int row = random.nextInt(TABLE_LENGTH);
            int column = random.nextInt(TABLE_LENGTH);
            int direction = random.nextInt(3);
            String word = wordArray.pop();
            char[] wordCharArray = word.toUpperCase().toCharArray();
            while (!isFullGridPosition(gridPositions)) {
                if (isContainPosition(row, column, gridPositions)) {
                    row = random.nextInt(TABLE_LENGTH);
                    column = random.nextInt(TABLE_LENGTH);
                    continue;
                }
                if (isValid(table, wordCharArray, row, column, direction)) {
                    addWordCharArrayToTable(row, column, direction, table, wordCharArray);
                    if (addListWordToTable(wordArray, table)) return true;
                    else {
                        removeWordCharArray(row, column, direction, table, wordCharArray);
                        wordArray.push(word);
                    }
                }
            }
            return false;
        }
    }

    private static void removeWordCharArray(int row, int column, int direction, char[][] table, char[] wordCharArray) {
        if (direction == 0) {
            for (int i = 0; i < wordCharArray.length; i++) {
                table[row][column + i] = '\0';
            }
        } else if (direction == 1) {
            for (int i = 0; i < wordCharArray.length; i++) {
                table[row + i][column] = '\0';
            }
        } else {
            for (int i = 0; i < wordCharArray.length; i++) {
                table[row + i][column + i] = '\0';
            }
        }
    }

    private static void addWordCharArrayToTable(int row, int column, int direction, char[][] table, char[] wordCharArray) {
        if (direction == 0) {
            for (int i = 0; i < wordCharArray.length; i++) {
                table[row][column + i] = wordCharArray[i];
            }
        } else if (direction == 1) {
            for (int i = 0; i < wordCharArray.length; i++) {
                table[row + i][column] = wordCharArray[i];
            }
        } else {
            for (int i = 0; i < wordCharArray.length; i++) {
                table[row + i][column + i] = wordCharArray[i];
            }
        }
    }

    private static boolean isContainPosition(int row, int column, List<Integer> gridPositions) {
        int grdPosition = row * TABLE_LENGTH + column;
        if (gridPositions.contains(grdPosition)) {
            return true;
        }
        System.out.println(grdPosition);
        gridPositions.add(grdPosition);
        return false;
    }

    private static boolean isFullGridPosition(List<Integer> gridPositions) {
        return gridPositions.size() >= Math.pow(TABLE_LENGTH, 2);
    }


    private static boolean isValid(char[][] table, char[] wordCharArray, int row, int column, int direction) {

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

    private static void sortWordArrayByLength(Stack<String> arrayWord) {
        for (int i = 0; i < arrayWord.size() - 1; i++) {
            for (int j = i + 1; j < arrayWord.size(); j++) {
                if (arrayWord.get(i).length() < arrayWord.get(j).length()) {
                    arrayWord.add(i, arrayWord.get(j));
                    arrayWord.remove(j + 1);
                }
            }
        }
    }

    private static int getTotalNumberWord(Stack<String> arrayWord) {
        int totalNumberWord = 0;
        for (String word : arrayWord) {
            totalNumberWord += word.length();
        }
        return totalNumberWord;
    }

    private static void printAnswerKey() {
        WordPositionManagement management = WordPositionManagement.getInstance();
        for (Map.Entry<String, WordPosition> stringWordPositionEntry : management.getWordPositionMap().entrySet()) {
            System.out.println("word: " + ((Map.Entry) stringWordPositionEntry).getKey() + "- position: " + ((Map.Entry) stringWordPositionEntry).getValue());
        }
    }
}
