package ru.kpfu.quantum.spring.entities;

import javax.persistence.*;

/**
 * Класс пользователя.
 * Отмечен аннотацией Entity, поэтому будет сохранен в СУБД
 *
 * @author sala
 */
@Entity
@Table(name = "Users")
public class User {
    /**
     * Идентификатор пользователя. Генерируется автоматически.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /**
     * Имя пользователя.
     */
    @Column(nullable = false)
    private String firstName;

    /**
     * Фамилия пользователя
     */
    @Column(nullable = false)
    private String lastName;

    /**
     * Электронная почта пользователя
     */
    @Column(nullable = false, unique = true)
    private String email;

    /**
     * Логин пользователя
     */
    @Column(nullable = false, unique = true)
    private String login;

    /**
     * Пароль пользователя
     */
    @Column(nullable = false)
    private String password;

    protected User() {

    }

    /**
     * Обычный конструктор
     * @param firstName Имя пользователя
     * @param lastName Фамилия пользователя
     * @param email Почта
     * @param login Логин
     * @param password Пароль
     */
    public User(String firstName, String lastName, String email, String login, String password) {
        if(firstName == null) throw new IllegalArgumentException("firstName cannot be null");
        if(lastName == null) throw new IllegalArgumentException("lastName cannot be null");
        if(email == null) throw new IllegalArgumentException("email cannot be null");
        if(login == null) throw new IllegalArgumentException("login cannot be null");
        if(password == null) throw new IllegalArgumentException("password cannot be null");
        //
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.login = login;
        this.password = password;
    }

    /**
     * @return Идентификатор пользователя
     */
    public long getId() {
        return id;
    }

    /**
     * @return Имя пользователя
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @return Фамилия пользователя
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @return Адрес электронной почты
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return Логин пользователя
     */
    public String getLogin() {
        return login;
    }

    /**
     * @return Пароль пользователя
     */
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
