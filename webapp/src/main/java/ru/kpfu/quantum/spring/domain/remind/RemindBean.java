package ru.kpfu.quantum.spring.domain.remind;

import javax.validation.constraints.Pattern;

/**
 * @author sala                                                                    `
 */
public class RemindBean {
    @Pattern(regexp = "[a-zA-Z0-9[!#$%&'()*+,/\\-_\\.\\\"]]+@[a-zA-Z0-9[!#$%&'()*+,/\\-_\\\"]]+\\.[a-zA-Z0-9[!#$%&'()*+,/\\-_\\\"\\.]]+",
             message = "Укажите корректный почтовый адрес")
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
