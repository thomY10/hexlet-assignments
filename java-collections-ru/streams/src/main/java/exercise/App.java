package exercise;

import java.util.List;
import java.util.Arrays;

// BEGIN
public class App {

    public static long getCountOfFreeEmails(List<String> emails) {
        return emails.stream().filter(email -> isFreeEmail(email)).count();
    }

    public static boolean isFreeEmail(String email) {

        return email.endsWith("gmail.com") || email.endsWith("yandex.ru") || email.endsWith("hotmail.com");

    }
}
// END
