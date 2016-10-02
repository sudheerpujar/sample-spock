package com.hsbc.testing.automation.spock.data.vo;

import com.hsbc.testing.automation.spock.data.builder.impl.SpockVoBuilder;

import java.sql.Timestamp;

/**
 * Created by sudhe on 24-07-2016.
 */

public class SpockVo {
    private  int id;
    private  String name;
    private  String emailId;
    private  Timestamp date;
    private  float amount;


    public SpockVo(SpockVoBuilder spockVoBuilder) {
        id=spockVoBuilder.getId();
        name=spockVoBuilder.getName();
        emailId=spockVoBuilder.getEmailId();
        date=spockVoBuilder.getDate();
        amount=spockVoBuilder.getAmount();
    }

    public float getAmount() {
        return amount;
    }

    public Timestamp getDate() {
        return date;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SpockVo)) return false;

        SpockVo spockVo = (SpockVo) o;

        if (getId() != spockVo.getId()) return false;
        if (Float.compare(spockVo.getAmount(), getAmount()) != 0) return false;
        if (!getName().equals(spockVo.getName())) return false;
        if (!getEmailId().equals(spockVo.getEmailId())) return false;
        return getDate().equals(spockVo.getDate());

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
        return "SpockVo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", emailId='" + emailId + '\'' +
                ", date=" + date +
                ", amount=" + amount +
                '}';
    }
}
