package exercise;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

// BEGIN
public class App {

    public static boolean scrabble(String symbols, String word) {

        boolean result = true;

        List<Character> chars = new ArrayList<>();
        for (char c : symbols.toCharArray()) {
            chars.add(c);
        }


        for (char c : word.toLowerCase().toCharArray()) {

            int indexOfChar = chars.indexOf(c);

            if(indexOfChar < 0) {
                result = false;
                break;
            } else {
                chars.remove(indexOfChar);
            }

        }

        return result;


    }

}
//END
