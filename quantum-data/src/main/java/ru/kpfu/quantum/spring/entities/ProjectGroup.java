package ru.kpfu.quantum.spring.entities;

import javax.persistence.*;
import java.util.ArrayList;
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

    @ManyToOne()
    @JoinColumn(name = "creator_id")
    private User creator;

    protected ProjectGroup() {
    }

    public ProjectGroup(String name, User creator) {
        if(name == null) throw new IllegalArgumentException("name cannot be null");
        if(creator == null) throw new IllegalArgumentException("creator cannot be null");
        this.name = name;
        this.creator = creator;
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
