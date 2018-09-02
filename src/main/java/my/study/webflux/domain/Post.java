package my.study.webflux.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Created by leesangpil on 2018. 8. 31..
 */
@Document(collection = "post")
@Getter
@Setter
public class Post {

    @Id
    private String id;

    private String title;

    private String content;

    @CreatedDate
    private Date createDate;

    @LastModifiedDate
    private Date updatedDate;

    private String creatorId;

    private Boolean open;       // 공개 / 비공개

    private Boolean delete;     // 삭제 여부
}
