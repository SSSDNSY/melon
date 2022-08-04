package fun.sssdnsy.melon.pojo.condition;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

/**
 * @author pengzh
 * @desc
 * @class UserCondtion
 * @since 2022-08-04
 */
@Getter
@Setter
public class UserCondtion extends PageRequest {

    private String id;
    private String name;

    public UserCondtion(int page, int size, Sort sort, String id, String name) {
        super(page, size, sort);
        this.id = id;
        this.name = name;
    }

}
