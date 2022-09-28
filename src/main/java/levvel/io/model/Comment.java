package levvel.io.model;
import lombok.Data;
import lombok.Builder;

@Data
@Builder
public class Comment {


    String author;
    String text;

    
}


