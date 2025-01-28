package exercise.dto.posts;

import java.util.List;
import exercise.model.Post;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


// BEGIN
@AllArgsConstructor
@Getter
public class PostsPage {
    private List<Post> posts;
    @Setter
    private int numberOfPage;

    public int nextPage() {
        return numberOfPage + 1;
    }

    public int previousPage() {
        return numberOfPage - 1;
    }
}
// END


