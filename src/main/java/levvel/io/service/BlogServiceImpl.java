package levvel.io.service;
import levvel.io.data.BlogRepository;
import levvel.io.model.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/*
 * 
 * Normally, controller classes talk with the methods in repository classes directly,
 *  there is nothing wrong. But, if we need to some business logic, we should not write
 *  this code block in the controller. Because of this, the service layer is needed.
 * Hence the implementation
 */

@Service
@AllArgsConstructor
public class BlogServiceImpl implements BlogService {

    private BlogRepository blogRepository;

    @Override
    public void addBlog(Blog blog) {
        blogRepository.save(blog);
    }

    @Override
    public Blog getBlog(String id) {
        return blogRepository.findById(id).orElseGet(null);
    }

    @Override
    public void addComment(Comment comment, String id){
        Blog blog = getBlog(id);
        blog.comments.add(comment); // add comment to the arraylist field in the blog object
        blogRepository.save(blog); // after we've added the new comment to the arraylist, we have to save the data to "lock it in"
    }

    @Override
    public Comment[] getComments(String id){
        Blog blog = getBlog(id);
        return blog.comments.toArray(new Comment[0]);  // take the blog's comments, cast them to an array of the type Comment[], and then return them
    }




}
