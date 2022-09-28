package levvel.io.model;
import lombok.Data;
import lombok.Builder;
import lombok.NonNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import java.util.ArrayList;

import java.time.LocalDateTime;

@Data
//@Builder // lombok annotation so i don't have to declare getters setters and constructors
public class Blog {

    @Id
    String id;

    @CreatedDate
    // LocalDateTime createdDate = LocalDateTime.now();
    LocalDateTime createdDate;

    @LastModifiedDate
    LocalDateTime lastModifiedDate;

    //@NonNull // prevents them from having a null value for validation purposes
    String author;

   // @NonNull
    String title;

   // @NonNull
    String text;


     public ArrayList<Comment> comments = new ArrayList<Comment>();
    //public ArrayList<Comment> comments;


    
    @Override // for testing purposes in the console
    public String toString() {
      return String.format(
          "Blog[author=%s, title='%s', text='%s', id='%s', commentArr = %s]",
          author, title, text, id, comments);
    }
}
