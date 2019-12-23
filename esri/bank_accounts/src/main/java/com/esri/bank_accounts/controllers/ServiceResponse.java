package com.esri.bank_accounts.controllers;

public class ServiceResponse<T> {

    T record;
    String message;

    public ServiceResponse(T record, String message) {
        this.record = record;
        this.message = message;
    }

    public T getRecord() {
        return record;
    }

    public void setRecord(T record) {
        this.record = record;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
