package com.gfs.domain.response;

public class GeneralSubmitResponse extends BaseResponse {
    private boolean success;

    public GeneralSubmitResponse() {
        super();
    }

    public GeneralSubmitResponse(boolean success) {
        super();
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
