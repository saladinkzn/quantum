package ru.kpfu.quantum.spring.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.quantum.spring.entities.PendingMail;

/**
 * @author sala
 */
@Repository
public interface PendingMailRepository extends PagingAndSortingRepository<PendingMail, Long> {

}
