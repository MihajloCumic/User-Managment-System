package com.raf.usermanagmentsystem.exceptions;

public class PrivilegeNotFoundException extends RuntimeException{
    public PrivilegeNotFoundException(){
        super("Privilege was not found.");
    }
}
