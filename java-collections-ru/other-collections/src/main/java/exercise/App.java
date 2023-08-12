package exercise;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

// BEGIN
public class App {

    public static LinkedHashMap<String, String> genDiff(Map<String, Object> first, Map<String, Object> second) {

        LinkedHashMap<String, String> result = new LinkedHashMap<>();

        Set<String> keysFirst = first.keySet();
        Set<String> keysSecond = second.keySet();

        Set<String> added = new TreeSet<>(keysSecond);
        added.removeAll(keysFirst);

        Set<String> deleted = new TreeSet<>(keysFirst);
        deleted.removeAll(keysSecond);

        Set<String> intersect = new TreeSet<>(keysFirst);
        intersect.addAll(keysSecond);
        intersect.removeAll(added);
        intersect.removeAll(deleted);

        Set<String> changed = new TreeSet<>();

        for (String e : intersect) {
            if (!first.get(e).equals(second.get(e))) {
                changed.add(e);
            }
        }

        Set<String> unchanged = new TreeSet<>(intersect);
        unchanged.removeAll(changed);

        Set<String> all = new TreeSet<>();
        all.addAll(added);
        all.addAll(deleted);
        all.addAll(unchanged);
        all.addAll(changed);

        for (String e : all) {
            if (added.contains(e)) {
                result.put(e, "added");
            } else if (deleted.contains(e)) {
                result.put(e, "deleted") ;
            } else if (changed.contains(e)) {
                result.put(e, "changed");
            } else {
                result.put(e, "unchanged");
            }
        }

        return result;

    }

}
//END
