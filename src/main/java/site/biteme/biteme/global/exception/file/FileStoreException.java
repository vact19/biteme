package site.biteme.biteme.global.exception.file;

import site.biteme.biteme.global.exception.BusinessException;
import site.biteme.biteme.global.exception.ErrorCode;

public class FileStoreException extends BusinessException{
    public FileStoreException(ErrorCode errorCode) {
        super(errorCode);
    }
}
