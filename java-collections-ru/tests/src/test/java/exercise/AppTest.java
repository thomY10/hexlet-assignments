package exercise;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

class AppTest {

    @Test
    void testTake() {
        // BEGIN
        List<Integer> res = new ArrayList<>();
        res.add(1);
        res.add(2);

        List<Integer> numbers1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
//        System.out.println(App.take(numbers1, 2)); // => [1, 2]
        assertThat(App.take(numbers1, 2)).isEqualTo(res);

        List<Integer> res2 = new ArrayList<>();
        res2.add(7);
        res2.add(3);
        res2.add(10);

        List<Integer> numbers2 = new ArrayList<>(Arrays.asList(7, 3, 10));
        assertThat(App.take(numbers2, 8)).isEqualTo(res2);
//        System.out.println(App.take(numbers2, 8)); // => [7, 3, 10]

//        List<Integer> numbers2 = new ArrayList<>();
//
//        numbers.add(7);
//        numbers.add(3);
//        numbers.add(10);
//
//        List<Integer> res2 = new ArrayList<>();
//        res2.add(7);
//        res2.add(3);
//        res2.add(10);
//
//        List<Integer> numbers3 = App.take(numbers, 8);
//        assertThat(numbers3.size()).isEqualTo(3);

        // END
    }
}
