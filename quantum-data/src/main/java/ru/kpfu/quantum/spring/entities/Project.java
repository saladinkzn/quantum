package ru.kpfu.quantum.spring.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @author sala
 */
@Entity
public class Project {
    /**
     * Идентификатор проекта
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Название проекта
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * Дата создания проекта
     */
    @Column(nullable = false)
    private Date created;

    /**
     * Дата последнего изменения проекта
     */
    @Column(nullable = false)
    private Date lastModified;

    /**
     * Признак, является ли проект архивным
     */
    @Column(nullable = false)
    private boolean archive;

    /**
     * Признак, является ли проект вычисленным
     */
    @Column(nullable = false)
    private boolean calculated;

    /**
     * Описание проекта
     */
    @Column(nullable = true, length = 8000)
    private String description;

    /**
     * Список функций проекта
     */
    @OneToMany(mappedBy = "project", orphanRemoval = true)
    private List<Function> functions;

    protected Project() {
    }

    /**
     * Создает проект
     *
     * @param name Название проекта
     * @param functions Список функций
     */
    public Project(String name, List<Function> functions) {
        if(name == null) throw new IllegalArgumentException("name cannot be null");
        if(functions == null) throw new IllegalArgumentException("functions cannot be null");
        this.name = name;
        this.functions = functions;
        this.created = new Date();
        this.lastModified = new Date();
        this.archive = false;
        this.calculated = false;
    }

    /**
     * @return Идентификатор проекта
     */
    public Long getId() {
        return id;
    }

    /**
     * @return Название проекта
     */
    public String getName() {
        return name;
    }

    /**
     * @return Дата создания
     */
    public Date getCreated() {
        return created;
    }

    /**
     * @return Дата последнего редактирования
     */
    public Date getLastModified() {
        return lastModified;
    }

    /**
     * @return Признак "архивности"
     */
    public boolean isArchive() {
        return archive;
    }

    /**
     * @return Признак "вычисленности"
     */
    public boolean isCalculated(){
        return calculated;
    }

    /**
     * @return Описание проекта
     */
    public String getDescription() {
        return description;
    }

    public void setArchive(boolean archive) {
        this.archive = archive;
    }

    public void setCalculated(boolean calculated) {
        this.calculated = calculated;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public List<Function> getFunctions() {
        return functions;
    }
}
