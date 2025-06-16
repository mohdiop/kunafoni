package com.mohdiop.notification.subject;

import com.mohdiop.notification.interfaces.NotificationObserver;
import com.mohdiop.notification.interfaces.NotificationSubject;

import java.sql.SQLException;
import java.util.ArrayList;

public class NotificationSubjectImpl implements NotificationSubject {
    private String message;
    private final ArrayList<NotificationObserver> observers = new ArrayList<>();

    @Override
    public void addObserver(NotificationObserver observer) {
        if(!isObserver(observer)){
            this.observers.add(observer);
        }
    }

    @Override
    public void deleteObserver(NotificationObserver observer) {
        if(isObserver(observer)){
            this.observers.remove(observer);
        }
    }

    @Override
    public void notifyObservers(String from, String to, String subject, int canalId) throws SQLException {
        for(NotificationObserver observer : observers){
            observer.sendMessage(from, message, to, subject, canalId);
        }
    }

    public Boolean isObserver(NotificationObserver observer){
        return this.observers.contains(observer);
    }

    public void sendNotification(String from, String message, String to, String subject, int canalId) throws SQLException{
        this.message = message;
        notifyObservers(from, to, subject, canalId);
    }
}
