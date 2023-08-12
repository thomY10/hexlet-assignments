package exercise;

import java.util.stream.Collectors;
import java.util.Arrays;

// BEGIN
public class App {

    public static String getForwardedVariables(String content) {

        String result = Arrays.stream(content.split("\n"))
                .filter(s -> s.startsWith("environment"))
                .map(s -> s.replaceAll("environment=", ""))
                .map(s -> s.replaceAll("\"", ""))
                .flatMap(s -> Arrays.stream(s.split(",")))
                .filter(s -> s.startsWith("X_FORWARDED_"))
                .map(s -> s.replaceAll("X_FORWARDED_", ""))
                .collect(Collectors.joining(","));
        
        return result;

    }

}
//END
