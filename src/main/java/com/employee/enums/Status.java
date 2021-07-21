package com.employee.enums;

public enum Status {

    PENDING(0), APPROVED(1), REJECTED(2);

    private final int code;

    Status(int code) {
        this.code = code;
    }
}
