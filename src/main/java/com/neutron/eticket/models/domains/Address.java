package com.neutron.eticket.models.domains;

public class Address {
    private String address;
    private String address2;
    private String city;
    private String state;
    private String zipCode;
    private Object timezone;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Object getTimezone() {
        return timezone;
    }

    public void setTimezone(Object timezone) {
        this.timezone = timezone;
    }
}
