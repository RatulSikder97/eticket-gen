package com.neutron.eticket.models.domains;

public class ViolationList {
    private String name;
    private String code;
    private double fee;
    private ViolationFieldList violationFieldList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public ViolationFieldList getViolationFieldList() {
        return violationFieldList;
    }

    public void setViolationFieldList(ViolationFieldList violationFieldList) {
        this.violationFieldList = violationFieldList;
    }
}
