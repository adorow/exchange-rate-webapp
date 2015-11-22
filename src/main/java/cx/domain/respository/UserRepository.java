package cx.domain.respository;


import cx.domain.entity.UserEntity;
import org.springframework.data.repository.Repository;

public interface UserRepository extends Repository<UserEntity, Long> {

    UserEntity save(UserEntity user);

    UserEntity findOneByEmail(String email);
}
