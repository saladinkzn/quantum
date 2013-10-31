package ru.kpfu.quantum.spring.repository;

import org.springframework.data.repository.CrudRepository;
import ru.kpfu.quantum.spring.entities.Function;

/**
 * @author sala
 */
public interface FunctionRepository extends CrudRepository<Function, Long> {
}
