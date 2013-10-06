package ru.kpfu.quantum.spring.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.quantum.spring.entities.User;

import java.util.List;

/**
 * Класс для доступа к пользователям.
 *
 * @author sala
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    /**
     * Возвращает пользователя по логину и паролю
     * @param login Логин пользователя
     * @param password Пароль пользователя
     * @return Пользователя или null, если пользователя с таким логином и паролем не существует
     */
    public User findByLoginAndPassword(String login, String password);

    /**
     * Возвращает пользователя по логину или электронной почте
     * @param login Логин пользователя
     * @return Пользователя или null, если пользователя с таким логином
     */
    public User findByLogin(String login);

    public User findByEmail(String email);
}
