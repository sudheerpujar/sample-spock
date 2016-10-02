package com.hsbc.testing.automation.spock.bean;

import java.util.Date;

/**
 * Created by sudhe on 30-08-2016.
 */
public class RestSpock {
    private  int id;
    private  String name;
    private  String emailId;
    private  Date date;
    private  float amount;

    public RestSpock(){

    }

    public RestSpock(int id, String name, String emailId, Date date, float amount) {
        this.id=id;
        this.name=name;
        this.emailId=emailId;
        this.date=date;
        this.amount=amount;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmailId() {
        return emailId;
    }

    public Date getDate() {
        return date;
    }

    public float getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RestSpock)) return false;

        RestSpock restSpock = (RestSpock) o;

        if (getId() != restSpock.getId()) return false;
        if (Float.compare(restSpock.getAmount(), getAmount()) != 0) return false;
        if (!getName().equals(restSpock.getName())) return false;
        if (!getEmailId().equals(restSpock.getEmailId())) return false;
        return getDate().equals(restSpock.getDate());

    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getName().hashCode();
        result = 31 * result + getEmailId().hashCode();
        result = 31 * result + getDate().hashCode();
        result = 31 * result + (getAmount() != +0.0f ? Float.floatToIntBits(getAmount()) : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RestSpock{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", emailId='" + emailId + '\'' +
                ", date=" + date +
                ", amount=" + amount +
                '}';
    }


}
