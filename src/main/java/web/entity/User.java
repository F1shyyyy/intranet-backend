package web.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class User {
    @Id
    private Long id;
    private String jmeno;
    private String heslo;
    private LocalDateTime lastLogin;

    public User(){

    }

    public User(shared.User user){
        this.id = user.getId();
        this.jmeno = user.getUsername();
        this.heslo = user.getHeslo();
        this.lastLogin = user.getLastLogin();
    }

    public shared.User getSharedUser() {
        shared.User user = new shared.User();
        user.setId(this.id);
        user.setUsername(this.jmeno);
        user.setHeslo(this.heslo);
        user.setLastLogin(this.lastLogin);
        return user;
    }

    public String getJmeno() {
        return jmeno;
    }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    public String getHeslo() {
        return heslo;
    }

    public void setHeslo(String heslo) {
        this.heslo = heslo;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
