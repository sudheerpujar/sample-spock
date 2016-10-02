package com.hsbc.testing.automation.spock.bean;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import com.hsbc.testing.automation.spock.builder.impl.SpockBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by sudhe on 30-07-2016.
 */
public class Spock {
    private final int id;
    private final String name;
    private final String emailId;
    private final Date date;
    private final float amount;

    public Spock(SpockBuilder spockBuilder) {
        id=spockBuilder.getId();
        name=spockBuilder.getName();
        emailId=spockBuilder.getEmailId();
        date=spockBuilder.getDate();
        amount=spockBuilder.getAmount();
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
        if (!(o instanceof Spock)) return false;

        Spock spock = (Spock) o;

        if (getId() != spock.getId()) return false;
        if (Float.compare(spock.getAmount(), getAmount()) != 0) return false;
        if (!getName().equals(spock.getName())) return false;
        if (!getEmailId().equals(spock.getEmailId())) return false;
        return getDate().equals(spock.getDate());

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
        return "Spock{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", emailId='" + emailId + '\'' +
                ", date=" + date +
                ", amount=" + amount +
                '}';
    }
}
