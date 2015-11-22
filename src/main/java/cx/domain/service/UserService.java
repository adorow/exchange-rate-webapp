package cx.domain.service;

import com.google.common.base.Objects;
import cx.domain.entity.UserEntity;
import cx.domain.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserEntity create(UserEntity user, String password) {
        user.setPasswordHash(passwordEncoder.encode(password));
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findOneByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    public UserEntity findAndAuthenticateUser(String email, String givenPassword) {
        // FIXME: for test only:
        if (Objects.equal(email, "test") && Objects.equal(givenPassword, "a")) {
            UserEntity fakeUser = new UserEntity();
            fakeUser.setEmail(email);
            fakeUser.setPasswordHash(passwordEncoder.encode(givenPassword));
            return fakeUser;
        }

        UserEntity user = userRepository.findOneByEmail(email);
        if (user == null) {
            return null;
        }

        if (passwordEncoder.matches(givenPassword, user.getPasswordHash())) {
            return user;
        }

        return null;
    }
}
