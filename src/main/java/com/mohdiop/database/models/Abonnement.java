package com.mohdiop.database.models;

public class Abonnement {
    private String employeeId;
    private int canalId;

    public Abonnement(String employeeId, int canalId){
        this.employeeId = employeeId;
        this.canalId = canalId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public int getCanalId() {
        return canalId;
    }

    public void setCanalId(int canalId) {
        this.canalId = canalId;
    }
}
