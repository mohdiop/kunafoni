package com.mohdiop.authentication;

import com.mohdiop.database.daosimpls.EmployesDAOImpl;
import com.mohdiop.database.models.Employe;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.SQLException;

public class EmployeeAuth implements Authentication {

    private final EmployesDAOImpl employesDAO = new EmployesDAOImpl();

    @Override
    public Boolean login(String identifiant, String password) throws SQLException {
        Employe employe = employesDAO.getEmployeeById(identifiant);
        if(employe == null){
            return false;
        }
        return BCrypt.checkpw(password, employe.getMotDePasse());
    }
}
