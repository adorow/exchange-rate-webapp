package cx.domain.validator;

import cx.domain.Country;
import cx.domain.RegistrationData;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.validation.MapBindingResult;

import static org.hamcrest.Matchers.*;

import java.util.Date;
import java.util.HashMap;

public class RegistrationDataValidatorTest {

    private RegistrationDataValidator validator = new RegistrationDataValidator();

    @Test
    public void ok() {
        RegistrationData registration = getGoodRegistrationData();
        MapBindingResult errors = new MapBindingResult(new HashMap(), "registration");
        validator.validate(registration, errors);
        assertThat(errors.hasErrors(), is(false));
    }

    @Test
    public void passwordsNotEqual() {
        RegistrationData registration = getGoodRegistrationData();
        registration.setPassword("111111");
        registration.setRepeatPassword("222222");
        MapBindingResult errors = new MapBindingResult(new HashMap(), "registration");
        validator.validate(registration, errors);
        assertThat(errors.hasErrors(), is(true));
        assertThat(errors.getFieldError("repeatPassword"), is(notNullValue()));
    }

    @Test
    public void invalidBirthdate() {
        RegistrationData registration = getGoodRegistrationData();
        registration.setBirthdate(new Date(/*2180=*/280, /*april=*/3, 12));
        MapBindingResult errors = new MapBindingResult(new HashMap(), "registration");
        validator.validate(registration, errors);
        assertThat(errors.hasErrors(), is(true));
        assertThat(errors.getFieldError("birthdate"), is(notNullValue()));
    }

    private RegistrationData getGoodRegistrationData() {
        RegistrationData registration = new RegistrationData();
        registration.setEmail("aaa@aaa.com");
        registration.setPassword("123123");
        registration.setRepeatPassword("123123");
        registration.setBirthdate(new Date(/*1980=*/80, /*april=*/3, 12));
        registration.setAddress("Maximilianstr. 1");
        registration.setPostalCode("12345");
        registration.setCity("Munich");
        registration.setCountry(Country.GERMANY.getCode());
        return registration;
    }

}
