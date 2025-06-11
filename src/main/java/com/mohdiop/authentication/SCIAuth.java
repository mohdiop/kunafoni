package com.mohdiop.authentication;

import com.mohdiop.database.daosimpls.SCIDAOImpl;
import com.mohdiop.database.models.SCI;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.SQLException;

public class SCIAuth implements Authentication {

    private final SCIDAOImpl scidao = new SCIDAOImpl();

    @Override
    public Boolean login(String identifiant, String password) throws SQLException {
        SCI sci = scidao.getSCI();
        return BCrypt.checkpw(password, sci.getMotDePasse());
    }
}
