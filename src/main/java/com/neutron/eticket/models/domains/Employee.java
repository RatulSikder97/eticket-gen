package com.neutron.eticket.models.domains;
import com.neutron.eticket.models.domains.PersonalDetails;

public class Employee {
    private long id;
    private PersonalDetails personalDetails;

    public long getID() { return id; }
    public void setID(long value) { this.id = value; }

    public PersonalDetails getPersonalDetails() { return personalDetails; }
    public void setPersonalDetails(PersonalDetails value) { this.personalDetails = value; }
}





