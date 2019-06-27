package com.fer.app.appwhere.model;

/**
 * Created by Fer on 26/06/2019.
 */

public class MerchantsObj {
    private String merchantName;
    private String merchantAddress;
    private String merchantTelephone;
    private String latitude;
    private String longitude;

    public MerchantsObj(String latitude, String longitude, String merchantAddress, String merchantName, String merchantTelephone){
        this.latitude = latitude;
        this.longitude = longitude;
        this.merchantAddress = merchantAddress;
        this.merchantName = merchantName;
        this.merchantTelephone = merchantTelephone;
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

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }
}
