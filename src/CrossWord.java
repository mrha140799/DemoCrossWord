import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CrossWord {
    public static void main(String[] args) {
        List<String> arrayWord = new ArrayList<>();
        char[][] table = new char[10][10];
        Random random = new Random();
        arrayWord.add("Reverse");
        arrayWord.add("CROSSWORD");
        arrayWord.add("STRING");
        arrayWord.add("LIGHT");
        arrayWord.add("FIGHTING");
        arrayWord.add("SAW");
//        arrayWord.add("avasc");
//        arrayWord.add("avwff");
//        arrayWord.add("cjedcsew");
//        arrayWord.add("qwevbc");
//        arrayWord.add("sdksdjge");
//        arrayWord.add("vkldfjwx");
//        arrayWord.add("roevnxk");
//        arrayWord.add("mcxikxp");
//        arrayWord.add("xmnvhks");
//        arrayWord.add("wefwer");
//        arrayWord.add("vcvbfdfg");
//        arrayWord.add("vcbdhc");

        System.out.println("array after sort: ");
        System.out.println(arrayWord);

        for (int i = 0; i < arrayWord.size() - 1; i++) {
            for (int j = i + 1; j < arrayWord.size(); j++) {
                if (arrayWord.get(i).length() < arrayWord.get(j).length()) {
                    arrayWord.add(i, arrayWord.get(j));
                    arrayWord.remove(j + 1);
                }
            }
        }
        System.out.println("array before sort: ");
        System.out.println(arrayWord);

        for (int wordIndex = 0; wordIndex < arrayWord.size(); wordIndex++) {
            String word = arrayWord.get(wordIndex);
            //revert
            int resultRevert = random.nextInt(2);
            // chieu add
            int direction = random.nextInt(3);
            StringBuffer wordBuffer = new StringBuffer();
            if (resultRevert == 1) {
                wordBuffer = wordBuffer.append(word);
                word = String.valueOf(wordBuffer.reverse());
            }
            word = word.toUpperCase();
            char[] wordCharArray = new char[word.length()];
            for (int i = 0; i < word.length(); i++) {
                wordCharArray[i] = word.charAt(i);
            }
            int row;
            int column;
            do {
                row = random.nextInt(10 - word.length());
                column = random.nextInt(10 - word.length());
            } while (!isValid(table, wordCharArray, row, column, direction));
            addWordToTable(table, wordCharArray, row, column, direction);
        }

        System.out.println("table");
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print((table[i][j] == '\0') ? "-|" : (table[i][j] + "|"));
            }
            System.out.println();
        }

    }

    private static void addWordToTable(char[][] table, char[] wordCharArray, int row, int column, int direction) {
        if (direction == 0) {
            for (int i = 0; i < wordCharArray.length; i++) {
                table[row + i][column] = wordCharArray[i];
            }
        } else if (direction == 1) {
            for (int i = 0; i < wordCharArray.length; i++) {
                table[row][column + i] = wordCharArray[i];
            }
        } else {
            for (int i = 0; i < wordCharArray.length; i++) {
                table[row + i][column + i] = wordCharArray[i];
            }
        }
    }

    private static boolean isValid(char[][] table, char[] wordCharArray, int row, int column, int direction) {
        if (direction == 0) {
            for (int i = 0; i < wordCharArray.length; i++) {
                if (table[row + i][column] != '\0' && table[row + i][column] != wordCharArray[i]) {
                    return false;
                }
            }
        } else if (direction == 1) {
            for (int i = 0; i < wordCharArray.length; i++) {
                if (table[row][column+i] != '\0' && table[row][column + i] != wordCharArray[i]) {
                    return false;
                }
            }
        } else {
            for (int i = 0; i < wordCharArray.length; i++) {
                if (table[row + i][column+i] != '\0' && table[row + i][column + i] != wordCharArray[i]) {
                    return false;
                }
            }
        }
        return true;
    }
}
