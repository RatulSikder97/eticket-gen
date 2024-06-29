package com.neutron.eticket.models.domains;


public class Body {
    private long id;
    private String departmentID;
    private long[] incidentTs;
    private long[] localIncidentTs;
    private String timezone;
    private String timezoneOffset;
    private Vehicle vehicle;
    private Driver driver;
    private String status;
    private long courtID;
    private String courtName;
    private boolean canEdit;
    private IncidentLocation incidentLocation;
    private Officer officer;
    private Object voidData;
    private DetailList[] detailList;
    private FormList[] formList;
    private Object officerNotes;
    private long[] appearAtCourtDate;
    private boolean hasETicket;
    private boolean canVoid;
}
