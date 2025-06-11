package com.mohdiop.database.daos;

import com.mohdiop.database.models.Notification;

import java.sql.SQLException;
import java.util.ArrayList;

public interface NotificationDAO {
    Notification getNotificationById(int id) throws SQLException;

    ArrayList<Notification> getAllNotifications() throws SQLException;

    ArrayList<Notification> getNotificationsByChannelId(int channelId) throws SQLException;

    Boolean addNewNotification(Notification notification) throws SQLException;
}
