package exercise;

import io.javalin.Javalin;
import java.util.List;
import io.javalin.http.NotFoundResponse;
import exercise.model.User;
import exercise.dto.users.UserPage;
import exercise.dto.users.UsersPage;
import java.util.Collections;

public final class App {

    // Каждый пользователь представлен объектом класса User
    private static final List<User> USERS = Data.getUsers();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
        });

        // BEGIN
        app.get("/users", ctx -> {
            ctx.json(USERS);
            var page = new UsersPage(USERS);
            ctx.render("users/index.jte", Collections.singletonMap("page", page));
        });
        app.get("/users/{id}", ctx -> {

            Long id = ctx.pathParamAsClass("id", Long.class).get();

            boolean finded = false;

            for (User user : USERS) {

                if (id.equals(user.getId())) {
                    finded = true;
                    var page = new UserPage(user);
                    ctx.render("users/show.jte", Collections.singletonMap("page", page));
                    break;
                }

            }

            if (!finded) {
                throw new NotFoundResponse("User not found");
            }

        });
        // END

        app.get("/", ctx -> {
            ctx.render("index.jte");
        });

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
