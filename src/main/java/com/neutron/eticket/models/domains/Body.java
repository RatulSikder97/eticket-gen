package com.neutron.eticket.models.domains;


import com.google.gson.annotations.SerializedName;

import java.util.*;

public class Body {
    private long id;
    @SerializedName("departmentId")
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(String departmentID) {
        this.departmentID = departmentID;
    }

    public long[] getIncidentTs() {
        return incidentTs;
    }


    public String getAppearAtCourtDateF() {
        return appearAtCourtDate[1] + "/"+ appearAtCourtDate[2] + "/" +  appearAtCourtDate[0] ;
    }
    public String getDatetTs() {
        return incidentTs[1] + "/"+ incidentTs[2] + "/" +  incidentTs[0] ;
    }

    public String getTimetTs() {
        long hour = localIncidentTs[3] % 12;

        String sHour = String.valueOf(hour);
        if(hour == 0) {
            sHour = String.valueOf(12);
        }

        if (hour < 10) {
            sHour = "0"+hour;
        }
        return sHour + ":"+ localIncidentTs[4] + ":" + (localIncidentTs[5] < 10 ? "0" : "") + localIncidentTs[5] ;
    }

    public String getTimeTsPm() {
        return localIncidentTs[3] >= 12 ? "checked": "";
    }

    public String getTimeTsAm() {
        return localIncidentTs[3] < 12 ? "checked" : "";
    }

    public void setIncidentTs(long[] incidentTs) {
        this.incidentTs = incidentTs;
    }

    public long[] getLocalIncidentTs() {
        return localIncidentTs;
    }

    public void setLocalIncidentTs(long[] localIncidentTs) {
        this.localIncidentTs = localIncidentTs;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getTimezoneOffset() {
        return timezoneOffset;
    }

    public void setTimezoneOffset(String timezoneOffset) {
        this.timezoneOffset = timezoneOffset;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getCourtID() {
        return courtID;
    }

    public void setCourtID(long courtID) {
        this.courtID = courtID;
    }

    public String getCourtName() {
        return courtName;
    }

    public void setCourtName(String courtName) {
        this.courtName = courtName;
    }

    public boolean isCanEdit() {
        return canEdit;
    }

    public void setCanEdit(boolean canEdit) {
        this.canEdit = canEdit;
    }

    public IncidentLocation getIncidentLocation() {
        return incidentLocation;
    }

    public void setIncidentLocation(IncidentLocation incidentLocation) {
        this.incidentLocation = incidentLocation;
    }

    public Officer getOfficer() {
        return officer;
    }

    public void setOfficer(Officer officer) {
        this.officer = officer;
    }

    public Object getVoidData() {
        return voidData;
    }

    public void setVoidData(Object voidData) {
        this.voidData = voidData;
    }

    public DetailList[] getDetailList() {
        return detailList;
    }

    public void setDetailList(DetailList[] detailList) {
        this.detailList = detailList;
    }

    public FormList[] getFormList() {
        return formList;
    }

    public void setFormList(FormList[] formList) {
        this.formList = formList;
    }

    public Object getOfficerNotes() {
        return officerNotes;
    }

    public void setOfficerNotes(Object officerNotes) {
        this.officerNotes = officerNotes;
    }

    public long[] getAppearAtCourtDate() {
        return appearAtCourtDate;
    }

    public void setAppearAtCourtDate(long[] appearAtCourtDate) {
        this.appearAtCourtDate = appearAtCourtDate;
    }

    public boolean isHasETicket() {
        return hasETicket;
    }

    public void setHasETicket(boolean hasETicket) {
        this.hasETicket = hasETicket;
    }

    public boolean isCanVoid() {
        return canVoid;
    }

    public void setCanVoid(boolean canVoid) {
        this.canVoid = canVoid;
    }

    public String getConditionDetail() {
        Optional<FormList> filteredFormList = Arrays.stream(formList).filter(details -> details.getType().equals("CONDITIONS")).findFirst();

        if(filteredFormList.isEmpty()) {
            return "";
        }
        ValueList[] valueList = filteredFormList.get().getValueList();
        int valueListLength = valueList.length;

        if(valueListLength == 0) {
            return "";
        }
        String conditionString = "";

        Long[] orders = {1L, 2L, 3L, 4L, 6L, 9L};
        Set<Long> orderIds = new HashSet<Long>();

        Collections.addAll(orderIds, orders);

        StringBuilder result = new StringBuilder();
        for (ValueList item : valueList) {

            if (!orderIds.contains(item.getOrderID())) {
                continue;
            }

            if (!result.isEmpty()) {
                result.append(", ");
            }


            if(item.getValues() != null && item.getValues().length > 0) {
                result.append("<b>").append(item.getName()).append("</b> - ").append(Arrays.toString(item.getValues()));
            }

            if(item.getValue() != null) {
                result.append("<b>").append(item.getName()).append("</b> - ").append(item.getValue());
            }

        }

        // Output the result string

        return result.toString();
    }

    public String getRadarValue() {
        Optional<FormList> filteredFormList = Arrays.stream(formList).filter(details -> details.getType().equals("CONDITIONS")).findFirst();

        if(filteredFormList.isEmpty()) {
            return "";
        }

        ValueList[] valueList = filteredFormList.get().getValueList();
        int valueListLength = valueList.length;

        if(valueListLength == 0) {
            return "";
        }

        Optional<ValueList> radar = Arrays.stream(valueList).filter(val -> val.getName().equals("Radar")).findFirst();

        if(radar.isEmpty()) {
            return  "";
        }

        return radar.get().getValue() != null ? radar.get().getValue() : (radar.get().getValues().length > 0 ? radar.get().getValues()[0] : "") ;
    }
}
