package com.mohdiop.database.daosimpls;

import com.mohdiop.database.PostgresDB;
import com.mohdiop.database.daos.NotificationDAO;
import com.mohdiop.database.models.Notification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class NotificationDAOImpl implements NotificationDAO {
    private final Connection pg = PostgresDB.getInstance().connection;

    @Override
    public Notification getNotificationById(int id) throws SQLException {
        String query = "select * from notification where id = " + id;
        PreparedStatement preparedStatement = pg.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        return new Notification(
                resultSet.getInt("id"),
                resultSet.getString("message"),
                resultSet.getString("idExpediteur")
        );
    }

    @Override
    public ArrayList<Notification> getAllNotifications() throws SQLException {
        String query = "select * from notification";
        PreparedStatement preparedStatement = pg.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        ArrayList<Notification> notifications = new ArrayList<>();
        while (resultSet.next()) {
            notifications.add(new Notification(
                    resultSet.getInt("id"),
                    resultSet.getString("message"),
                    resultSet.getString("idExpediteur")
            ));
        }
        return notifications;
    }

    @Override
    public Boolean addNewNotification(Notification notification) throws SQLException {
        String query = String.format("insert into notification (message, idExpediteur) values (%s, %s)",
                notification.getMessage(), notification.getIdExpediteur());
        PreparedStatement preparedStatement = pg.prepareStatement(query);
        return preparedStatement.execute();
    }
}
