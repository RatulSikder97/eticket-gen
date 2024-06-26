package com.neutron.eticket.models.domains;

public class PersonalDetails {
    private String name;
    private long age;
    private String bloodGroup;
    private String address;

    public String getName() { return name; }
    public void setName(String value) { this.name = value; }

    public long getAge() { return age; }
    public void setAge(long value) { this.age = value; }

    public String getBloodGroup() { return bloodGroup; }
    public void setBloodGroup(String value) { this.bloodGroup = value; }

    public String getAddress() { return address; }
    public void setAddress(String value) { this.address = value; }
}
