package ru.kpfu.quantum.spring.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.kpfu.quantum.spring.entities.PasswordRemind;
import ru.kpfu.quantum.spring.entities.User;


/**
 * @author sala
 */
public interface PasswordRemindRepository extends CrudRepository<PasswordRemind, Long> {
    @Query("select pr.user from PasswordRemind pr where (pr.used = false) and (pr.code = ?1)")
    public User findUserByCode(String code);
}
