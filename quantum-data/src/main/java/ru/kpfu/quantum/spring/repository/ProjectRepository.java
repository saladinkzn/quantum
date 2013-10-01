package ru.kpfu.quantum.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.quantum.spring.entities.Project;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author sala
 */
@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {


}
