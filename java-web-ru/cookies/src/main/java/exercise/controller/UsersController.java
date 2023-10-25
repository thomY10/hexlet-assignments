package exercise.controller;

import exercise.dto.users.UserPage;
import io.javalin.validation.ValidationException;
import org.apache.commons.lang3.StringUtils;
import exercise.util.Security;
import exercise.model.User;
import exercise.util.NamedRoutes;
import java.util.Collections;
import java.util.Optional;

import exercise.repository.UserRepository;
import io.javalin.http.NotFoundResponse;
import io.javalin.http.Context;


public class UsersController {

    public static void build(Context ctx) throws Exception {
        ctx.render("users/build.jte");
    }

    // BEGIN
    public static void create(Context ctx) {

        String token = Security.generateToken();


        var firstName = ctx.formParamAsClass("firstName", String.class).get();
        var lastName = ctx.formParamAsClass("lastName", String.class).get();
        var email = ctx.formParamAsClass("email", String.class).get();
        var password = ctx.formParamAsClass("password", String.class).get();

        var user = new User(firstName, lastName, email, password, token);

        UserRepository.save(user);

        ctx.cookie("token", token);
        ctx.redirect(NamedRoutes.userPath(user.getId()));

//        try {
//            var name = ctx.formParamAsClass("name", String.class)
//                    .check(value -> value.length() >= 2, "Название не должно быть короче двух символов")
//                    .get();
//
//            var body = ctx.formParamAsClass("body", String.class)
//                    .check(value -> value.length() >= 10, "Пост должен быть не короче 10 символов")
//                    .get();
//
//            var post = new Post(name, body);
//            PostRepository.save(post);
//            ctx.redirect(NamedRoutes.postsPath());
//
//        } catch (ValidationException e) {
//            var name = ctx.formParam("name");
//            var body = ctx.formParam("body");
//            var page = new BuildPostPage(name, body, e.getErrors());
//            ctx.render("posts/build.jte", Collections.singletonMap("page", page)).status(422);
//        }
    }
    public static void show(Context ctx) {
        var id = ctx.pathParamAsClass("id", Long.class).get();

        Optional<User> optionalUser = UserRepository.find(id);

        if (optionalUser.isEmpty()) {
            ctx.redirect(NamedRoutes.buildUserPath());
            return;
        }

        User user = optionalUser.get();
        String token = ctx.cookie("token");
        if (user.getToken().equals(token)) {
            var userPage = new UserPage(user);
            ctx.render("users/show.jte", Collections.singletonMap("page", userPage));
        } else {
            ctx.redirect(NamedRoutes.buildUserPath());
        }

    }
    // END
}
