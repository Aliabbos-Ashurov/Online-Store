package backend.model.user;

import backend.enums.Role;
import backend.model.BaseModel;
/**
 * @author Aliabbos Ashurov
 * Date: 23/February/2024  21:28
 **/
public class User extends BaseModel {
    private String fullname;
    private String username;
    private String password;
    private Role role;
    private boolean isActive;

    public User(String fullname, String username, String password, Role role, boolean isActive) {
        this.fullname = fullname;
        this.username = username;
        this.password = password;
        this.role = role;
        this.isActive = isActive;
    }
    public String getFullname() {
        return fullname;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public Role getRole() {
        return role;
    }
    public boolean isActive() {
        return isActive;
    }
    public void setActive(boolean active) {
        isActive = active;
    }
    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return String.format("User -> [Fullname: %s, UserName: %s, Password: %s, Role: %s, isActive: %b]",fullname,username,password,role,isActive);
    }
    public String toStringWithId() {
        return String.format("User -> [Fullname: %s, UserName: %s, Password: %s, Role: %s, isActive: %b, ID: %s]"
                ,fullname,username,password,role,isActive,getId());
    }

}