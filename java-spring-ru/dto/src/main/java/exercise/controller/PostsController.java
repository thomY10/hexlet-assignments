package exercise.controller;

import exercise.model.Comment;
import exercise.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.exception.ResourceNotFoundException;
import exercise.dto.PostDTO;
import exercise.dto.CommentDTO;

// BEGIN
@RestController
@RequestMapping("/posts")
public class PostsController {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;

    @GetMapping("")
    public List<PostDTO> index() {

        List<PostDTO> result = new ArrayList<>();

        for (Post post : postRepository.findAll()) {
            result.add(getPostDTOByPost(post));
        }

        return result;
    }

    @GetMapping("/{id}")
    public PostDTO show(@PathVariable long id) {

        var post =  postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not found"));

        return getPostDTOByPost(post);

    }

    private PostDTO getPostDTOByPost(Post post) {

        PostDTO result = new PostDTO();

        result.setId(post.getId());
        result.setTitle(post.getTitle());
        result.setBody(post.getBody());

        List<CommentDTO> commentsDTO = new ArrayList<>();

        for (Comment comment : commentRepository.findByPostId(post.getId())) {

            CommentDTO commentDTO = new CommentDTO();
            commentDTO.setId(comment.getId());
            commentDTO.setBody(comment.getBody());

            commentsDTO.add(commentDTO);

        }

        result.setComments(commentsDTO);

        return result;

    }
}
// END
