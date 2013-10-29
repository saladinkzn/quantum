package ru.kpfu.quantum.spring.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.quantum.spring.entities.ProjectGroup;
import ru.kpfu.quantum.spring.entities.User;

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

    @Query("select distinct pg from ProjectGroup pg")
    public List<ProjectGroup> findAllGroups();

    @Query("select distinct pg from ProjectGroup pg left join fetch pg.projects p where pg.id = ?1")
    public ProjectGroup findOneFetchChildren(Long groupId);

    @Query("select distinct pg from ProjectGroup pg where pg.creator = ?1")
    public List<ProjectGroup> findAllGroupsOwnByUser(User creator);




}
