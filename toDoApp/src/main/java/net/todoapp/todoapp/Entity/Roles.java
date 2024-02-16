package net.todoapp.todoapp.Entity;

import org.springframework.security.core.GrantedAuthority;

public class Roles implements GrantedAuthority {
    String roleName;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String getAuthority() {
        return roleName;
    }
}
