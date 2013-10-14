package ru.kpfu.quantum.spring.domain;

import org.hibernate.validator.constraints.NotEmpty;

public class LoginBean {
	@NotEmpty(message = "Поле не может быть пустым")
	private String login;
	@NotEmpty(message = "Поле не может быть пустым")
	private String password;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
