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

    @Query("select distinct p from ProjectGroup pg join pg.projects p where pg.id = ?1 and p.archive = ?2")
    public List<Project> findAllByArchive(Long groupId, boolean isArchive);

    @Query("select p from Project p left join fetch p.functions f where p.id = ?1")
    public Project findOneFetchFunctions(long id);

}
