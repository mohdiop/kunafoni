package com.mohdiop;

import com.mohdiop.notification.observers.ConsoleNotification;
import com.mohdiop.notification.observers.EmailNotification;
import com.mohdiop.notification.subject.NotificationSubjectImpl;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        NotificationSubjectImpl notificationSubject = new NotificationSubjectImpl();
        notificationSubject.addObserver(new EmailNotification());
        notificationSubject.addObserver(new ConsoleNotification());
        try {
            notificationSubject.sendNotification("mohdiopcode@gmail.com", "Bonjour", "mohameddiop1951@gmail.com", "Salutation", 6);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}