package site.biteme.biteme.global.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    ;




    private int status;
    private String message;

    ErrorCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
