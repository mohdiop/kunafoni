package com.mohdiop.notification.interfaces;

import java.sql.SQLException;

public interface NotificationSubject {
    void addObserver(NotificationObserver observer);
    void deleteObserver(NotificationObserver observer);
    void notifyObservers(String from, String to, String subject, int canalId) throws SQLException;
}
