package exercise.controller;

import exercise.model.Comment;
import exercise.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.HttpStatus;
import java.util.List;

import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.exception.ResourceNotFoundException;

// BEGIN
@RestController
@RequestMapping("/posts")
public class PostsController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping(path = "")
    public List<Post> showAll() {
        return postRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Post showById(@PathVariable long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not found"));

        return post;
    }

    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public Post create(@RequestBody Post post) {

        postRepository.save(post);
        return post;
    }

    @PutMapping(path = "/{id}")
    public Post create (@PathVariable long id, @RequestBody Post postData) {

        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found!"));

        post.setBody(postData.getBody());
        post.setTitle(postData.getTitle());

        postRepository.save(post);
        return post;
    }

    @DeleteMapping(path = "/{id}")
    public void remove(@PathVariable long id) {
        commentRepository.deleteByPostId(id);
        postRepository.deleteById(id);
    }

}
// END
