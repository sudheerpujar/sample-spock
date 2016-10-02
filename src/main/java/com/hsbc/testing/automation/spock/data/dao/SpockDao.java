package com.hsbc.testing.automation.spock.data.dao;

import com.hsbc.testing.automation.spock.data.mapper.impl.SpockMapper;
import com.hsbc.testing.automation.spock.data.vo.SpockVo;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by sudhe on 24-07-2016.
 */
public interface SpockDao {

    void setDataSource(DataSource dataSource);
    void setMapper(SpockMapper spockMapper);
    SpockMapper getMapper();
    List<SpockVo> getAll();
    SpockVo getById(int id);
    SpockVo getByEmailId(String emailId);
    void createSpock(SpockVo vo);
    void modifySpock(SpockVo vo);
    void removeSpock(int id);
}
