package com.neutron.eticket.models.domains;

public class DetailList {
    private String type;
    private Object notes;
    private String officerNotes;
    private ViolationList[] violationList;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getNotes() {
        return notes;
    }

    public void setNotes(Object notes) {
        this.notes = notes;
    }

    public String getOfficerNotes() {
        return officerNotes;
    }

    public void setOfficerNotes(String officerNotes) {
        this.officerNotes = officerNotes;
    }

    public ViolationList[] getViolationList() {
        return violationList;
    }

    public void setViolationList(ViolationList[] violationList) {
        this.violationList = violationList;
    }
}
