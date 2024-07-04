package com.neutron.eticket.models.domains;

import java.util.Objects;

public class Vehicle {

    private String year;
    private String make;
    private String model;
    private String color;
    private String type;
    private String licensePlate;
    private String state;
    private String vin;
    private Object color2;
    private String cmv;
    private String hazmat;
    private long[] exp;

    public String getExpire() {
        return  this.exp[1] + "/" + this.exp[2] + "/" + this.exp[0];
    }
    public String isCVM() {
        return Objects.equals(this.cmv, "yes") ? "checked" : "";
    }

    public String isHAZ() {
        return Objects.equals(this.hazmat, "yes") ? "checked" : "";
    }


    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public Object getColor2() {
        return color2;
    }

    public void setColor2(Object color2) {
        this.color2 = color2;
    }

    public String getCmv() {
        return cmv;
    }

    public void setCmv(String cmv) {
        this.cmv = cmv;
    }

    public String getHazmat() {
        return hazmat;
    }

    public void setHazmat(String hazmat) {
        this.hazmat = hazmat;
    }

    public long[] getExp() {
        return exp;
    }

    public void setExp(long[] exp) {
        this.exp = exp;
    }
}
