package levvel.io.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import levvel.io.model.Blog;
import levvel.io.model.Comment;
import levvel.io.service.BlogService;
import lombok.AllArgsConstructor;


@RestController //@RestController is a specialized version of the controller. It includes the @Controller and @ResponseBody annotations, and as a result, simplifies the controller implementation:
@AllArgsConstructor
@RequestMapping("/blog") // The @RequestMapping("/blog") prepares the REST endpoint root context for endpoints added to this class and map them as "/blog"
public class BlogController {

    private BlogService blogService;

    @PostMapping("/post")
    public ResponseEntity<Blog> addBlog(@RequestBody Blog blog) { // @RequestBody annotation enables automatic deserialization of the inbound HttpRequest body onto a Java object
        blogService.addBlog(blog);
        System.out.println("Added blog " + blog.toString()); // for console testing purposes
        return ResponseEntity.ok().body(blog); // ResponseEntity represents the whole HTTP response: status code, headers, and body
    }

    @GetMapping("/post/{id}") // the @GetMapping("/post/{id}") registers the context "/post/{id}" to receive HTTP GET requests 
    public ResponseEntity<Blog> getBlog(@PathVariable String id) { // val of id from url is mapped to id param
        Blog blog = blogService.getBlog(id);
        System.out.println("Got a particular post " + blog.toString());
        return ResponseEntity.ok().body(blog);
    }

    //  post comment to blog with the passed id
    @PostMapping("/post/{id}/comment") //  @PostMapping handles the posts requests matched with the uri
    public ResponseEntity<Comment> addComment(@RequestBody Comment comment, @PathVariable String id) { 
        blogService.addComment(comment, id);
        System.out.println("posted comment. id is " + id.toString() + " and comment is " + comment.toString()); // for console testing
        return ResponseEntity.ok().body(comment);
    }

    

    // get all the comments that are associated with the blog post with the passed id
    @GetMapping("/post/{id}/comment")
    public ResponseEntity<Comment[]> getComment(@PathVariable String id) {
        Comment[] comments = blogService.getComments(id);
        return ResponseEntity.ok().body(comments);
    }


}
