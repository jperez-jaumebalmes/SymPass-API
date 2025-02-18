package net.jaumebalmes.dam.m13.entities;

import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.*;
import java.sql.Blob;
import java.time.OffsetDateTime;
import java.util.Date;

@Entity
@Table(name = "sympass_password")
public class Password {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String username;
    @Column(name = "account_password")
    private String password;
    @Column(name = "password_description")
    private String description;
    private String link;
    @Column(name = "password_type")
    private int type;
    @Column(name="last_modification",columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP")
    private OffsetDateTime lastModification;
    private String icon;
//    @Column(name = "folder_id")
//    private Directory folder;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @RestResource(path = "passwordUser", rel="user")
    private User user;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public OffsetDateTime getLastModification() {
        return lastModification;
    }

    public void setLastModification(OffsetDateTime lastModification) {
        this.lastModification = lastModification;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
