package exercise.controller;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import exercise.dto.posts.PostsPage;
import exercise.dto.posts.PostPage;
import exercise.model.Post;
import exercise.repository.PostRepository;

import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

public class PostsController {

    // BEGIN
    public static void show(Context ctx) {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var post = PostRepository.find(id);

        if (post.isPresent()) {
            var page = new PostPage(post.get());
            ctx.render("posts/show.jte", Collections.singletonMap("page", page));
        } else {
            ctx.status(404);
            ctx.result("Page not found");
        }

    }

    public static void index(Context ctx) {

        var pageNum = ctx.queryParamAsClass("page", Integer.class).getOrDefault(0);

        if (pageNum < 1) {
            pageNum = 1;
        }

        var posts = PostRepository.getEntities();

        var result = new ArrayList<Post>();

        int first =  (pageNum - 1) * 5;

        for (int i = first; i < Math.min(posts.size(), first + 5); i++) {
            result.add(posts.get(i));
        }

        var page = new PostsPage(result, pageNum);
        ctx.render("posts/index.jte", Collections.singletonMap("page", page));
    }

    // END
}
