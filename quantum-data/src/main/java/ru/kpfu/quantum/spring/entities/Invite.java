package ru.kpfu.quantum.spring.entities;

import javax.persistence.*;

/**
 * @author sala
 */
@Entity
public class Invite {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false)
    private boolean used;

    @OneToOne(optional = true)
    private User registrant;

    protected Invite() {
    }

    /**
     * Создает инвайт
     * @param code код для инвайта
     */
    public Invite(String code) {
        if(code == null)
        this.code = code;
        this.used = false;
        this.registrant = null;
    }

    /**
     * @return Идентификатор инвайта
     */
    public long getId() {
        return id;
    }

    /**
     * @return Код программы
     */
    public String getCode() {
        return code;
    }

    /**
     * @return Признак использованности
     */
    public boolean isUsed() {
        return used;
    }

    /**
     * @return Пользователь, воспользовавшийся инвайтом
     */
    public User getRegistrant() {
        return registrant;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public void setRegistrant(User registrant) {
        this.registrant = registrant;
    }
}
