package ru.kpfu.quantum.spring.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.quantum.spring.entities.ProjectGroup;

import java.util.List;

/**
 * @author sala
 */
@Repository
public interface ProjectGroupRepository extends CrudRepository<ProjectGroup, Long> {
    /**
     * @return Возвращает список всех групп проектов с заполненным списком детей
     */
    @Query("select distinct pg from ProjectGroup pg join fetch pg.projects p")
    public List<ProjectGroup> findAllFetchChildren();
}
