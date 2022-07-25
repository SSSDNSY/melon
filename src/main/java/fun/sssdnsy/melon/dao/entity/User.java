package fun.sssdnsy.melon.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Date;

/**
 * @author fun.pengzh
 * @class fun.sssdnsy.melon.dao.entity.User
 * @desc
 * @since 2022-07-25
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_user")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Email
    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true)
    private String password;

    private String salt;

    @Column(nullable = false)

    private String mobile;

    @Column(unique = true, nullable = false)
    private Integer status;

    private String createUserId;

    @Column(nullable = false)
    private Date createTime;

}
