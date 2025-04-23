package shared;

import java.io.Serializable;
import java.time.LocalDateTime;

public class User implements Serializable {
    private Long id;
    private String username;
    private String heslo;
    private LocalDateTime lastLogin;

    public String getHeslo() {
        return heslo;
    }

    public User setHeslo(String heslo) {
        this.heslo = heslo;
        return this;
    }

    public User() {}

    public Long getId() {
        return id;
    }

    public User setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String loginJmeno) {
        this.username = loginJmeno;
        return this;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public User setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
        return this;
    }
}
