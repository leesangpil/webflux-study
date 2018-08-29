package my.study.webflux.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by leesangpil on 2018. 8. 30..
 */
@Document
@Getter
@Setter
public class Member {
    @Id
    private String id;
    private String name;
}
