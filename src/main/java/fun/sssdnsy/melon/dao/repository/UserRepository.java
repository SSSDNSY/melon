package fun.sssdnsy.melon.dao.repository;

import fun.sssdnsy.melon.dao.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author fun.pengzh
 * @class fun.sssdnsy.melon.dao.repository.UserRepository
 * @desc
 * @since 2022-07-25
 */
public interface UserRepository extends JpaRepository<User, Long> {

}
