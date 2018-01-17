package com.elson.basecore.exception;

/**
 * 异常
 * Created by elson on 2017/8/29
 */

public class BizRunException extends RuntimeException {

    private static final String DEFAULT_ERROR_CODE = "UNKNOW_ERROR";
    private String errorCode = DEFAULT_ERROR_CODE;

    private String errorMsg = null;

    public BizRunException(String message) {
        super(message);
        this.errorMsg = message;
    }

    public BizRunException(String code, String message) {
        super(message);
        this.errorCode = code;
        this.errorMsg = message;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public String getErrorMsg() {
        if (errorMsg != null) {
            return errorMsg;
        }
        return DEFAULT_ERROR_CODE;
    }

}
