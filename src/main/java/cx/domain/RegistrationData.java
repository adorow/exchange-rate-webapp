package cx.domain;

import org.hibernate.validator.constraints.Email;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class RegistrationData {

    @NotNull(message = "E-mail is missing")
    @Size(min = 1, message = "E-mail is missing")
    @Email(message = "E-mail is not well-formed")
    private String email;

    @NotNull(message = "Password is missing")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;

    @NotNull(message = "Repeat the password")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String repeatPassword;

    @NotNull(message = "Birthdate is missing")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthdate;

    @NotNull(message = "Address is missing")
    @Size(min = 1, message = "Address is missing")
    private String address;

    @NotNull(message = "Postal code is missing")
    @Size(min = 1, message = "Postal code is missing")
    private String postalCode;

    @NotNull(message = "City is missing")
    @Size(min = 1, message = "City is missing")
    private String city;

    @NotNull(message = "Country is missing")
    private String country;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
