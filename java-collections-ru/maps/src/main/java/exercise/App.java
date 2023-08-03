package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
public class App {

    public static HashMap<String, Integer> getWordCount(String sentence) {

        HashMap<String, Integer> result = new HashMap<>();

        if (sentence.isEmpty()) {
            return result;
        }

        int count;

        for (String s : sentence.split(" ")) {

            count = 0;

            if (result.containsKey(s)) {
                count = result.get(s);
            }

            count++;
            result.put(s, count);

        }

        return result;

    }

    public static String toString(Map<String, Integer> map) {

        if (map.isEmpty()) {
            return "{}";
        }

        String result = "{";

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            result += "\n  ";
            result += entry.getKey();
            result += ": ";
            result += entry.getValue();
        }

        result += "\n}";

        return result;

    }

}
//END
