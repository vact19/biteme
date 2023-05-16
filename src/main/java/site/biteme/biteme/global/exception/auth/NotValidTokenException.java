package site.biteme.biteme.global.exception.auth;


import site.biteme.biteme.global.exception.BusinessException;
import site.biteme.biteme.global.exception.ErrorCode;

public class NotValidTokenException extends BusinessException {
    public NotValidTokenException(ErrorCode errorCode) {
        super(errorCode);
    }
}