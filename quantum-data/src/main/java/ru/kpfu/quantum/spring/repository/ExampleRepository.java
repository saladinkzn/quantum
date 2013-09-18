package ru.kpfu.quantum.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.quantum.spring.entities.Example;

/**
 * @author sala
 */
@Repository
public interface ExampleRepository extends CrudRepository<Example, Long> {
}
