package ru.kpfu.quantum.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.quantum.spring.entities.Project;

/**
 * @author sala
 */
@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {
}
