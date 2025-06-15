package exercise.controller.users;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import exercise.model.Post;
import exercise.Data;

@RestController
@RequestMapping("/api")
public class PostsController {
    @Setter
    private static List<Post> posts = Data.getPosts();

    @GetMapping("/users/{userId}/posts")
    public List<Post> show(@PathVariable Integer userId) {
        return posts.stream()
                .filter(p -> p.getUserId() == userId)
                .toList();
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/users/{userId}/posts")
    public Post create(@PathVariable Integer userId,
                       @RequestBody Post post) {

        post.setUserId(userId);
        posts.add(post);
        return post;
    }
}
