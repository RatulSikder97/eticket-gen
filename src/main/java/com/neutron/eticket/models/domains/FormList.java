package com.neutron.eticket.models.domains;

public class FormList {
    private String name;
    private String type;
    private ValueList[] valueList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ValueList[] getValueList() {
        return valueList;
    }

    public void setValueList(ValueList[] valueList) {
        this.valueList = valueList;
    }
}
