package exercise;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Map.Entry;

// BEGIN
public class App {

    public static <K, V> List<Map<K, V>> findWhere (List<Map<K, V>> list, Map<K, V> where) {

        List<Map<K, V>> result = new ArrayList<>();
        boolean accept;

        for (Map<K, V> kvMap : list) {

            accept = true;

            for (K k : where.keySet()) {

                if (!kvMap.get(k).equals(where.get(k))) {
                    accept = false;
                    break;
                }

            }

            if (accept) {
                result.add(kvMap);
            }

        }

        return result;

    }

}
//END
