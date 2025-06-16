package com.mohdiop.notification.observers;

import com.mohdiop.database.daosimpls.EmployesDAOImpl;
import com.mohdiop.database.daosimpls.NotificationDAOImpl;
import com.mohdiop.database.models.Employe;
import com.mohdiop.database.models.Notification;
import com.mohdiop.notification.interfaces.NotificationObserver;

import java.sql.SQLException;

public class ConsoleNotification implements NotificationObserver {

    private final NotificationDAOImpl notificationDAO = new NotificationDAOImpl();
    private final EmployesDAOImpl employesDAO = new EmployesDAOImpl();

    @Override
    public Boolean sendMessage(String from, String content, String to, String subject, int canalId) throws SQLException {
        Employe expeditor = employesDAO.getEmployeeByEmail(from);
        return notificationDAO.addNewNotification(new Notification(
                content,
                expeditor.getIdentifiant(),
                canalId
        ));
    }
}
