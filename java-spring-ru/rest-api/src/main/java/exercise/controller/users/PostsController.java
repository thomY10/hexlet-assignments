package exercise.controller.users;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

import exercise.model.Post;
import exercise.Data;

// BEGIN
@RestController
@RequestMapping("/api")
public class PostsController {

    static List<Post> posts = new ArrayList<>();
    @GetMapping("/users/{id}/posts")
    public static List<Post> getUserPosts(@PathVariable int id) {
        return posts.stream().filter(p -> p.getUserId() == id).toList();
    }

    @PostMapping("/users/{id}/posts")
    public static ResponseEntity<Post> addUserPosts(@PathVariable int id, @RequestBody Post post) {

//        Post post = new Post();
        post.setUserId(id);
//        post.setBody(body);
//        post.setTitle(title);
//        post.setSlug(slug);

        posts.add(post);

        return ResponseEntity.status(201).body(post);
    }

}
// END
