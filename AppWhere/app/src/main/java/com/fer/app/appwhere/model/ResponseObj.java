package com.fer.app.appwhere.model;

import java.util.Objects;

/**
 * Created by Fer on 23/06/2019.
 */

public class ResponseObj {
    private String description;
    private String userId;
    private int status;
    private boolean successful;
    private Object merchants[];


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean getSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }
}
