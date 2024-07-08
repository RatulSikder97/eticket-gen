package com.neutron.eticket.models.domains;

public class ZoneCheck {
    boolean isSchoolZone;
    boolean isConstructionZone;

    public  ZoneCheck() {
        this.isSchoolZone = false;
        this.isConstructionZone = false;
    }

    public boolean isSchoolZone() {
        return isSchoolZone;
    }

    public void setSchoolZone(boolean schoolZone) {
        isSchoolZone = schoolZone;
    }

    public boolean isConstructionZone() {
        return isConstructionZone;
    }

    public void setConstructionZone(boolean constructionZone) {
        isConstructionZone = constructionZone;
    }
}