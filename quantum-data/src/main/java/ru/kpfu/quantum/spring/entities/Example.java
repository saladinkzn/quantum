package ru.kpfu.quantum.spring.entities;

import javax.persistence.*;

/**
 * Пример программы
 *
 * @author sala
 */
@Entity
public class Example {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = true)
    @Lob
    private String description;

    @Column(nullable = false)
    @Lob
    private String code;

    protected Example() {
    }

    /**
     * Создает пример программы
     * @param name Название примера
     * @param code Код программы
     */
    public Example(String name, String code) {
        if(name == null) {
            throw new IllegalArgumentException("name cannot be null");
        }
        this.name = name;
        this.code = code;
    }

    /**
     * @return Идентификатор пользователя
     */
    public long getId() {
        return id;
    }

    /**
     * @return Название примера
     */
    public String getName() {
        return name;
    }

    /**
     * @return Описание
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return Код программы
     */
    public String getCode() {
        return code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
