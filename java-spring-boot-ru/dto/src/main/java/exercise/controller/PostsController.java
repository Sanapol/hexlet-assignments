package exercise.controller;

import exercise.model.Comment;
import exercise.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
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
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @GetMapping(path = "")
    public List<PostDTO> index() {
        return postRepository.findAll().stream().map(this::toDTO).toList();
    }

    @GetMapping(path = "/{id}")
    public PostDTO show(@PathVariable long id) {
        return postRepository.findById(id).map(this::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not found"));
    }

    private PostDTO toDTO(Post post) {
        PostDTO postDTO = new PostDTO();
        postDTO.setId(post.getId());
        postDTO.setBody(post.getBody());
        postDTO.setTitle(post.getTitle());
        List<CommentDTO> commentDTO = commentRepository.findByPostId(post.getId()).stream().map(this::toDTO).toList();
        postDTO.setComments(commentDTO);
        return postDTO;
    }

    private CommentDTO toDTO(Comment comment) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(comment.getId());
        commentDTO.setBody(comment.getBody());
        return commentDTO;
    }
}
// END
