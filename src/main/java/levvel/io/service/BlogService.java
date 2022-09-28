package levvel.io.service;
import levvel.io.model.*;

public interface BlogService {

    void addBlog(Blog blog);

    Blog getBlog(String id);

    // create interface methods for add comments and getting comments from blog

    void addComment(Comment comment, String id);

    Comment[] getComments(String id);

  
}
