package com.neutron.eticket.models.domains;

public class ETicket {
        private Body body;
        private Object[] errors;
        private long itemsCount;

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public Object[] getErrors() {
        return errors;
    }

    public void setErrors(Object[] errors) {
        this.errors = errors;
    }

    public long getItemsCount() {
        return itemsCount;
    }

    public void setItemsCount(long itemsCount) {
        this.itemsCount = itemsCount;
    }
}
