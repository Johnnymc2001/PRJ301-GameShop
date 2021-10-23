/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package johnny.registeration;

import java.io.Serializable;

/**
 *
 * @author JohnnyMC
 */
public class RegisterationCreateError implements Serializable {
    private String usernameLengthError;
    private String passwordLengthError;
    private String nameLengthError;
    private String confirmNotMatched;
    private String usernameIsExisted;

    public RegisterationCreateError() {
    }

    public String getUsernameLengthError() {
        return usernameLengthError;
    }

    public void setUsernameLengthError(String usernameLengthError) {
        this.usernameLengthError = usernameLengthError;
    }

    public String getPasswordLengthError() {
        return passwordLengthError;
    }

    public void setPasswordLengthError(String passwordLengthError) {
        this.passwordLengthError = passwordLengthError;
    }

    public String getNameLengthError() {
        return nameLengthError;
    }

    public void setNameLengthError(String nameLengthError) {
        this.nameLengthError = nameLengthError;
    }

    public String getConfirmNotMatched() {
        return confirmNotMatched;
    }

    public void setConfirmNotMatched(String confirmNotMatched) {
        this.confirmNotMatched = confirmNotMatched;
    }

    public String getUsernameIsExisted() {
        return usernameIsExisted;
    }

    public void setUsernameIsExisted(String usernameIsExisted) {
        this.usernameIsExisted = usernameIsExisted;
    }
    
    
}
