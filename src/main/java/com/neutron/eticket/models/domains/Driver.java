package com.neutron.eticket.models.domains;

public class Driver {
    private String firstName;
    private String midName;
    private String lastName;
    private String sex;
    private String race;
    private long[] birthDate;
    private long age;
    private String dlNumber;
    private String dlType;
    private long[] dlExpire;
    private Address address;
    private String dlClassCode;
    private String dlClass;
    private boolean cdl;
    private String rest;
    private String end;
    private String height;
    private String weight;
    private String hairColor;
    private String eyeColor;
    private String stateDl;
    private Object phoneNumber;
    private boolean isDriver;

    public String getBirthString() {
        return this.birthDate[1] + "/" + this.birthDate[2] + "/" + this.birthDate[0];
    }
    public String isComY() {
        return this.cdl ? "checked" : "";
    }

    public String isComN() {
        return this.cdl ? "" : "checked";
    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMidName() {
        return midName;
    }

    public void setMidName(String midName) {
        this.midName = midName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public long[] getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(long[] birthDate) {
        this.birthDate = birthDate;
    }

    public long getAge() {
        return age;
    }

    public void setAge(long age) {
        this.age = age;
    }

    public String getDlNumber() {
        return dlNumber;
    }

    public void setDlNumber(String dlNumber) {
        this.dlNumber = dlNumber;
    }

    public String getDlType() {
        return dlType;
    }

    public void setDlType(String dlType) {
        this.dlType = dlType;
    }

    public long[] getDlExpire() {
        return dlExpire;
    }

    public void setDlExpire(long[] dlExpire) {
        this.dlExpire = dlExpire;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getDlClassCode() {
        return dlClassCode;
    }

    public void setDlClassCode(String dlClassCode) {
        this.dlClassCode = dlClassCode;
    }

    public String getDlClass() {
        return dlClass;
    }

    public void setDlClass(String dlClass) {
        this.dlClass = dlClass;
    }

    public boolean isCdl() {
        return cdl;
    }

    public void setCdl(boolean cdl) {
        this.cdl = cdl;
    }

    public String getRest() {
        return rest;
    }

    public void setRest(String rest) {
        this.rest = rest;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHairColor() {
        return hairColor;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    public String getStateDl() {
        return stateDl;
    }

    public void setStateDl(String stateDl) {
        this.stateDl = stateDl;
    }

    public Object getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Object phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isDriver() {
        return isDriver;
    }

    public void setDriver(boolean driver) {
        isDriver = driver;
    }
}
