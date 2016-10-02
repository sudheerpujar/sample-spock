package com.hsbc.testing.automation.spock.data.mapper.impl;

import com.hsbc.testing.automation.spock.data.builder.impl.SpockVoBuilder;
import com.hsbc.testing.automation.spock.data.mapper.Mapper;
import com.hsbc.testing.automation.spock.data.vo.SpockVo;


import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by sudhe on 24-07-2016.
 */
public class SpockMapper implements Mapper {
    private SpockVoBuilder spockVoBuilder;

    public SpockVoBuilder getBuilder() {
        return spockVoBuilder;
    }

    public SpockMapper (SpockVoBuilder spockVoBuilder){
        this.spockVoBuilder=spockVoBuilder;
    }

    @Override
    public SpockVo mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        return spockVoBuilder.id(resultSet.getInt("id"))
                .name(resultSet.getString("name"))
                .date(resultSet.getTimestamp("date"))
                .emailId(resultSet.getString("emailid"))
                .amount(resultSet.getFloat("amount"))
                .build();
    }
}
