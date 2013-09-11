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
     * Возвращает пользователя по имени.
     * Метод реализуется автоматически.
     *
     * @param name Имя пользователя
     * @return Пользователь
     */
    public User findByName(String name);


    /**
     * Возвращает всех пользователей, имя которых начинается с буквы S
     * Реализуется, так как отмечен аннотацией @Query
     *
     * @return Список пользователей
     */
    @Query("select u from User u where u.name like 's%'")
    public List<User> getAllStartsWithS();
}
