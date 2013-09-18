package ru.kpfu.quantum.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.quantum.spring.entities.Invite;

/**
 * Репозиторий для работы с инвайтами
 *
 * @author sala
 */
@Repository
public interface InviteRepository extends CrudRepository<Invite, Long> {
    /**
     * Ищет инвайт по коду и признаку использованности
     * @param code Код инвайта
     * @param used Признак использованности
     * @return Инвайт или null, если ни такого инвайта не найдено.
     */
    public Invite findByCodeAndUsed(String code, boolean used);
}
