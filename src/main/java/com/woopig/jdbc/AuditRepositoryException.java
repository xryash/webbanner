package com.woopig.jdbc;

public class AuditRepositoryException extends RuntimeException{

    public AuditRepositoryException() {}

    public AuditRepositoryException(String message) { super(message); }
}