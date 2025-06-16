package com.mohdiop.notification.interfaces;

import java.sql.SQLException;

public interface NotificationObserver {
    Boolean sendMessage(String from, String content, String to, String subject, int canalId) throws SQLException;
}
