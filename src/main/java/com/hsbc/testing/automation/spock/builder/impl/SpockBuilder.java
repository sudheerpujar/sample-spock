package com.hsbc.testing.automation.spock.builder.impl;

import com.hsbc.testing.automation.spock.bean.Spock;
import com.hsbc.testing.automation.spock.builder.Builder;
import com.hsbc.testing.automation.spock.data.dao.SpockDao;
import com.hsbc.testing.automation.spock.data.vo.SpockVo;

import java.sql.Timestamp;
import java.util.*;

/**
 * Created by sudhe on 30-07-2016.
 */
public class SpockBuilder implements Builder {
    private int id;
    private String name;
    private String emailId;
    private Date date;
    private float amount;
    private SpockDao spockDao;

    public SpockBuilder(SpockDao spockDao){
        this.spockDao=spockDao;
    }

    private Spock spockVo2Bean(SpockVo vo) {
        if (vo==null) return null;
        return this.id(vo.getId())
                .name(vo.getName())
                .emailId(vo.getEmailId())
                .date(new Date(vo.getDate().getTime()))
                .amount(vo.getAmount())
                .build();
    }

    private SpockVo spockBean2Vo(Spock spock) {
        return spockDao.getMapper().getBuilder()
                .id(spock.getId())
                .name(spock.getName())
                .date(new Timestamp(spock.getDate().getTime()))
                .emailId(spock.getEmailId())
                .amount(spock.getAmount())
                .build();
    }

    public SpockBuilder id(int id){
        this.id=id;
        return this;
    }

    public SpockBuilder name(String name){
        this.name=name;
        return this;
    }

    public SpockBuilder emailId(String emailId){
        this.emailId=emailId;
        return this;
    }
    public SpockBuilder date(Date date){
        this.date=date;
        return this;
    }

    public SpockBuilder amount(float amount){
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

    public Date getDate() {
        return date;
    }

    public float getAmount() {
        return amount;
    }

    public List<Spock> getAll(){
        List<SpockVo> allVo=spockDao.getAll();
        List<Spock> all = new ArrayList<Spock>();
        for (SpockVo vo : allVo){
            all.add(spockVo2Bean(vo));
        }
        return all;
    }
    public Spock getById(int id){
        return spockVo2Bean(spockDao.getById(id));
    }

    public Spock getByEmailId(String emailId){
        return spockVo2Bean(spockDao.getByEmailId(emailId));
    }

    public void createSpock(Spock spock){
        spockDao.createSpock(spockBean2Vo(spock));
    }

    public void modifySpock(Spock spock){
        spockDao.modifySpock(spockBean2Vo(spock));
    }

    public void removeSpock(int id){
        spockDao.removeSpock(id);
    }

    @Override
    public Spock build() {
        return new Spock(this);
    }
}