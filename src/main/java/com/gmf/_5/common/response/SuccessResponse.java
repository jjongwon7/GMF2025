package com.gmf._5.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SuccessResponse<T> {

    private Boolean isSuccess;
    private int status;
    private int code;
    private String message;
    private T data;

    // 성공 시 공통 코드만 있는 경우
    public SuccessResponse(CommonCode commonCode) {
        this.isSuccess = true;
        this.status = commonCode.getStatus();
        this.code = commonCode.getCode();
        this.message = commonCode.getMessage();
        this.data = null;
    }

    // 성공 시 + 데이터 반환
    public SuccessResponse(CommonCode commonCode, T data) {
        this.isSuccess = true;
        this.status = commonCode.getStatus();
        this.code = commonCode.getCode();
        this.message = commonCode.getMessage();
        this.data = data;
    }
}
