import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CrossWord {
    private static final int TABLE_LENGTH = 10;

    public static void main(String[] args) {
        List<Integer> numbersRandomRow = new ArrayList<>();
        List<Integer> numbersRandomColumn = new ArrayList<>();
        char[][] table = new char[TABLE_LENGTH][TABLE_LENGTH];

        List<String> arrayWord = new ArrayList<>();

        Random random = new Random();
        arrayWord.add("CROSSWORD");
        arrayWord.add("exception");
        arrayWord.add("Reverse");
        arrayWord.add("STRING");
        arrayWord.add("LIGHT");
        arrayWord.add("FIGHTING");
        arrayWord.add("SAW");
        arrayWord.add("avasc");
        arrayWord.add("avwff");
        arrayWord.add("cjedcsew");
        arrayWord.add("qwevbc");
        arrayWord.add("sdksdjge");
//        arrayWord.add("vkldfjwx");
//        arrayWord.add("roevnxk");
//        arrayWord.add("mcxikxp");
//        arrayWord.add("xmnvhks");
//        arrayWord.add("wefwer");
//        arrayWord.add("vcvbfdfg");
//        arrayWord.add("vcbdhc");

        System.out.println("array before sort: ");
        System.out.println(arrayWord);

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
                    System.out.print((table[i][j] == '\0') ? "-|" : (table[i][j] + "|"));
                }
                System.out.println();
            }
        }
    }

    private static boolean addWordToTable(char[][] table, List<String> wordArray) {
        if (wordArray.size() == 0) return true;
        Random random = new Random();
        int run = 0;
        while (run < TABLE_LENGTH) {
            int direction = random.nextInt(3);
            System.out.println("Direction: " + direction);
            char[] wordCharArray = wordArray.get(0).toUpperCase().toCharArray();
            String wordDelete = wordArray.get(0);
            if (direction == 0) {
                int row = random.nextInt(TABLE_LENGTH);
                int column = random.nextInt(TABLE_LENGTH - wordCharArray.length + 1);
                if (isValid(table, wordCharArray, row, column, direction)) {
                    System.out.println("IsValid");
                    //them vao table
                    for (int i = 0; i < wordCharArray.length; i++)
                        table[row][column + i] = wordCharArray[i];
                    wordArray.remove(wordDelete);
                    run++;
                    if (addWordToTable(table, wordArray)) return true;
                    //xoa khoi table
                    for (int i = 0; i < wordCharArray.length; i++)
                        table[row][column + i] = '\0';
                    //add lai tu vua xoa de tro lai them lai
                    wordArray.add(0, wordDelete);
                    run--;
                }
            }
            if (direction == 1) {
                int row = random.nextInt(TABLE_LENGTH - wordCharArray.length + 1);
                int column = random.nextInt(TABLE_LENGTH);
                if (isValid(table, wordCharArray, row, column, direction)) {
                    //them vao table
                    for (int i = 0; i < wordCharArray.length; i++)
                        table[row + i][column] = wordCharArray[i];
                    wordArray.remove(wordDelete);
                    run++;
                    if (addWordToTable(table, wordArray)) return true;
                    //xoa khoi table
                    for (int i = 0; i < wordCharArray.length; i++)
                        table[row + i][column] = '\0';
                    //add lai tu vua xoa de tro lai them lai
                    wordArray.add(0, wordDelete);
                    //xoa khoi table
                    run--;
                }
            }
            if (direction == 2) {
                int row = random.nextInt(TABLE_LENGTH - wordCharArray.length + 1);
                int column = random.nextInt(TABLE_LENGTH - wordCharArray.length + 1);
                if (isValid(table, wordCharArray, row, column, direction)) {
                    //them vao table
                    for (int i = 0; i < wordCharArray.length; i++)
                        table[row + i][column + i] = wordCharArray[i];
                    wordArray.remove(wordDelete);
                    run++;
                    if (addWordToTable(table, wordArray)) return true;
                    //xoa khoi table
                    for (int i = 0; i < wordCharArray.length; i++)
                        table[row + i][column + i] = '\0';
                    //add lai tu vua xoa de tro lai them lai
                    wordArray.add(0, wordDelete);
                    run--;
                }
            }
        }
        return false;
    }

    private static boolean isValid(char[][] table, char[] wordCharArray, int row, int column, int direction) {
        if (direction == 0) {   //ngang
            for (int i = 0; i < wordCharArray.length; i++) {
                if (table[row][column + i] != '\0' && table[row][column + i] != wordCharArray[i]) {
                    return false;
                }
            }
        } else if (direction == 1) { //doc
            for (int i = 0; i < wordCharArray.length; i++) {
                if (table[row + i][column] != '\0' && table[row + i][column] != wordCharArray[i]) {
                    return false;
                }
            }
        } else if (direction == 2) { //cheo
            for (int i = 0; i < wordCharArray.length; i++) {
                if (table[row + i][column + i] != '\0' && table[row + i][column + i] != wordCharArray[i]) {
                    return false;
                }
            }
        }
        return true;
    }
}
