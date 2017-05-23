/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.response;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 *
 * @author javie
 */
public class Autenticador extends Authenticator {
    public Autenticador() {
        super();
    }

@Override
public PasswordAuthentication getPasswordAuthentication() {
 String username = "djjaemaiyet@gmail.com";
 String password = "passworddjja";
    if ((username != null) && (username.length() > 0) && (password != null) 
      && (password.length   () > 0)) {

        return new PasswordAuthentication(username, password);
    }

    return null;
}
}
