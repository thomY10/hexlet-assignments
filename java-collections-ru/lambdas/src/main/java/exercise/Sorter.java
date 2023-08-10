package exercise;

import java.util.Comparator;
import java.util.Map;
import java.util.List;
import java.time.LocalDate;
import java.util.stream.Collectors;

// BEGIN
public class Sorter {

    public static List<String> takeOldestMans(List<Map<String, String>> users) {

        List<Map<String, String>> mans = users.stream().filter(u -> u.get("gender").equals("male")).collect(Collectors.toList());

        Comparator<Map<String, String>> comparator = Comparator.comparing(u -> LocalDate.parse(u.get("birthday")));
        mans.sort(comparator);

        return mans.stream().map(u -> u.get("name")).collect(Collectors.toList());
    }
}
// END
