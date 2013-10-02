package ru.kpfu.quantum.spring.domain;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author sala
 */
public class RegistrationBean {
    @NotEmpty(message = "Поле не может быть пустым")
    private String firstname;
    @NotEmpty(message = "Поле не может быть пустым")
    private String lastname;
    @NotEmpty(message = "Поле не может быть пустым")
    private String userLog;
    @NotEmpty(message = "Поле не может быть пустым")
    private String userEmail;
    @NotEmpty(message = "Поле не может быть пустым")
    private String passw;
    @NotEmpty(message = "Поле не может быть пустым")
    private String checkPassw;
    @NotEmpty(message = "Поле не может быть пустым")
    private String registrKey;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUserLog() {
        return userLog;
    }

    public void setUserLog(String userLog) {
        this.userLog = userLog;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPassw() {
        return passw;
    }

    public void setPassw(String passw) {
        this.passw = passw;
    }

    public String getCheckPassw() {
        return checkPassw;
    }

    public void setCheckPassw(String checkPassw) {
        this.checkPassw = checkPassw;
    }

    public String getRegistrKey() {
        return registrKey;
    }

    public void setRegistrKey(String registrKey) {
        this.registrKey = registrKey;
    }
}
