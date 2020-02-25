import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CrossWord {
    public static void main(String[] args) {
        List<String> arrayWord = new ArrayList<>();
        char[][] table = new char[20][20];
        Random random = new Random();
        System.out.println(random.nextInt(10));
        arrayWord.add("HELLO");
        arrayWord.add("CROSSWORD");
        arrayWord.add("STRING");
        arrayWord.add("LIGHT");
        arrayWord.add("FIGHTING");
        arrayWord.add("SAW");

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
            StringBuffer wordBuffer = new StringBuffer();
            if (resultRevert == 1) {
                wordBuffer = wordBuffer.append(word);
                word = String.valueOf(wordBuffer.reverse());
            }
            char[] wordCharArray = new char[word.length()];
            for (int i = 0; i < word.length(); i++) {
                wordCharArray[i] = word.charAt(i);
            }
            int row = random.nextInt(20 - word.length());
            int column = random.nextInt(20 - word.length());
            addWordToTable(table, wordCharArray, row, column);
        }

        System.out.println("table");
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                System.out.print(table[i][j] + "||");
            }
            System.out.println();
        }

    }

    private static void addWordToTable(char[][] table, char[] wordCharArray, int row, int column) {
        Random random = new Random();
        int resultAdd = random.nextInt(3);

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
}
