import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CrossWord {
    public static void main(String[] args) {
        List<String> arrayWord = new ArrayList<>();
        char[][] table = new char[9][9];
        Random random = new Random();
        arrayWord.add("Reverse");
        arrayWord.add("ROSSWORD");
        arrayWord.add("STRING");
        arrayWord.add("LIGHT");
        arrayWord.add("FIGHTING");
        arrayWord.add("SAW");
        arrayWord.add("avasc");
        arrayWord.add("avwff");
        arrayWord.add("cjedcsew");
        arrayWord.add("qwevbc");
        arrayWord.add("sdksdjge");
        arrayWord.add("vkldfjwx");
        arrayWord.add("roevnxk");
        arrayWord.add("mcxikxp");
        arrayWord.add("xmnvhks");
        arrayWord.add("wefwer");
        arrayWord.add("vcvbfdfg");
        arrayWord.add("vcbdhc");

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
            int resultAdd = random.nextInt(3);
            StringBuffer wordBuffer = new StringBuffer();
            if (resultRevert == 1) {
                wordBuffer = wordBuffer.append(word);
                word = String.valueOf(wordBuffer.reverse());
            }
            char[] wordCharArray = new char[word.length()];
            for (int i = 0; i < word.length(); i++) {
                wordCharArray[i] = word.charAt(i);
            }
            int row;
            int column;
            do {
                row = random.nextInt(9 - word.length());
                column = random.nextInt(9 - word.length());
            } while (isValid(table, wordCharArray, row, column, resultAdd));
            addWordToTable(table, wordCharArray, row, column, resultAdd);
        }

        System.out.println("table");
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(table[i][j] + " ");
            }
            System.out.println();
        }

    }

    private static void addWordToTable(char[][] table, char[] wordCharArray, int row, int column, int resultAdd) {
        if (resultAdd == 0) {
            for (int i = 0; i < wordCharArray.length; i++) {
                table[row + i][column] = wordCharArray[i];
            }
        } else if (resultAdd == 1) {
            for (int i = 0; i < wordCharArray.length; i++) {
                table[row][column + i] = wordCharArray[i];
            }
        } else {
            for (int i = 0; i < wordCharArray.length; i++) {
                table[row + i][column + i] = wordCharArray[i];
            }
        }
    }

    private static boolean isValid(char[][] table, char[] wordCharArray, int row, int column, int resultAdd) {
        if (resultAdd == 0) {
            for (int i = 0; i < wordCharArray.length; i++) {
                if (table[row + i][column] != wordCharArray[i] || table[row + i][column] != '\0') {
                    return false;
                }
            }
        } else if (resultAdd == 1) {
            for (int i = 0; i < wordCharArray.length; i++) {
                if (table[row][column + i] != wordCharArray[i] || table[row + i][column] != '\0') {
                    return false;
                }
            }
        } else {
            for (int i = 0; i < wordCharArray.length; i++) {
                if (table[row + i][column + i] != wordCharArray[i] || table[row + i][column] != '\0') {
                    return false;
                }
            }
        }
        return false;
    }
}
