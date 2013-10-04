package ru.kpfu.quantum.spring.entities;

import javax.persistence.*;

/**
 * @author sala
 */
@Entity
public class PasswordRemind {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(optional = false)
    private User user;

    @Column(unique = true, nullable = false)
    private String code;

    @Column(nullable = false)
    private boolean used;

    protected PasswordRemind() {
    }

    public PasswordRemind(User user, String code) {
        this.user = user;
        this.code = code;
        this.used = false;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }
}
