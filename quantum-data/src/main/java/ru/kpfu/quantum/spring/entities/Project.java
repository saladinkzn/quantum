package ru.kpfu.quantum.spring.entities;

import javax.persistence.*;
import java.util.Date;

/**
 * @author sala
 */
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"name", "creatorId"}))
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
    @Column(nullable = false)
    private String name;

    /**
     * Код программы
     */
    @Column(nullable = false, length = 8000)
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
     * Признак, является ли проект вычисленным
     */
    @Column(nullable = false)
    private boolean calculated;

    /**
     * Описание проекта
     */
    @Column(nullable = true, length = 8000)
    private String description;

    @ManyToOne()
    @JoinColumn(name = "creatorId")
    private User creator;

    protected Project() {
    }

    /*
     * Создает проект
     *
     * @param name Название проекта
     * @param creator Создатель проекта
     * @param code Код проекта

    public Project(String name, User creator, String code) {
        if(name == null) throw new IllegalArgumentException("name cannot be null");
        if(created == null) throw new IllegalArgumentException("creator cannot be null");
        if(code == null) throw new IllegalArgumentException("code cannot be null");
        this.code = code;
        this.name = name;
        this.created = new Date();
        this.lastModified = new Date();
        this.archive = false;
        this.calculated = false;
        this.creator = creator;
    }          */

    //TODO Удалить этот конструктор и изменить экшен создания проекта, когда будет реализован пользователь
    /**
     * Создает проект
     *
     * @param name Название проекта
     * @param code Код проекта
     */
    public Project(String name, String code) {
        if(name == null) throw new IllegalArgumentException("name cannot be null");
        if(code == null) throw new IllegalArgumentException("code cannot be null");
        this.code = code;
        this.name = name;
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

    public void setCode(String code) {
        this.code = code;
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
}
