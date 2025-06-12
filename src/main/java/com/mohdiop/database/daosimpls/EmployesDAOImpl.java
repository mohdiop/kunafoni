package com.mohdiop.database.daosimpls;

import com.mohdiop.database.PostgresDB;
import com.mohdiop.database.daos.EmployesDAO;
import com.mohdiop.database.models.Employe;
import com.mohdiop.utilities.Utilities;
import jdk.jshell.execution.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployesDAOImpl implements EmployesDAO {

    private final Connection pg = PostgresDB.getInstance().connection;

    @Override
    public Employe getEmployeeById(String id) throws SQLException {
        String query = "select * from employes where identifiant = '" + id + "'";
        PreparedStatement preparedStatement = pg.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            return new Employe(resultSet.getString("identifiant"),
                    resultSet.getString("motDePasse"),
                    resultSet.getString("nom"),
                    resultSet.getString("prenom"),
                    resultSet.getString("poste"),
                    resultSet.getString("departement"));
        } else {
            return null;
        }
    }

    @Override
    public ArrayList<Employe> getEmployeesByDepartment(String department) throws SQLException {
        String query = "select *  from employes where departement = '" + department + "'";
        PreparedStatement preparedStatement = pg.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        ArrayList<Employe> employes = new ArrayList<>();
        while (resultSet.next()) {
            employes.add(new Employe(resultSet.getString("identifiant"),
                    resultSet.getString("motDePasse"),
                    resultSet.getString("nom"),
                    resultSet.getString("prenom"),
                    resultSet.getString("poste"),
                    resultSet.getString("departement")));
        }
        return employes;
    }

    @Override
    public ArrayList<Employe> getAllEmployees() throws SQLException {
        String query = "select *  from employes";
        PreparedStatement preparedStatement = pg.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        ArrayList<Employe> employes = new ArrayList<>();
        while (resultSet.next()) {
            employes.add(new Employe(resultSet.getString("identifiant"),
                    resultSet.getString("motDePasse"),
                    resultSet.getString("nom"),
                    resultSet.getString("prenom"),
                    resultSet.getString("poste"),
                    resultSet.getString("departement")));
        }
        return employes;
    }

    @Override
    public Boolean updatePassword(String employeeId, String newPassword) throws SQLException {
        String query = "update employes set motDePasse = '" + newPassword + "' where identifiant = '" + employeeId +"'";
        PreparedStatement preparedStatement = pg.prepareStatement(query);
        preparedStatement.execute();
        return true;
    }

    @Override
    public Boolean updatePosition(String employeeId, String newPosition) throws SQLException {
        String query = "update employes set poste = '" + newPosition + "' where identifiant = '" + employeeId + "'";
        PreparedStatement preparedStatement = pg.prepareStatement(query);
        preparedStatement.execute();
        return true;
    }

    @Override
    public Boolean updateDepartment(String employeeId, String newDepartment) throws SQLException {
        String query = "update employes set departement = '" + newDepartment + "' where identifiant = '" + employeeId + "'";
        PreparedStatement preparedStatement = pg.prepareStatement(query);
        preparedStatement.execute();
        return true;
    }

    @Override
    public Boolean deleteEmployeeById(String employeeId) throws SQLException {
        String query = "delete from employes where identifiant = '" + employeeId + "'";
        PreparedStatement preparedStatement = pg.prepareStatement(query);
        preparedStatement.execute();
        return true;
    }

    @Override
    public Boolean addNewEmployee(Employe newEmployee) throws SQLException {
        if (isAlreadyChosenIdentifiantNumber(
                newEmployee.getIdentifiant().substring(9, 12)
        )){
            newEmployee.setIdentifiant("kunafoni-"+ Utilities.generateIdentifiantNumber());
            return addNewEmployee(newEmployee);
        } else {
            String query = String.format("insert into employes (identifiant, nom, prenom, motDePasse, poste, departement) values ('%s', '%s', '%s', '%s', '%s', '%s')",
                    newEmployee.getIdentifiant(), newEmployee.getNom(), newEmployee.getPrenom(), newEmployee.getMotDePasse(), newEmployee.getPoste(), newEmployee.getDepartement());
            PreparedStatement preparedStatement = pg.prepareStatement(query);
            preparedStatement.execute();
            return true;
        }
    }

    private Boolean isAlreadyChosenIdentifiantNumber(String identifiantNumber) throws SQLException {
        String query = "select * from employes";
        PreparedStatement preparedStatement = pg.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            if(resultSet.getString("identifiant").contains(identifiantNumber)){
                return true;
            }
        }
        return false;
    }
}
