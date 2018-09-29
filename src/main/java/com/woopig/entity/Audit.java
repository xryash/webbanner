package com.woopig.entity;

import java.io.Serializable;

public class Audit implements Serializable {

    private String id;

    private String adminId;

    private String bannerId;

    private String operationType;

    private String columnName;

    private String oldValue;

    private String newValue;

    private String recordDate;

    public Audit() {
    }

    public Audit(String id, String adminId, String bannerId, String operationType, String columnName, String oldValue, String newValue, String recordDate) {
        this.id = id;
        this.adminId = adminId;
        this.bannerId = bannerId;
        this.operationType = operationType;
        this.columnName = columnName;
        this.oldValue = oldValue;
        this.newValue = newValue;
        this.recordDate = recordDate;
    }

    public Audit(String id, String adminId, String bannerId, String operationType, String recordDate) {
        this.id = id;
        this.adminId = adminId;
        this.bannerId = bannerId;
        this.operationType = operationType;
        this.recordDate = recordDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getBannerId() {
        return bannerId;
    }

    public void setBannerId(String bannerId) {
        this.bannerId = bannerId;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getOldValue() { return oldValue; }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    public String getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(String recordDate) {
        this.recordDate = recordDate;
    }

    @Override
    public String toString() {
        return "Audit{" +
                "id='" + id + '\'' +
                ", adminId='" + adminId + '\'' +
                ", bannerId='" + bannerId + '\'' +
                ", operationType='" + operationType + '\'' +
                ", columnName='" + columnName + '\'' +
                ", oldValue='" + oldValue + '\'' +
                ", newValue='" + newValue + '\'' +
                ", recordDate='" + recordDate + '\'' +
                '}';
    }
}
