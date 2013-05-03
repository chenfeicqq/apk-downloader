package com.gc.android.market.api.exception;

public class HttpException extends RuntimeException
{
    /** 默认序列号版本UID */
    private static final long serialVersionUID = 1L;

    private int errorCode;

    private String errorData;

    public HttpException(final int errorCode, final String errorData)
    {
        super("HTTP Code " + errorCode + " : " + errorData);
        this.errorCode = errorCode;
        this.errorData = errorData;
    }

    public int getErrorCode()
    {
        return this.errorCode;
    }

    public String getErrorData()
    {
        return this.errorData;
    }

}