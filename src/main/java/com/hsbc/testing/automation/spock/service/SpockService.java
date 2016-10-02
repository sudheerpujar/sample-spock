package com.hsbc.testing.automation.spock.service;

import com.hsbc.testing.automation.spock.bean.Spock;

import java.util.List;

/**
 * Created by sudhe on 30-07-2016.
 */
public interface SpockService {
    List<Spock> getAll();
    Spock getById(int id);
    Spock getByEmailId(String emailId);
    void createSpock(Spock spock);
    void modifySpock(Spock spock);
    void removeSpock(int id);
}
