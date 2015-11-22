package cx.domain.validator;


import com.google.common.base.Objects;
import cx.domain.RegistrationData;
import cx.util.DateUtil;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Component
public class RegistrationDataValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return RegistrationData.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        RegistrationData registration = (RegistrationData) target;
        checkPassword(registration, errors);
        checkBirthdate(registration, errors);
    }

    private void checkPassword(RegistrationData registration, Errors errors) {
        if (!Objects.equal(registration.getPassword(), registration.getRepeatPassword())) {
            errors.rejectValue("repeatPassword", "repeatpassword.notequal", "Password and repeated password must be the same");
        }
    }

    private void checkBirthdate(RegistrationData registration, Errors errors) {
        if (registration.getBirthdate() == null) {
            return;
        }

        LocalDate birthdate = DateUtil.oldJavaToLocalDate(registration.getBirthdate());
        // age > ~8yo and age < ~120yo
        if (birthdate.isAfter(today().minus(8 * 365, ChronoUnit.DAYS)) || birthdate.isBefore(today().minus(120 * 365, ChronoUnit.DAYS))) {
            errors.rejectValue("birthdate", "birthdade.invalid", "Must be a valid birthdate");
        }
    }

    private static LocalDate today() {
        return LocalDate.now();
    }
}
