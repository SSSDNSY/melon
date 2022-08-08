package fun.sssdnsy.melon.dao.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
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
@TableName("t_user")
public class UserEntity {

    @Id
    @GeneratedValue
    @TableId
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true)
    private String password;

    private String salt;

    @Email
    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String mobile;

    @Column(unique = true, nullable = false)
    private Integer status;

    private String createUserId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8:00")
    @Column(nullable = false)
    private Date createTime;

}
