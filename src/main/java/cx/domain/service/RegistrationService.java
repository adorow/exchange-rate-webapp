package cx.domain.service;

import cx.domain.RegistrationData;
import cx.domain.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    @Autowired
    private UserService userService;

    public UserEntity register(RegistrationData registrationData) {
        UserEntity user = new UserEntity();
        user.setEmail(registrationData.getEmail());
        user.setBirthdate(registrationData.getBirthdate());
        user.setAddress(registrationData.getAddress());
        user.setPostalCode(registrationData.getPostalCode());
        user.setCity(registrationData.getCity());
        user.setCountry(registrationData.getCountry());

        return userService.create(user, registrationData.getPassword());
    }

}
