package ru.kpfu.quantum.spring.entities;

import javax.persistence.*;
import java.util.List;

/**
 * Группа проектов
 *
 * @author sala
 */
@Entity
public class ProjectGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false, unique = true)
    String name;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Project> projects;

    protected ProjectGroup() {
    }

    public ProjectGroup(String name) {
        this.name = name;
    }

    /**
     * @return Идентификатор группы проектов
     */
    public long getId() {
        return id;
    }

    /**
     * @return Название группы проектов
     */
    public String getName() {
        return name;
    }

    /**
     * @return Список проектов, входящих в группу, ленивый.
     */
    public List<Project> getProjects() {
        return projects;
    }
}
