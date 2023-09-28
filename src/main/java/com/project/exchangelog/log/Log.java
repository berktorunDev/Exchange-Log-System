package com.project.exchangelog.log;

import java.util.Date;

public class Log {
    // This class represents a Log object that captures details of an operation.

    private Date operationDate;
    private String operationTitle;
    private String relatedTable;
    private Object operatedObject;

    // Constructor for creating a Log object with operation details.

    public Log(Date operationDate, String operationTitle, String relatedTable, Object operatedObject) {
        this.operationDate = operationDate;
        this.operationTitle = operationTitle;
        this.relatedTable = relatedTable;
        this.operatedObject = operatedObject;
    }

    // Getter and setter methods for class properties.

    public Date getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(Date operationDate) {
        this.operationDate = operationDate;
    }

    public String getOperationTitle() {
        return operationTitle;
    }

    public void setOperationTitle(String operationTitle) {
        this.operationTitle = operationTitle;
    }

    public String getRelatedTable() {
        return relatedTable;
    }

    public void setRelatedTable(String relatedTable) {
        this.relatedTable = relatedTable;
    }

    public Object getOperatedObject() {
        return operatedObject;
    }

    public void setOperatedObject(Object operatedObject) {
        this.operatedObject = operatedObject;
    }

    // toString() method for displaying log details.

    @Override
    public String toString() {
        return "Log [operationDate=" + operationDate + ", operationTitle=" + operationTitle + ", relatedTable="
                + relatedTable + ", operatedObject=" + operatedObject + "]";
    }
}
