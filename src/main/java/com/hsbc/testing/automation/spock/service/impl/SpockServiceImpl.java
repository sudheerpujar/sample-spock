package com.hsbc.testing.automation.spock.service.impl;

import com.hsbc.testing.automation.spock.bean.Spock;
import com.hsbc.testing.automation.spock.builder.impl.SpockBuilder;
import com.hsbc.testing.automation.spock.service.SpockService;


import java.util.List;

/**
 * Created by sudhe on 30-07-2016.
 */
public class SpockServiceImpl implements SpockService {
    private SpockBuilder spockBuilder;

    public void setSpockBuilder(SpockBuilder spockBuilder) {
        this.spockBuilder = spockBuilder;
    }

    @Override
    public List<Spock> getAll() {
        return spockBuilder.getAll();
    }

    @Override
    public Spock getById(int id) {
        return spockBuilder.getById(id);
    }

    @Override
    public Spock getByEmailId(String emailId) {
        return spockBuilder.getByEmailId(emailId);
    }

    @Override
    public void createSpock(Spock spock) {
        spockBuilder.createSpock(spock);
    }

    @Override
    public void modifySpock(Spock spock) {
        spockBuilder.modifySpock(spock);
    }

    @Override
    public void removeSpock(int id) {
        spockBuilder.removeSpock(id);

    }
}
