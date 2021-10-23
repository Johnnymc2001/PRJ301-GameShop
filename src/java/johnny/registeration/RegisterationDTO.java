/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package johnny.registeration;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author JohnnyMC
 */
public class RegisterationDTO implements Serializable {
    private String username;
    private String password;
    private String name;
    private boolean isAdmin;

    public RegisterationDTO() {
        
    }

    public RegisterationDTO(String username, String password, String name, boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.isAdmin = isAdmin;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }


}
