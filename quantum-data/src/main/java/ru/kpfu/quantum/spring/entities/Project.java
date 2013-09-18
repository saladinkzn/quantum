package ru.kpfu.quantum.spring.entities;

import javax.persistence.*;
import java.util.Date;

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
    @Column(nullable = false, unique = true)
    private String name;

    /**
     * Код программы
     */
    @Column(nullable = false, unique = true)
    @Lob
    private String code;

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
     * Описание проекта
     */
    @Column(nullable = true)
    @Lob
    private String description;

    @ManyToOne(optional = false)
    private User creator;

    protected Project() {
    }

    /**
     * Создает проект
     *
     * @param name Название проекта
     * @param code Код проекта
     * @param creator Создатель проекта
     */
    public Project(String name, String code, User creator) {
        if(name == null) throw new IllegalArgumentException("name cannot be null");
        if(code == null) throw new IllegalArgumentException("code cannot be null");
        if(created == null) throw new IllegalArgumentException("creator cannot be null");
        this.name = name;
        this.code = code;
        this.created = new Date();
        this.lastModified = new Date();
        this.archive = false;
        this.creator = creator;
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
     * @return Код проекта
     */
    public String getCode() {
        return code;
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
     * @return Описание проекта
     */
    public String getDescription() {
        return description;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setArchive(boolean archive) {
        this.archive = archive;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }
}
