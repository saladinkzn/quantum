package ru.kpfu.quantum.spring.entities;

import javax.persistence.*;

/**
 * @author sala
 */
@Entity
public class Function {
    @Id
    @GeneratedValue
    private long id;

    @Column
    private String name;

    @Column
    private String code;

    @Column
    private String imageUrl;

    @ManyToOne
    private Project project;

    protected Function() {}

    public Function(String name, String code, String imageUrl, Project project) {
        this.name = name;
        this.code = code;
        this.imageUrl = imageUrl;
        this.project = project;
    }

    public long getId() {
        return id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
