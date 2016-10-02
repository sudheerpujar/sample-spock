package com.hsbc.testing.automation.spock.data.builder.impl;

import com.hsbc.testing.automation.spock.builder.Builder;
import com.hsbc.testing.automation.spock.data.vo.SpockVo;

import java.sql.Timestamp;

/**
 * Created by sudhe on 30-07-2016.
 */
public class SpockVoBuilder implements Builder {

    private  int id;
    private  String name;
    private  String emailId;
    private Timestamp date;
    private  float amount;

    public SpockVoBuilder id(int id){
        this.id=id;
        return this;
    }

    public SpockVoBuilder name(String name){
        this.name=name;
        return this;
    }

    public SpockVoBuilder emailId(String emailId){
        this.emailId=emailId;
        return this;
    }
    public SpockVoBuilder date(Timestamp date){
        this.date=date;
        return this;
    }

    public SpockVoBuilder amount(float amount){
        this.amount=amount;
        return this;
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

    public Timestamp getDate() {
        return date;
    }

    public float getAmount() {
        return amount;
    }

    @Override
    public SpockVo build() {
        return new SpockVo(this);
    }
}
