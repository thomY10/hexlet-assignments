package exercise.controller;

import java.util.Collections;
import exercise.dto.MainPage;
import exercise.dto.LoginPage;
import exercise.repository.UsersRepository;
import static exercise.util.Security.encrypt;

import exercise.util.NamedRoutes;
import exercise.util.Security;
import io.javalin.http.Context;

public class SessionsController {

    // BEGIN
    public static void index(Context ctx) {

        String name = ctx.sessionAttribute("loginedUser");

        var page = new MainPage(name);
        ctx.render("index.jte", Collections.singletonMap("page", page));
    }

    public static void build(Context ctx) {
        var page = new LoginPage("", "");
        ctx.render("build.jte", Collections.singletonMap("page", page));
    }

    public static void create(Context ctx) {
        var name = ctx.formParam("name");
        var password = ctx.formParam("password");
        var user = UsersRepository.findByName(name);

        // Тут должна быть проверка пароля
        if (!UsersRepository.existsByName(name) || !user.getPassword().equals(Security.encrypt(password))) {
            var page = new LoginPage(name, "Wrong username or password");
            ctx.render("build.jte", Collections.singletonMap("page", page));
            return;
        }

        ctx.sessionAttribute("loginedUser", name);
        ctx.redirect(NamedRoutes.rootPath());
    }

    public static void destroy(Context ctx) {
        ctx.sessionAttribute("loginedUser", null);
        ctx.redirect("/");
    }
    // END
}
