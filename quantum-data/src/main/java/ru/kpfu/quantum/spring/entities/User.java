package ru.kpfu.quantum.spring.entities;

import javax.persistence.*;

/**
 * Класс пользователя.
 * Отмечен аннотацией Entity, поэтому будет сохранен в СУБД
 *
 * @author sala
 */
@Entity
public class User {
    /**
     * Идентификатор пользователя. Генерируется автоматически.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /**
     * Имя пользователя.
     */
    @Column
    private String name;
}
