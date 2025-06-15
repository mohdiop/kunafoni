package com.mohdiop.database.daos;

import com.mohdiop.database.models.Employe;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployesDAO {
    Employe getEmployeeById(String id) throws SQLException;

    ArrayList<Employe> getEmployeesByDepartment(String department) throws SQLException;

    ArrayList<Employe> getAllEmployees() throws SQLException;

    Boolean updatePassword(String employeeId, String newPassword) throws SQLException;

    Boolean updatePosition(String employeeId, String newPosition) throws SQLException;

    Boolean updateDepartment(String employeeId, String newDepartment) throws SQLException;

    Boolean deleteEmployeeById(String employeeId) throws SQLException;

    Boolean addNewEmployee(Employe newEmployee) throws SQLException;

    Employe getEmployeeByEmail(String email) throws SQLException;
}
