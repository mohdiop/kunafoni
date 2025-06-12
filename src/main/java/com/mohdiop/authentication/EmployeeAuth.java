package com.mohdiop.authentication;

import com.mohdiop.database.daosimpls.EmployesDAOImpl;
import com.mohdiop.database.models.Employe;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.SQLException;

public class EmployeeAuth implements Authentication {

    private final EmployesDAOImpl employesDAO = new EmployesDAOImpl();

    @Override
    public Boolean login(String identifiant, String password) throws SQLException {
        Employe employee = employesDAO.getEmployeeById(identifiant);
        if (employee == null) {
            return null;
        }
        return BCrypt.checkpw(password, employee.getMotDePasse());
    }
}
