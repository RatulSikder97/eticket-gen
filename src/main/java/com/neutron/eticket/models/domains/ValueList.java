package com.neutron.eticket.models.domains;

import com.google.gson.annotations.SerializedName;

public class ValueList {
    @SerializedName("orderId")
    private long orderID;
    private String name;
    private String[] values;
    private String value;


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public long getOrderID() {
        return orderID;
    }

    public void setOrderID(long orderID) {
        this.orderID = orderID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getValues() {
        return values;
    }

    public void setValues(String[] values) {
        this.values = values;
    }
}
