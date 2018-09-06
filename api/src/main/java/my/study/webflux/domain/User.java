package my.study.webflux.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Created by leesangpil on 2018. 8. 31..
 */
@Document(collection = "user")
@Getter
@Setter
public class User {

    @Id
    private String id;

    @Indexed(unique = true)
    private String nickname;

    @CreatedDate
    private Date createDate;

    @LastModifiedDate
    private Date updatedDate;

    public static User create(String nickname) {
        User user = new User();
        Date createDate = new Date();
        user.setNickname(nickname);
        user.setCreateDate(createDate);
        user.setUpdatedDate(createDate);
        return user;
    }
}
