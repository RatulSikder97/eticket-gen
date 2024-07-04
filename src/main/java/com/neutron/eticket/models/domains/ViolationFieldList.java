package com.neutron.eticket.models.domains;

import com.google.gson.annotations.SerializedName;

public class ViolationFieldList {
    private int id;
    @SerializedName("Recorded Speed")
    private String recordedSpeed;
    @SerializedName("Posted Speed")
    private String postedSpeed;

    public String getRecordedSpeed() { return recordedSpeed != null ? recordedSpeed : ""; }
    public void setRecordedSpeed(String value) { this.recordedSpeed = value; }

    public String getPostedSpeed() { return postedSpeed != null ? postedSpeed : ""; }
    public void setPostedSpeed(String value) { this.postedSpeed = value; }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
