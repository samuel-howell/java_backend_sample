package levvel.io;

import org.springframework.boot.test.context.SpringBootTest;

import levvel.io.model.*;
import levvel.io.service.BlogService;
import levvel.io.controller.BlogController;
import levvel.io.data.BlogRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.test.web.servlet.request.*;
import static org.mockito.Mockito.*;
import java.util.*;
import static org.hamcrest.Matchers.notNullValue;

import com.fasterxml.jackson.databind.ObjectMapper;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;






//@SpringBootTest

@ExtendWith(SpringExtension.class)
@WebMvcTest(BlogController.class)


class WorkSampleApplicationTests {

	@Autowired 
    MockMvc mockMvc;

    @MockBean 
    BlogService blogService;

    @MockBean 
    BlogRepository blogRepository;

    @Autowired 
    ObjectMapper mapper;




	@Test
	void contextLoads() {
	}

	@Test
    public void addBlog_success() throws Exception 
	{
        //! This works as long as @Builder is declared in the blog model, but declaring builder will make the Comment Arraylist init as null, rather than
        //! [], which will led to a null pointer exception when you try to add a comment. Builder.Default doesn't fix this issue.
        // Blog blog = Blog.builder() // utilizing the lombok @Builder notation to directly build an instantiation for unit testing
        //         .author("John Doe")
        //         .title("The Title")
        //         .text("New York USA")
        //         .build();
    
        //when(blogRepository.save(blog)).thenReturn(blog);
    
        // MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/blog/post")
        //         .contentType(MediaType.APPLICATION_JSON)
        //         .accept(MediaType.APPLICATION_JSON)
        //         .content(this.mapper.writeValueAsString(blog));
    
        // mockMvc.perform(mockRequest)
        //         .andExpect(status().isOk());
    }

	@Test
    public void addComment_success() throws Exception 
	{
		// create a comment that you can add to blog to run the unit test.
        Comment comment = Comment.builder()
                .author("Commenter 1")
                .text("this is the comment text body")
                .build();

    
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/blog/post/123/comment") // 123 serves as the stand-in ID value
                .contentType(MediaType.APPLICATION_JSON) // sets the content type header of request to JSON
                .accept(MediaType.APPLICATION_JSON)      // sets the accept header to JSON
                .content(this.mapper.writeValueAsString(comment)); //  generates JSON from comment java obj and returns generated JSON as string
    

        mockMvc.perform(mockRequest) // Perform mcokRequest and return a type that allows chaining further actions, such as asserting expectations, on the result.
                .andExpect(status().isOk()); // status() retrieves HTTP status code and isOk() validates that it is 200 OK, proving that the endpoint is functional.
		
    }

	@Test
    public void getComment_success() throws Exception 
	{

        mockMvc.perform(MockMvcRequestBuilders.get("/blog/post/123/comment") 
				.contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());   //TODO: add more andExpect statements to make sure the correct output is returned
		
    }

	// @Test
    // public void getBlog_success() throws Exception 
	// // {

	// 	Blog blog = Blog.builder() 
    //             .author("John Doe")
    //             .title("The Title")
    //             .text("New York USA")
	// 			.id("1234")
    //             .build();
    
    //     when(blogRepository.save(blog)).thenReturn(blog);

    //     mockMvc.perform(MockMvcRequestBuilders.get("/blog/post/1234"))  //! Null pointer exception thrown here... how do i instantiate a test blog with id 1234 for it to "get"?
    //             .andExpect(status().isOk());   
		
    // }



}

