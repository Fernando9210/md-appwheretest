package com.fer.app.appwhere.model;

/**
 * Created by Fer on 25/06/2019.
 */

public class SucursalesObj {
    private String merchantName;
    private String merchantAddress;
    private String merchantTelephone;
    private Object merchants[];

    public Object[] getMerchants() {
        return merchants;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public String getMerchantAddress() {
        return merchantAddress;
    }

    public String getMerchantTelephone() {
        return merchantTelephone;
    }

}
