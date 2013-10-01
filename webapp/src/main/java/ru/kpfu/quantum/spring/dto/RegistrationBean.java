package ru.kpfu.quantum.spring.dto;

/**
 * @author sala
 */
public class RegistrationBean {
    private String firstname;
    private String lastname;
    private String userLog;
    private String userEmail;
    private String passw;
    private String checkPassw;
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
