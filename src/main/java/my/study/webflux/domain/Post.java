package my.study.webflux.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by leesangpil on 2018. 8. 31..
 */
@Document(collection = "post")
@Data
public class Post {

    @Id
    private String id;

    @Size(min = 1, max = 20)
    private String title;

    @Size(min = 1, max = 300)
    private String content;

    @CreatedDate
    private Date createDate;

    @LastModifiedDate
    private Date updateDate;

    @NotEmpty
    private String creator;

    private Boolean open = true;       // 공개 / 비공개

    private Boolean delete = false;     // 삭제 여부

    public static Post createPost(String title, String content, String creator) {
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setCreator(creator);
        post.setCreateDate(new Date());
        post.setUpdateDate(new Date());
        return post;
    }
}
