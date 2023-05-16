package site.biteme.biteme.global.exception.file;

import site.biteme.biteme.global.exception.BusinessException;
import site.biteme.biteme.global.exception.ErrorCode;

public class FileIOException extends BusinessException{
    public FileIOException(ErrorCode errorCode) {
        super(errorCode);
    }
}
